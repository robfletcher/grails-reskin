package grails.plugin.reskin

import grails.plugin.reskin.pages.CreatePersonPage
import spock.lang.*
import test.Person
import grails.plugin.geb.GebSpec

class MessagesSpec extends GebSpec {

	def cleanupSpec() {
		Person.withTransaction {
			Person.list()*.delete()
		}
	}

	def setup() {
//		geb.client.javaScriptEnabled = false
	}

	String getBaseUrl() {
		"http://localhost:8080/"
	}

	@Unroll("the #property field with value '#value' fails validation")
	def "text field errors"() {
		given: "I am on the create person page"
		to CreatePersonPage

		when: "I submit the form with an invalid value"
		form."$property" = value
		createButton.click CreatePersonPage

		then: "the create form is re-displayed"
		at CreatePersonPage

		and: "the field is marked with an error"
		form."$property"().hasClass("error")
		form."$property"().next("aside").text() == errorMessage

		where:
		property    | value     | errorMessage
		"name"      | ""        | "Property [name] of class [class test.Person] cannot be blank"
		"password"  | ""        | "Property [password] of class [class test.Person] cannot be blank"
		"birthdate" | "invalid" | "Property birthdate must be a valid Date"
		"email"     | "invalid" | "Property [email] of class [class test.Person] with value [invalid] is not a valid e-mail address"
		"website"   | "invalid" | "Property [website] of class [class test.Person] with value [invalid] is not a valid URL"
	}

	def "field errors are not duplicated above the form"() {
		given: "I am on the create person page"
		to CreatePersonPage

		when: "I submit the form with an invalid value"
		form.name = ""
		createButton.click(CreatePersonPage)

		then: "the create form is re-displayed"
		at CreatePersonPage

		and: "the field is marked with an error"
		form.name().next("aside").text() == errorMessage
		!(errorMessage in errorMessages*.text())

		where:
		errorMessage = "Property [name] of class [class test.Person] cannot be blank"
	}

}
