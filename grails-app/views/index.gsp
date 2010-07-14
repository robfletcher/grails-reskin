<!DOCTYPE html>
<html>
	<head>
		<title>Welcome to Grails</title>
		<meta name="layout" content="main"/>
		<style type="text/css" media="screen">
		aside {
			background: #ccc;
			border: 3px solid #999;
			-webkit-border-radius: 10px;
			-moz-border-radius: 10px;
			border-radius: 10px;
			margin-top: 20px;
			margin-left: 30px;
			width: 192px;
			float: left;
			padding: 15px; /* the good old double margin float bug in IE6 */
			_margin-left: 15px;
			behavior: url(/css/PIE.htc);
			_padding-top: 0;
		}

		aside#pluginList {
			clear: left;
		}

		aside * {
			margin: 0;
		}

		aside ul {
			list-style-type: none;
		}

		aside header h1 {
			text-transform: uppercase;
			font-size: 1.1em;
			margin-bottom: 10px;
		}

		h2 {
			margin-top: 15px;
			margin-bottom: 15px;
			font-size: 1.2em;
		}

		section.main {
			float: none;
			margin: 0 20px 10px 280px;
		}
		</style>
	</head>
	<body>
		<aside id="status">
			<header>
				<h1>Application Status</h1>
			</header>
			<ul>
				<li>App version: <g:meta name="app.version"/></li>
				<li>Grails version: <g:meta name="app.grails.version"/></li>
				<li>JVM version: ${System.getProperty('java.version')}</li>
				<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
				<li>Domains: ${grailsApplication.domainClasses.size()}</li>
				<li>Services: ${grailsApplication.serviceClasses.size()}</li>
				<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
			</ul>
		</aside>
		<aside id="pluginList">
			<header>
				<h1>Installed Plugins</h1>
			</header>
			<ul>
				<g:set var="pluginManager" value="${applicationContext.getBean('pluginManager')}"></g:set>
				<g:each var="plugin" in="${pluginManager.allPlugins}">
					<li>${plugin.name} - ${plugin.version}</li>
				</g:each>
			</ul>
		</aside>
		<section class="main">
			<header>
				<h1>Welcome to Grails</h1>
			</header>
			<p>Congratulations, you have successfully started your first Grails application! At the moment
			this is the default page, feel free to modify it to either redirect to a controller or display whatever
			content you may choose. Below is a list of controllers that are currently deployed in this application,
			click on each to execute its default action:</p>

			<section id="controllerList">
				<header>
					<h2>Available Controllers:</h2>
				</header>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses}">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</section>
		</section>
	</body>
</html>