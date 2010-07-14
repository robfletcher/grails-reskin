package grails.plugin.reskin

import test.Person
import static test.Gender.*
import static test.Title.*
import grails.plugin.gebspock.GebSpec
import grails.plugin.reskin.pages.ListPersonPage
import grails.plugin.reskin.pages.ShowPersonPage

class RenderingSpec extends GebSpec {

	Person person1, person2

	def setup() {
		geb.client.javaScriptEnabled = false

		Person.withTransaction {
			person1 = new Person(title: MR, name: "Al Coholic", password: "s3cr37", gender: MALE, birthdate: new Date(71, 10, 29), email: "al@10minutemail.com", website: new URL("http://icanhascheezburger.com/"))
			person1.save(failOnError: true, flush: true)
			person2 = new Person(name: "Bea O'Problem", password: "s3cr37", gender: FEMALE, birthdate: new Date(72, 7, 6), email: "bea@10minutemail.com", website: new URL("http://icanhascheezburger.com/"))
			person2.save(failOnError: true, flush: true)
		}
	}

	def cleanup() {
		Person.withTransaction {
			Person.list()*.delete()
		}
	}

	def getBaseUrl() {
		"http://localhost:8080/"
	}

	def "MessageSourceResolvable properties are resolved on list pages"() {
		when:
		to ListPersonPage

		then:
		find("table tbody").get("tr", 0).get("td", 1).text() == "Mr"
		find("table tbody").get("tr", 1).get("td", 1).text() == ""
	}

	def "MessageSourceResolvable properties are resolved on show pages"() {
		when:
		to ShowPersonPage, "$person1.id"

		then:
		find("#title").text() == "Mr"
		find("#gender").text() == "Male"
	}

}
