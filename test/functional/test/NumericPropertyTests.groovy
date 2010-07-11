package test

import functionaltestplugin.FunctionalTestCase
import org.junit.Test
import static com.gargoylesoftware.htmlunit.html.DomElement.ATTRIBUTE_NOT_DEFINED
import static javax.servlet.http.HttpServletResponse.SC_OK
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.hasItems
import static org.junit.Assert.assertThat
import org.junit.Before
import com.gargoylesoftware.htmlunit.html.HtmlInput
import com.gargoylesoftware.htmlunit.html.HtmlSelect

class NumericPropertyTests extends FunctionalTestCase {

	@Before
	void setUp() {
		super.setUp()
		baseURL = "http://localhost:8080"
		javaScriptEnabled = false
		get "/numeric/create"
		assertStatus SC_OK
	}

	@Test
	void bytePropertyIsRenderedAsNumberWithMinAndMax() {
		HtmlInput element = byName("simpleByte")
		assertThat "input type", element.getAttribute("type"), equalTo("number")
		assertThat "input min", element.getAttribute("min"), equalTo(Byte.MIN_VALUE as String)
		assertThat "input max", element.getAttribute("max"), equalTo(Byte.MAX_VALUE as String)
	}

	@Test
	void intPropertyIsRenderedAsNumber() {
		HtmlInput element = byName("simpleInt")
		assertThat "input type", element.getAttribute("type"), equalTo("number")
		assertThat "input min", element.getAttribute("min"), equalTo(ATTRIBUTE_NOT_DEFINED)
		assertThat "input max", element.getAttribute("max"), equalTo(ATTRIBUTE_NOT_DEFINED)
	}

	@Test
	void intPropertyWithRangeConstraintIsRenderedAsRange() {
		HtmlInput element = byName("intInRange")
		assertThat "input type", element.getAttribute("type"), equalTo("range")
		assertThat "input min", element.getAttribute("min"), equalTo("1")
		assertThat "input max", element.getAttribute("max"), equalTo("10")
	}

	@Test
	void intPropertyWithInListConstraintIsRenderedAsSelect() {
		HtmlSelect element = byName("intInList")
		assertThat "input type", element.tagName, equalTo("select")
		assertThat "input options", element.options.valueAttribute, hasItems("2", "4", "6", "8", "10")
	}

	@Test
	void intPropertyWithMinConstraintIsRenderedAsNumberWithMinAttribute() {
		HtmlInput element = byName("intWithMin")
		assertThat "input type", element.getAttribute("type"), equalTo("number")
		assertThat "input min", element.getAttribute("min"), equalTo("1")
		assertThat "input max", element.getAttribute("max"), equalTo(ATTRIBUTE_NOT_DEFINED)
	}

	@Test
	void intPropertyWithMaxConstraintIsRenderedAsNumberWithMaxAttribute() {
		HtmlInput element = byName("intWithMax")
		assertThat "input type", element.getAttribute("type"), equalTo("number")
		assertThat "input min", element.getAttribute("min"), equalTo(ATTRIBUTE_NOT_DEFINED)
		assertThat "input max", element.getAttribute("max"), equalTo("10")
	}

	@Test
	void intPropertyWithMinAndMaxConstraintsIsRenderedAsNumberWithMinAndMaxAttributes() {
		HtmlInput element = byName("intWithMinAndMax")
		assertThat "input type", element.getAttribute("type"), equalTo("number")
		assertThat "input min", element.getAttribute("min"), equalTo("1")
		assertThat "input max", element.getAttribute("max"), equalTo("10")
	}

}
