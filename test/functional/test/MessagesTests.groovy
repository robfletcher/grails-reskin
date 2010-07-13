package test

import com.gargoylesoftware.htmlunit.html.HtmlElement
import functionaltestplugin.FunctionalTestCase
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import static javax.servlet.http.HttpServletResponse.SC_OK
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.hasItems
import static org.junit.Assert.assertThat
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import static test.ClassNameMatcher.*

class MessagesTests extends FunctionalTestCase {

	@AfterClass
	static void tearDownFixture() {
		Person.withTransaction {
			Person.list()*.delete()
		}
	}

	@Before
	void setUp() {
		super.setUp()
		baseURL = "http://localhost:8080"
		javaScriptEnabled = false
		get "/person/create"
		assertStatus SC_OK
	}

	@Test
	void textFieldsInErrorAreHighlighted() {
		form {
			create.click()
		}

		assertThat "name label", byXPath("//label[@for='name']"), hasClassName("error")
		assertThat "name field", byId("name"), hasClassName("error")
	}

	@Test
	void selectFieldsInErrorAreHighlighted() {

	}

	@Test
	void customFieldsInErrorAreHighlighted() {
		form {
			create.click()
		}

		assertThat "password label", byXPath("//label[@for='name']"), hasClassName("error")
		assertThat "password field", byId("password"), hasClassName("error")
	}

}

class ClassNameMatcher extends TypeSafeMatcher<HtmlElement> {

	static Matcher<HtmlElement> hasClassName(String expectedClassName) {
		new ClassNameMatcher(expectedClassName)
	}

	private final String expectedClassName

	ClassNameMatcher(String expectedClassName) {
		this.expectedClassName = expectedClassName
	}

	boolean matchesSafely(HtmlElement actual) {
		actual.getAttribute("class") =~ /\b$expectedClassName\b/
	}

	void describeTo(Description description) {
		description.appendText("expected the class '").appendValue(expectedClassName).appendText("'")
	}
}
