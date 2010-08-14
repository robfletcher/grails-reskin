package grails.plugin.reskin

import grails.plugin.reskin.pages.EditPersonPage
import spock.lang.*
import test.Person
import static test.Gender.FEMALE
import static test.Gender.MALE
import spock.lang.Shared
import grails.plugin.geb.GebSpec

class InputTypeSpec extends GebSpec {

	@Shared Person person1, person2

	def setup() {
//		geb.client.javaScriptEnabled = false
	}

	def setupSpec() {
		Person.withTransaction {
			person1 = Person.build(name: "Al Coholic", gender: MALE)
			person2 = Person.build(name: "Bea O'Problem", gender: FEMALE)
		}
	}

	def cleanupSpec() {
		Person.withTransaction {
			Person.list()*.delete()
		}
	}

	String getBaseUrl() {
		"http://localhost:8080/"
	}

	@Unroll("the #property property is rendered as a #inputType input")
	def "input types"() {
		when: "I am on the edit page"
		to EditPersonPage, person1.id

		then: "the correct input type is used"
		form."$property"().@type == inputType

		and: "the initial value is correct"
		form."$property" == value

		where:
		property    | inputType  | value
		"password"  | "password" | person1.password
		"birthdate" | "date"     | person1.birthdate.format("yyyy-MM-dd")
		"email"     | "email"    | person1.email
		"website"   | "url"      | person1.website as String
	}

	@Unroll("the #property property is rendered as a select")
	def "select rendering"() {
		when: "I am on the edit page"
		to EditPersonPage, "$person1.id"

		then: "selects are rendered correctly"
		form."$property"().find("option")*.@value == options
		form."$property"().find("option")*.text() == labels
		form."$property" == selected

		where:
		property    | options                                | labels                           | selected
		"gender"    | ["MALE", "FEMALE"]                     | ["Male", "Female"]               | "MALE"
		"title"     | ["", "MR", "MRS", "MS", "DR"]          | ["", "Mr", "Mrs", "Ms", "Dr"]    | null
		"spouse.id" | ["null", "$person1.id", "$person2.id"] | ["", person1.name, person2.name] | "null"
	}
}
