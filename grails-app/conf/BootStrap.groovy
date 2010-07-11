import org.codehaus.groovy.grails.plugins.web.taglib.JavascriptTagLib

class BootStrap {

	def init = { servletContext ->
		JavascriptTagLib.LIBRARY_MAPPINGS.modernizr = ["modernizr/modernizr-1.5.min"]
		JavascriptTagLib.LIBRARY_MAPPINGS."html5-support" = ["html5-support"]
	}

	def destroy = {
	}
} 