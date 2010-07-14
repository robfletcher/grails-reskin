package grails.plugin.reskin

import grails.plugin.gebspock.GebSpec
import grails.plugin.reskin.pages.EditPersonPage
import spock.lang.Unroll
import test.Person
import static test.Gender.FEMALE
import static test.Gender.MALE
import spock.lang.Shared

class InputTypeSpec extends GebSpec {

	@Shared static Person person1, person2

	def setup() {
		geb.client.javaScriptEnabled = false
	}

	def setupSpec() {
		Person.withTransaction {
			person1 = new Person(name: "Al Coholic", password: "s3cr37", gender: MALE, birthdate: new Date(71, 10, 29), email: "al@10minutemail.com", website: new URL("http://icanhascheezburger.com/"))
			person1.save(failOnError: true, flush: true)
			person2 = new Person(name: "Bea O'Problem", password: "s3cr37", gender: FEMALE, birthdate: new Date(72, 7, 6), email: "bea@10minutemail.com", website: new URL("http://icanhascheezburger.com/"))
			person2.save(failOnError: true, flush: true)
		}
	}

	def cleanupSpec() {
		Person.withTransaction {
			Person.list()*.delete()
		}
	}

	def getBaseUrl() {
		"http://localhost:8080/"
	}

	@Unroll("the #property property is rendered as a #inputType input")
	def "input types"() {
		when: "I am on the edit page"
		to EditPersonPage, "$person1.id"

		then: "the correct input type is used"
		form."$property".attribute("type") == inputType

		where:
		property    | inputType
		"password"  | "password"
		"birthdate" | "date"
		"email"     | "email"
		"website"   | "url"
	}

	@Unroll("the #property property is rendered as a select")
	def "select rendering"() {
		when: "I am on the edit page"
		to EditPersonPage, "$person1.id"

		then: "selects are rendered correctly"
		form."$property".get("option")*.attribute("value") == options
		form."$property".get("option")*.text() == labels
		form."$property".get("option").with("selected")*.attribute("value") == selected

		where:
		property | options                                | labels                           | selected
		"gender" | ["MALE", "FEMALE"]                     | ["Male", "Female"]               | ["MALE"]
		"title"  | ["", "MR", "MRS", "MS", "DR"]          | ["", "Mr", "Mrs", "Ms", "Dr"]    | []
		"spouse" | ["null", "$person1.id", "$person2.id"] | ["", person1.name, person2.name] | []
	}
}
