package grails.plugin.reskin

import grails.test.AbstractCliTestCase
import org.apache.commons.io.FileUtils
import org.apache.commons.lang.RandomStringUtils
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import static grails.plugin.reskin.CrcMatcher.crcMatches
import static net.time4tea.rsync.matcher.FileMatchers.exists
import static net.time4tea.rsync.matcher.FileMatchers.isDirectory
import static org.hamcrest.Matchers.*
import static org.junit.Assert.assertThat

class InstallationTests extends AbstractCliTestCase {

	File tempDir = new File(System.properties."java.io.tmpdir", getClass().name)
	File packagedPlugin = new File(workDir, "grails-reskin-1.0.zip")
	String tempProjectName = RandomStringUtils.randomAlphanumeric(8)

	@Before
	void setUp() {
		super.setUp()

		runGrailsCommand "package-plugin"

		tempDir.mkdirs()
	}

	@After
	void tearDown() {
		println output
		super.tearDown()
		tempDir.deleteDir()
	}

	@Test
	void installsScaffoldingTemplatesIfNotPresent() {
		def appBaseDir = createTempApp()
		def scaffoldingDir = new File(appBaseDir, "src/templates/scaffolding")
		assertThat "scaffolding directory", scaffoldingDir, not(exists())

		installReskinPlugin appBaseDir
		assertThat "scaffolding directory", scaffoldingDir, isDirectory()
		assertThat "scaffolding templates", scaffoldingDir.listFiles().name, hasItems("create.gsp", "edit.gsp", "list.gsp", "show.gsp", "renderEditor.template")
		scaffoldingDir.listFiles().each { File template ->
			assertThat template.name, template, crcMatches(new File("./src/templates/scaffolding/$template.name"))
		}
	}

	@Test
	void promptsForConfirmationBeforeOverwritingScaffoldingTemplates() {
		def appBaseDir = createTempApp()
		def scaffoldingDir = new File(appBaseDir, "src/templates/scaffolding")
		scaffoldingDir.mkdirs()
		FileUtils.touch new File(scaffoldingDir, "create.gsp")
		FileUtils.touch new File(scaffoldingDir, "edit.gsp")

		workDir = appBaseDir
		execute(["install-plugin", packagedPlugin.absolutePath])
		assertThat "user prompt", output, containsString("Overwrite create.gsp? [y/n]")
		enterInput "y"
		assertThat "user prompt", output, containsString("Overwrite edit.gsp? [y/n]")
		enterInput "n"
		waitForProcessSuccess "install-plugin", packagedPlugin.absolutePath
		verifyHeader()

		installReskinPlugin appBaseDir
		assertThat "create.gsp", new File(scaffoldingDir, "create.gsp"), crcMatches(new File("./src/templates/scaffolding/create.gsp"))
		assertThat "edit.gsp", new File(scaffoldingDir, "edit.gsp"), not(crcMatches(new File("./src/templates/scaffolding/edit.gsp")))
	}

	private File createTempApp() {
		workDir = tempDir
		runGrailsCommand "create-app", tempProjectName
		return new File(tempDir, tempProjectName)
	}

	private void installReskinPlugin(File appBaseDir) {
		workDir = appBaseDir
		runGrailsCommand "install-plugin", packagedPlugin.absolutePath
	}

	private void runGrailsCommand(String... args) {
		execute(args as List)
		waitForProcessSuccess(args)
		verifyHeader()
	}

	private void waitForProcessSuccess(String... args) {
		assertThat "exit code from ${args.join(' ')}", waitForProcess(), equalTo(0)
	}

}

class CrcMatcher extends TypeSafeMatcher<File> {

	static Matcher<File> crcMatches(File expected) {
		new CrcMatcher(FileUtils.checksumCRC32(expected))
	}

	private final long expectedCrc

	CrcMatcher(long expectedCrc) {
		this.expectedCrc = expectedCrc;
	}

	boolean matchesSafely(File t) {
		FileUtils.checksumCRC32(t) == expectedCrc
	}

	void describeTo(Description description) {
		description.appendText("a File with a CRC checksum matching ").appendValue(expectedCrc)
	}
}