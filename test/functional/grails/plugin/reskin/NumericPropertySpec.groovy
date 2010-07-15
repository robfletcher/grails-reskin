package grails.plugin.reskin

import grails.plugin.gebspock.GebSpec
import grails.plugin.reskin.pages.CreateNumericPage
import spock.lang.Unroll

class NumericPropertySpec extends GebSpec {

	def setup() {
		geb.client.javaScriptEnabled = false
	}

	def getBaseUrl() {
		"http://localhost:8080/"
	}

	@Unroll("the #property property is rendered as a #inputType input")
	def "numeric inputs with min and max"() {
		when:
		to CreateNumericPage

		then:
		form."$property".attribute("type") == inputType
		form."$property".attribute("min") == min
		form."$property".attribute("max") == max

		where:
		property           | inputType | min               | max
		"simpleByte"       | "number"  | "$Byte.MIN_VALUE" | "$Byte.MAX_VALUE"
		"simpleInt"        | "number"  | ""                | ""
		"intInRange"       | "range"   | "1"               | "10"
		"intWithMin"       | "number"  | "1"               | ""
		"intWithMax"       | "number"  | ""                | "10"
		"intWithMinAndMax" | "number"  | "1"               | "10"
	}

	def "the intInList property is rendered as a select"() {
		when:
		to CreateNumericPage

		then:
		form.intInList.get("option")*.attribute("value") == expectedOptions

		where:
		expectedOptions = ["2", "4", "6", "8", "10"]
	}

}
