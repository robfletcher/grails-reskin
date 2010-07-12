package test

import com.gargoylesoftware.htmlunit.html.HtmlElement
import functionaltestplugin.FunctionalTestCase
import org.junit.Before
import org.junit.Test
import static com.gargoylesoftware.htmlunit.html.DomElement.ATTRIBUTE_NOT_DEFINED
import static javax.servlet.http.HttpServletResponse.SC_OK
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.hasItems
import static org.junit.Assert.assertThat
import org.junit.AfterClass
import org.junit.BeforeClass

class RenderingTests extends FunctionalTestCase {

	static Person person1, person2

	@BeforeClass
	static void setUpFixture() {
		Person.withTransaction {
			person1 = new Person(title: Title.MR, name: "Al Coholic", password: "s3cr37", gender: Gender.MALE, birthdate: new Date(71, 10, 29), email: "al@10minutemail.com", website: new URL("http://icanhascheezburger.com/"))
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
	}

	@Test
	void messageSourceResolvablePropertiesValuesAreRenderedUsingMessageTagOnListPage() {
		get "/person/list"
		assertStatus SC_OK

		assertThat "rendered value", byXPath("//table/tbody/tr[1]/td[2]").textContent, equalTo("Mr")
		assertThat "rendered value", byXPath("//table/tbody/tr[2]/td[2]").textContent, equalTo("")
	}

	@Test
	void messageSourceResolvablePropertiesValuesAreRenderedUsingMessageTagOnShowPage() {
		get "/person/show/$person1.id"
		assertStatus SC_OK

		assertThat "rendered value", byId("title").textContent, equalTo("Mr")
		assertThat "rendered value", byId("gender").textContent, equalTo("Male")
	}

	@Test
	void nullableMessageSourceResolvablePropertiesValuesAreHandled() {
		get "/person/show/$person2.id"
		assertStatus SC_OK

		assertThat "rendered value", byId("title").textContent, equalTo("")
	}

}
