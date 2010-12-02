package grails.plugin.reskin.pages

import geb.Module

class NavigationModule extends Module {

	static content = {
		items {
			$("ul li").collect {
				module NavigationItem, it
			}
		}
		activeItem(required: false) {
			module NavigationItem, $("ul li.active", 0)
		}
	}

}

class NavigationItem extends Module {

	static content = {
		link { $("a") }
		label { link.text() }
		url { link.attr("href") }
		subItems(required: false) {
			$("ul li").collect {
				module NavigationItem, it
			}
		}
		activeSubItem(required: false) {
			module NavigationItem, $("ul li.active", 0)
		}
	}

}
