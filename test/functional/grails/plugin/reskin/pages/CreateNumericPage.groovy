package grails.plugin.reskin.pages

import geb.Page

class CreateNumericPage extends Page {

	static url = "/numeric/create"

	static content = {
		form { $("form") }
	}

}
