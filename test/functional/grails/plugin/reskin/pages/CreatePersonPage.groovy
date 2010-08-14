package grails.plugin.reskin.pages

import geb.Page

class CreatePersonPage extends Page {

	static url = "/person/create"

	static at = {
		heading.text() == "Create Person"
	}

	static content = {
		heading { $("header h1") }
		flashMessage(required: false) { $("aside.message") }
		errorMessages(required: false) { $("aside.errors ul li") }
		form { $("form") }
		createButton(toPage: ShowPersonPage) { form.create() }
	}

}
