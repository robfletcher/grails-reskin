package grails.plugin.reskin

import grails.plugin.reskin.pages.ListPersonPage
import grails.plugin.reskin.pages.ShowPersonPage
import spock.lang.*
import test.Person
import grails.plugin.geb.GebSpec
import static test.Gender.*
import static test.Title.*

class ContentFilteringSpec extends GebSpec {
	
	def cleanup() {
		Person.withTransaction {
			Person.list()*.delete()
		}
	}

	def "only fields with data are displayed in show pages"() {
		given: "a person with only minimal data"
		def person = Person.withTransaction {
			new Person(name: "Al Coholic", password: "password", gender: MALE).save(failOnError: true)
		}
		
		when: "viewing the show person page"
		to ShowPersonPage, "$person.id"
		
		then: "fields with values are present"
		$("dt", text: "Name").next("dd").text() == person.name
		
		and: "fields without values are not rendered"
		$("dt", text: "Title").empty
		$("dt", text: "Birthdate").empty
		$("dt", text: "Email").empty
		$("dt", text: "Website").empty
		$("dt", text: "Spouse").empty
	}
	
	def "sensitive fields are omitted from show pages"() {
		given: "a person with only minimal data"
		def person = Person.withTransaction {
			Person.build(name: "Bea O'Problem")
		}
		
		when: "viewing the show person page"
		to ShowPersonPage, "$person.id"
		
		then: "the password field is not displayed"
		$("dt", text: "Password").empty
	}
	
	def "sensitive fields are omitted from list pages"() {
		given: "a person with only minimal data"
		def person = Person.withTransaction {
			Person.build(name: "Bea O'Problem")
		}
		
		when: "viewing the show person page"
		to ListPersonPage
		
		then: "the password field is not displayed"
		$("th", text: "Password").empty
	}
}