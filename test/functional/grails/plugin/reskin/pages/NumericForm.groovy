package grails.plugin.reskin.pages

import org.codehaus.geb.spock.page.Module

class NumericForm extends Module {

	static content = {
		simpleByte { find("input").withName("simpleByte") }
		simpleInt { find("input").withName("simpleInt") }
		intInRange { find("input").withName("intInRange") }
		intInList { find("select").withName("intInList") }
		intWithMin { find("input").withName("intWithMin") }
		intWithMax { find("input").withName("intWithMax") }
		intWithMinAndMax { find("input").withName("intWithMinAndMax") }
	}

}
