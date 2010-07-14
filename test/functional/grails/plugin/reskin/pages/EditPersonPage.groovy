package grails.plugin.reskin.pages

import org.codehaus.geb.spock.page.Page

class EditPersonPage extends Page {

	static url = "/person/edit"
	
	static content = {
		form { module PersonForm, find("form") }
	}
	
}
