package grails.plugin.reskin.pages

import geb.Module

class PersonForm extends Module {

	static content = {
		nameField { $("input", name: "name") }
		password { $("input", name: "password") }
		birthdate { $("input", name: "birthdate") }
		email { $("input", name: "email") }
		website { $("input", name: "website") }
		gender { $("select", name: "gender") }
		title { $("select", name: "title") }
		spouse { $("select", name: "spouse.id") }
	}

}
