package grails.plugin.reskin.pages

import org.codehaus.geb.spock.page.Page

class CreatePersonPage extends Page {

	static url = "/person/create"

	static at = {
		heading.text() == "Create Person"
	}

	static content = {
		heading { find("header h1") }
		flashMessage(required: false) { find("aside.message") }
		errorMessages(required: false) { find("aside.errors ul li") }
		form { module PersonForm, find("form") }
		createButton(toPage: ShowPersonPage) { find("input").withName("create") }
	}

}
