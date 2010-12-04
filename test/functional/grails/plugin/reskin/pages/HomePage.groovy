package grails.plugin.reskin.pages

import geb.Page

class HomePage extends Page {

	static url = "/"
	static at = { title == "Welcome to Grails" }

	static content = {
		navigation { module NavigationModule, $(".header .nav") }
	}

}
