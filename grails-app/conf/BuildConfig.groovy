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
		test("org.spockframework:spock-grails-support:0.5-groovy-1.7-SNAPSHOT") {
			excludes "svnkit", "servlet-api", "jstl", "oscache", "spring-webflow", "grails-webflow", "ant-junit", "grails-test", "junit", "slf4j-log4j12", "hsqldb", "grails-gorm", "grails-crud", "groovy-all"
		}
		test("org.seleniumhq.selenium:selenium-htmlunit-driver:2.0a5") {
			excludes "xml-apis"
		}
	}
	plugins {
		runtime ":bean-fields:1.0-RC3"
		runtime ":jquery:1.4.2.5"
		runtime ":jquery-ui:1.8.2.4"
		test(":spock:0.5-groovy-1.7-SNAPSHOT") {
			excludes "spock-grails-support"
		}
		test(":geb:0.5-SNAPSHOT") {
			excludes "geb-junit3", "geb-junit4"
		}
		test ":build-test-data:1.1.1"
	}
}
