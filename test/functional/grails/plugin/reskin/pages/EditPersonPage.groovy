package grails.plugin.reskin.pages

import geb.Page

class EditPersonPage extends Page {

	static url = "/person/edit"
	
	static content = {
		form { module PersonForm, find("form") }
	}
	
}
