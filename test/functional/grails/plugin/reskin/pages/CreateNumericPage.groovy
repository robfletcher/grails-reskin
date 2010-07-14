package grails.plugin.reskin.pages

import org.codehaus.geb.spock.page.Page

class CreateNumericPage extends Page {

	static url = "/numeric/create"

	static content = {
		form { module NumericForm, find("form") }
	}

}
