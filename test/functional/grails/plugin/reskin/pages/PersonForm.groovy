package grails.plugin.reskin.pages

import org.codehaus.geb.spock.page.Module

class PersonForm extends Module {

	static content = {
		password { find("input").withName("password") }
		birthdate { find("input").withName("birthdate") }
		email { find("input").withName("email") }
		website { find("input").withName("website") }
		gender { find("select").withName("gender") }
		title { find("select").withName("title") }
		spouse { find("select").withName("spouse.id") }
	}

}
