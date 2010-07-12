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

class InputTypeTests extends FunctionalTestCase {

	static person1, person2

	@BeforeClass
	static void setUpFixture() {
		Person.withTransaction {
			person1 = new Person(name: "Al Coholic", password: "s3cr37", gender: Gender.MALE, birthdate: new Date(71, 10, 29), email: "al@10minutemail.com", website: new URL("http://icanhascheezburger.com/"))
			person1.save(failOnError: true, flush: true)
			person2 = new Person(name: "Bea O'Problem", password: "s3cr37", gender: Gender.FEMALE, birthdate: new Date(72, 7, 6), email: "bea@10minutemail.com", website: new URL("http://icanhascheezburger.com/"))
			person2.save(failOnError: true, flush: true)
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
		get "/person/edit/$person1.id"
		assertStatus SC_OK
	}

	@Test
	void passwordPropertyIsRenderedAsEmail() {
		HtmlElement element = byName("password")
		assertThat "input type", element.getAttribute("type"), equalTo("password")
		assertThat "input value", element.getAttribute("value"), equalTo("s3cr37")
	}

	@Test
	void datePropertyIsRenderedAsDate() {
		HtmlElement element = byName("birthdate")
		assertThat "input type", element.getAttribute("type"), equalTo("date")
		assertThat "input value", element.getAttribute("value"), equalTo("1971-11-29")
	}

	@Test
	void enumPropertyIsRenderedAsSelect() {
		HtmlElement element = byName("gender")
		assertThat "input type", element.tagName, equalTo("select")
		assertThat "input options", element.options.valueAttribute, hasItems("MALE", "FEMALE")
		assertThat "input labels", element.options.text, hasItems("Male", "Female")
		assertThat "selected option", element.selectedOptions.valueAttribute, equalTo(["MALE"])
	}

	@Test
	void nullableEnumPropertyIsRenderedAsSelectWithBlankOption() {
		HtmlElement element = byName("title")
		assertThat "input type", element.tagName, equalTo("select")
		assertThat "input options", element.options[0].valueAttribute, equalTo("")
	}

	@Test
	void emailPropertyIsRenderedAsEmail() {
		HtmlElement element = byName("email")
		assertThat "input type", element.getAttribute("type"), equalTo("email")
		assertThat "input value", element.getAttribute("value"), equalTo("al@10minutemail.com")
	}

	@Test
	void urlPropertyIsRenderedAsUrl() {
		HtmlElement element = byName("website")
		assertThat "input type", element.getAttribute("type"), equalTo("url")
		assertThat "input value", element.getAttribute("value"), equalTo("http://icanhascheezburger.com/")
	}

	@Test
	void associationPropertyIsRenderedAsSelect() {
		HtmlElement element = byName("spouse.id")
		assertThat "input type", element.tagName, equalTo("select")
		assertThat "input options", element.options.valueAttribute, hasItems("null", "$person1.id" as String, "$person2.id" as String)
		assertThat "input labels", element.options.text, hasItems("", person1.toString(), person2.toString())
	}

}
