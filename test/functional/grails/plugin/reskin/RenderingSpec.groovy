package grails.plugin.reskin

import grails.plugin.gebspock.GebSpec
import grails.plugin.reskin.pages.ListPersonPage
import grails.plugin.reskin.pages.ShowPersonPage
import spock.lang.Shared
import test.Person
import static test.Gender.FEMALE
import static test.Title.MR

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

	def setup() {
		geb.client.javaScriptEnabled = false
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
