package grails.plugin.reskin

import grails.plugin.geb.GebSpec
import grails.plugin.reskin.pages.HomePage
import grails.plugin.reskin.pages.ListPersonPage
import grails.plugin.reskin.pages.CreateNumericPage
import grails.plugin.reskin.pages.CreatePersonPage
import spock.lang.Unroll

class NavigationSpec extends GebSpec {

	@Override
	String getBaseUrl() {
		"http://localhost:8080"
	}

	def "all controllers have a top-level nav item"() {
		given:
		to HomePage

		expect:
		navigation.items.label == ["Home", "Person", "Numeric"]
	}

	@Unroll("top level nav item '#label' is highligted on #page")
	def "top level nav context is highlighted"() {
		given:
		to page

		expect:
		navigation.activeItem.label == label

		where:
		page              | label
		HomePage          | "Home"
		ListPersonPage    | "Person"
		CreatePersonPage  | "Person"
		CreateNumericPage | "Numeric"
	}

	@Unroll("second level nav item #activeSubItem is highlighted on #page")
	def "second level nav for current controller is shown"() {
		given:
		to page

		expect:
		navigation.activeItem.subItems.label == subItems
		navigation.activeItem.activeSubItem.label == activeSubItem

		where:
		page              | subItems           | activeSubItem
		ListPersonPage    | ["List", "Create"] | "List"
		CreatePersonPage  | ["List", "Create"] | "Create"
		CreateNumericPage | ["List", "Create"] | "Create"
	}

}
