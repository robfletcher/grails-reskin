package grails.plugin.reskin

import grails.plugin.geb.GebSpec
import spock.lang.Unroll
import grails.plugin.reskin.pages.*

class NavigationSpec extends GebSpec {

	@Override
	String getBaseUrl() {
		"http://localhost:8080"
	}

	def "all controllers have a top-level nav item"() {
		given:
		to ListPersonPage

		expect:
		navigation.items.label == ["Person", "Numeric"]
	}

	@Unroll("top level nav item '#label' is highligted on #page")
	def "top level nav context is highlighted"() {
		given:
		to page

		expect:
		navigation.activeItem.label == label

		where:
		page              | label
		ListPersonPage    | "Person"
		CreatePersonPage  | "Person"
		CreateNumericPage | "Numeric"
	}

	@Unroll("second level nav item #activeSubItem is highlighted on #page")
	def "second level nav for current controller is shown"() {
		given:
		to page

		expect:
		navigation.subItems.label == subItems
		navigation.activeSubItem.label == activeSubItem

		where:
		page              | subItems           | activeSubItem
		ListPersonPage    | ["List", "Create"] | "List"
		CreatePersonPage  | ["List", "Create"] | "Create"
		CreateNumericPage | ["List", "Create"] | "Create"
	}

}
