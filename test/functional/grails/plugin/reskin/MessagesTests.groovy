package grails.plugin.reskin

import com.gargoylesoftware.htmlunit.html.HtmlElement
import functionaltestplugin.FunctionalTestCase
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.AfterClass
import org.junit.Before
import org.junit.Test
import test.Person
import static grails.plugin.reskin.ClassNameMatcher.hasClassName
import static javax.servlet.http.HttpServletResponse.SC_OK
import static org.hamcrest.Matchers.*
import static org.junit.Assert.assertThat

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
		assertThat "name error message", byId("name").nextSibling.textContent, equalTo("Property [name] of class [class test.Person] cannot be blank")
	}

	@Test
	void selectFieldsInErrorAreHighlighted() {

	}

	// TODO: all these tests could be merged using spock & @Unroll

	@Test
	void passwordFieldsInErrorAreHighlighted() {
		form {
			create.click()
		}

		assertThat "password label", byXPath("//label[@for='password']"), hasClassName("error")
		assertThat "password field", byId("password"), hasClassName("error")
		assertThat "name error message", byId("password").nextSibling.textContent, equalTo("Property [password] of class [class test.Person] cannot be blank")
	}

	@Test
	void dateFieldsInErrorAreHighlighted() {
		form {
			birthdate = "invalid"
			create.click()
		}

		assertThat "birthdate label", byXPath("//label[@for='birthdate']"), hasClassName("error")
		assertThat "birthdate field", byId("birthdate"), hasClassName("error")
		assertThat "birthdate error message", byId("birthdate").nextSibling.textContent, equalTo("Property birthdate must be a valid Date")
	}

	@Test
	void emailFieldsInErrorAreHighlighted() {
		form {
			email = "invalid"
			create.click()
		}

		assertThat "email label", byXPath("//label[@for='email']"), hasClassName("error")
		assertThat "email field", byId("email"), hasClassName("error")
		assertThat "email error message", byId("email").nextSibling.textContent, equalTo("Property [email] of class [class test.Person] with value [invalid] is not a valid e-mail address")
	}

	@Test
	void urlFieldsInErrorAreHighlighted() {
		form {
			website = "invalid"
			create.click()
		}

		assertThat "website label", byXPath("//label[@for='website']"), hasClassName("error")
		assertThat "website field", byId("website"), hasClassName("error")
		assertThat "website error message", byId("website").nextSibling.textContent, equalTo("Property [website] of class [class test.Person] with value [invalid] is not a valid URL")
	}

	@Test
	void errorMessagesAreNotDuplicatedAtTopOfForm() {
		form {
			create.click()
		}

		assertThat "name error message", byId("name").nextSibling.textContent, equalTo("Property [name] of class [class test.Person] cannot be blank")
		assertThat "form-wide error messages",
				byXPath("//section[@class='dialog']/aside[@class='errors']/ul").childElements.collect {
					it.textContent
				},
				not(hasItem("Property [name] of class [class test.Person] cannot be blank"))
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
