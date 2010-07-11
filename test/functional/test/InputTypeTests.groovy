package test

import functionaltestplugin.FunctionalTestCase
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import static javax.servlet.http.HttpServletResponse.SC_OK
import static org.hamcrest.Matchers.*
import static org.junit.Assert.assertThat
import com.gargoylesoftware.htmlunit.html.HtmlInput
import com.gargoylesoftware.htmlunit.html.HtmlSelect

class InputTypeTests extends FunctionalTestCase {

	static personId

	@BeforeClass
	static void setUpFixture() {
		Person.withTransaction {
			def person = new Person(name: "Al Coholic", password: "s3cr37", gender: Gender.MALE, birthdate: new Date(71, 10, 29), email: "al@10minutemail.com", website: new URL("http://icanhascheezburger.com/"))
			person.save(failOnError: true, flush: true)
			personId = person.id
		}
	}

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
		get "/person/edit/$personId"
		assertStatus SC_OK
	}

	@Test
	void passwordPropertyIsRenderedAsEmail() {
		HtmlInput element = byName("password")
		assertThat "input type", element.getAttribute("type"), equalTo("password")
		assertThat "input value", element.getAttribute("value"), equalTo("s3cr37")
	}

	@Test
	void datePropertyIsRenderedAsDate() {
		HtmlInput element = byName("birthdate")
		assertThat "input type", element.getAttribute("type"), equalTo("date")
		assertThat "input value", element.getAttribute("value"), equalTo("1971-11-29")
	}

	@Test
	void enumPropertyIsRenderedAsSelect() {
		HtmlSelect element = byName("gender")
		assertThat "input type", element.tagName, equalTo("select")
		assertThat "input options", element.options.valueAttribute, hasItems("MALE", "FEMALE")
		assertThat "selected option", element.selectedOptions.valueAttribute, equalTo(["MALE"])
	}

	@Test
	void emailPropertyIsRenderedAsEmail() {
		HtmlInput element = byName("email")
		assertThat "input type", element.getAttribute("type"), equalTo("email")
		assertThat "input value", element.getAttribute("value"), equalTo("al@10minutemail.com")
	}

	@Test
	void urlPropertyIsRenderedAsUrl() {
		HtmlInput element = byName("website")
		assertThat "input type", element.getAttribute("type"), equalTo("url")
		assertThat "input value", element.getAttribute("value"), equalTo("http://icanhascheezburger.com/")
	}

}
