package grails.plugin.reskin.pages

import geb.Module

class NumericForm extends Module {

	static content = {
		simpleByte { $("input", name: "simpleByte") }
		simpleInt { $("input", name: "simpleInt") }
		intInRange { $("input", name: "intInRange") }
		intInList { $("select", name: "intInList") }
		intWithMin { $("input", name: "intWithMin") }
		intWithMax { $("input", name: "intWithMax") }
		intWithMinAndMax { $("input", name: "intWithMinAndMax") }
	}

}
