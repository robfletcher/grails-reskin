<!DOCTYPE html>
<html>
	<head>
		<title>Welcome to Grails</title>
		<meta name="layout" content="main"/>
		<style type="text/css" media="screen">
		aside {
			margin-top: 20px;
			margin-left: 30px;
			width: 228px;
			float: left;
		}

		aside section * {
			margin: 0;
		}

		aside section ul {
			list-style-type: none;
			margin-bottom: 10px;
		}

		aside section h1 {
			text-transform: uppercase;
			font-size: 1.1em;
			margin-bottom: 10px;
		}

		aside section {
			background: url(/images/leftnav_midstretch.png) repeat-y top;
			margin: 0;
			padding: 0 15px;
		}

		aside section:first-of-type {
			padding: 15px 15px 10px;
		}

		aside section:last-of-type {
			padding: 0 15px 15px;
		}

		aside .panelBtm {
			background: url(/images/leftnav_btm.png) no-repeat top;
			height: 20px;
			margin: 0;
		}

		aside .panelTop {
			background: url(/images/leftnav_top.png) no-repeat top;
			height: 11px;
			margin: 0;
		}

		h2 {
			margin-top: 15px;
			margin-bottom: 15px;
			font-size: 1.2em;
		}

		article {
			float: none;
			margin: 0 20px 10px 280px;
		}
		</style>
	</head>
	<body>
		<aside>
			<div class="homePagePanel">
				<div class="panelTop"></div>
				<section id="status">
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
				</section>
				<section id="pluginList">
					<header>
						<h1>Installed Plugins</h1>
					</header>
					<ul>
						<g:set var="pluginManager" value="${applicationContext.getBean('pluginManager')}"></g:set>
						<g:each var="plugin" in="${pluginManager.allPlugins}">
							<li>${plugin.name} - ${plugin.version}</li>
						</g:each>
					</ul>
				</section>
				<div class="panelBtm"></div>
			</div>
		</aside>
		<article>
			<header>
				<h1>Welcome to Grails</h1>
			</header>
			<p>Congratulations, you have successfully started your first Grails application! At the moment
			this is the default page, feel free to modify it to either redirect to a controller or display whatever
			content you may choose. Below is a list of controllers that are currently deployed in this application,
			click on each to execute its default action:</p>

			<section id="controllerList" class="dialog">
				<header>
					<h2>Available Controllers:</h2>
				</header>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses}">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</section>
		</article>
	</body>
</html>