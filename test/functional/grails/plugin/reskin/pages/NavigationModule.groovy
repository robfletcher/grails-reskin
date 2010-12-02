package grails.plugin.reskin.pages

import geb.Module

class NavigationModule extends Module {

	static content = {
		items {
			$("ul.navigation li").collect {
				module NavigationItem, it
			}
		}
		activeItem(required: false) {
			module NavigationItem, $("ul li.navigation_active", 0)
		}
		subItems(required: false) {
			$("ul.subnavigation li").collect {
				module NavigationItem, it
			}
		}
		activeSubItem(required: false) {
			module NavigationItem, $("ul.subnavigation li.subnavigation_active", 0)
		}
	}

}

class NavigationItem extends Module {

	static content = {
		link { $("a") }
		label { link.text() }
		url { link.attr("href") }
	}

}
