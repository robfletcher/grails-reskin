/*
 * Copyright 2010 Rob Fletcher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import grails.plugin.reskin.binding.Html5PropertyEditorRegistrar
import org.codehaus.groovy.grails.plugins.web.taglib.JavascriptTagLib

class ReskinGrailsPlugin {

	def version = "1.0"
	def grailsVersion = "1.2.0 > *"
	def dependsOn = [
			beanFields: "1.0-RC3",
			jqueryUi: "1.8.2.4"
	]
	def pluginExcludes = [
			"grails-app/controllers/**",
			"grails-app/domain/**",
			"grails-app/i18n/**",
			"web-app/WEB-INF/**"
	]

	def author = "Rob Fletcher"
	def authorEmail = "rob@energizedwork.com"
	def title = "Reskin Plugin"
	def description = '''\\
Reskins out-of-the-box Grails using HTML5 and semantic markup.
'''
	def documentation = "http://robfletcher.github.com/grails-reskin"

	def doWithWebDescriptor = {xml ->
	}

	def doWithSpring = {
		html5PropertyEditorRegistrar(Html5PropertyEditorRegistrar)
	}

	def doWithDynamicMethods = {ctx ->
		JavascriptTagLib.LIBRARY_MAPPINGS.modernizr = ["modernizr/modernizr-1.5.min"]
		JavascriptTagLib.LIBRARY_MAPPINGS."html5-support" = ["html5-support"]
	}

	def doWithApplicationContext = {applicationContext ->
	}

	def onChange = {event ->
	}

	def onConfigChange = {event ->
	}
}
