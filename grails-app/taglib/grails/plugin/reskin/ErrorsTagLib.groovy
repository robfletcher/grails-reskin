package grails.plugin.reskin

import org.springframework.validation.Errors

class ErrorsTagLib {

	def renderGlobalErrors = { attrs ->
		if (!attrs.bean) throwTagError("attribute 'bean' is required")

		def codec = attrs.codec ?: "HTML"
		if (codec == "none") codec = ""

		Errors errors = attrs.bean.errors

		if (errors.hasGlobalErrors()) {
			out << "<ul>"
			out << errors.globalErrors.each {
				out << "<li>${message(error: it, encodeAs: codec)}</li>"
			}
			out << "</ul>"
		}
	}

	def hasGlobalErrors = { attrs, body ->
		if (!attrs.bean) throwTagError("attribute 'bean' is required")

		Errors errors = attrs.bean.errors

		if (errors.hasGlobalErrors()) {
			out << body()
		}
	}

}
