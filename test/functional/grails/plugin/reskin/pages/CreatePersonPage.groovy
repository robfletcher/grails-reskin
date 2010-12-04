package grails.plugin.reskin.pages

import geb.Page

class CreatePersonPage extends Page {

	static url = "/person/create"

	static at = {
		heading.text() == "Create Person"
	}

	static content = {
		heading { $("h1") }
		flashMessage(required: false) { $(".message") }
		errorMessages(required: false) { $(".errors ul li") }
		form(dynamic: true) { $("form") }
		createButton(toPage: ShowPersonPage) { form.create() }
		navigation { module NavigationModule, $(".header .nav") }
	}

}
