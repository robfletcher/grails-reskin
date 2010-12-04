grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.dependency.resolution = {
	inherits "global"
	log "warn"
	repositories {
		grailsPlugins()
		grailsHome()
		grailsCentral()
	}
	dependencies {
		test("org.hamcrest:hamcrest-all:1.1") {
			excludes "junit", "easymock", "jmock"
		}
		test("org.seleniumhq.selenium:selenium-htmlunit-driver:2.0a5") {
			excludes "xml-apis"
		}
	}
	plugins {
		compile ":navigation:1.1.1"
		runtime ":bean-fields:1.0-RC3"
		runtime ":jquery:1.4.3.2"
		runtime ":jquery-ui:1.8.6.1"
		test ":spock:0.5-groovy-1.7-SNAPSHOT"
		test ":geb:0.5-SNAPSHOT"
		test ":build-test-data:1.1.1"
	}
}
