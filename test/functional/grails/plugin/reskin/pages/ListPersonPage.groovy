package grails.plugin.reskin.pages

import geb.Page

class ListPersonPage extends Page {

	static url = "/person/list"

	static content = {
		navigation { module NavigationModule, $("header nav") }
	}

}
