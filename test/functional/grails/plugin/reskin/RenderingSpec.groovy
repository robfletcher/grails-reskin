package grails.plugin.reskin

import grails.plugin.reskin.pages.ListPersonPage
import grails.plugin.reskin.pages.ShowPersonPage
import spock.lang.*
import test.Person
import static test.Gender.FEMALE
import static test.Title.MR
import grails.plugin.geb.GebSpec

class RenderingSpec extends GebSpec {

	@Shared Person person1, person2

	def setupSpec() {
		Person.withTransaction {
			person1 = Person.build(title: MR, name: "Al Coholic")
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

	def "MessageSourceResolvable properties are resolved on list pages"() {
		when:
		to ListPersonPage

		then:
		$("table tbody").find("tr", 0).find("td", 1).text() == "Mr"
		$("table tbody").find("tr", 1).find("td", 1).text() == ""
	}

	def "MessageSourceResolvable properties are resolved on show pages"() {
		when:
		to ShowPersonPage, "$person1.id"

		then:
		$("dt", text: "Title").next("dd").text() == "Mr"
		$("dt", text: "Gender").next("dd").text() == "Male"
	}

}
