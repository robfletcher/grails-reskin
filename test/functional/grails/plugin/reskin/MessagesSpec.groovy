package grails.plugin.reskin

import grails.plugin.reskin.pages.CreatePersonPage
import spock.lang.*
import test.Person
import grails.plugin.geb.GebSpec
import org.openqa.selenium.By

class MessagesSpec extends GebSpec {

	def cleanupSpec() {
		Person.withTransaction {
			Person.list()*.delete()
		}
	}

	String getBaseUrl() {
		"http://localhost:8080/"
	}

	@Unroll("the #property field with value '#value' fails validation")
	def "text field errors"() {
		given: "I am on the create person page"
		to CreatePersonPage
		def element = browser.driver.findElement(By.name(property))

		when: "I submit the form with an invalid value"
		form."$property" = value
		createButton.click CreatePersonPage
		def element2 = browser.driver.findElement(By.name(property))

		then: "the create form is re-displayed"
		at CreatePersonPage

		and: "the field is marked with an error"
		"error" in $("#$property").classes()
		"error" in form."$property"().classes()
		form."$property"().hasClass("error")
		form."$property"().next("aside").text() == errorMessage

		where:
		property    | value     | errorMessage
		"name"      | ""        | "Property [name] of class [class test.Person] cannot be blank"
		"password"  | ""        | "Property [password] of class [class test.Person] cannot be blank"
		"birthdate" | "invalid" | "Property birthdate must be a valid Date"
		"email"     | "invalid" | "Property [email] of class [class test.Person] with value [invalid] is not a valid e-mail address"
		"website"   | "invalid" | "Property website must be a valid URL"
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
