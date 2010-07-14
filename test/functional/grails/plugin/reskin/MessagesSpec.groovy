package grails.plugin.reskin

import grails.plugin.gebspock.GebSpec
import grails.plugin.reskin.pages.CreatePersonPage
import spock.lang.Unroll
import test.Person

class MessagesSpec extends GebSpec {

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

	@Unroll("the #property field with value '#value' fails validation")
	def "text field errors"() {
		given: "I am on the create person page"
		to CreatePersonPage

		when: "I submit the form with an invalid value"
		form."$property".value(value)
		form.createButton.click()

		then: "the create form is re-displayed"
		at CreatePersonPage

		and: "the field is marked with an error"
		form."$property".classValue() =~ /\berror\b/
		form."$property".next("aside").text() == errorMessage

		where:
		property    | value     | errorMessage
		"nameField" | ""        | "Property [name] of class [class test.Person] cannot be blank"
		"password"  | ""        | "Property [password] of class [class test.Person] cannot be blank"
		"birthdate" | "invalid" | "Property birthdate must be a valid Date"
		"email"     | "invalid" | "Property [email] of class [class test.Person] with value [invalid] is not a valid e-mail address"
		"website"   | "invalid" | "Property [website] of class [class test.Person] with value [invalid] is not a valid URL"
	}

//	@Test
//	void selectFieldsInErrorAreHighlighted() {
//
//	}
//
//	@Test
//	void errorMessagesAreNotDuplicatedAtTopOfForm() {
//		form {
//			create.click()
//		}
//
//		assertThat "name error message", byId("name").nextSibling.textContent, equalTo("Property [name] of class [class test.Person] cannot be blank")
//		assertThat "form-wide error messages",
//				byXPath("//section[@class='dialog']/aside[@class='errors']/ul").childElements.collect {
//					it.textContent
//				},
//				not(hasItem("Property [name] of class [class test.Person] cannot be blank"))
//	}

	def "field errors are not duplicated above the form"() {
		given: "I am on the create person page"
		to CreatePersonPage

		when: "I submit the form with an invalid value"
		form.nameField.value("")
		form.createButton.click()

		then: "the create form is re-displayed"
		at CreatePersonPage

		and: "the field is marked with an error"
		form.nameField.next("aside").text() == errorMessage
		!(errorMessage in errorMessages*.text())

		where:
		errorMessage = "Property [name] of class [class test.Person] cannot be blank"
	}

}
