import org.codehaus.groovy.grails.plugins.web.taglib.JavascriptTagLib

class BootStrap {

	def init = { servletContext ->
		JavascriptTagLib.LIBRARY_MAPPINGS.modernizr = ["modernizr/modernizr-1.6.min"]
		JavascriptTagLib.LIBRARY_MAPPINGS.belatedpng = ["belatedpng/dd_belatedpng"]
	}

	def destroy = {
	}
} 