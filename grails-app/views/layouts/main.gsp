<!DOCTYPE html>
<html class="no-js">
	<head>
		<title><g:layoutTitle default="Grails"/></title>
		<meta charset="UTF-8"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"/>
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
		<g:layoutHead/>
		<g:javascript library="modernizr"/>
		<g:javascript library="jquery" plugin="jquery"/>
		<jqui:resources components="datepicker" mode="normal" />
		<g:javascript library="html5-support"/>
		<g:javascript library="application"/>
	</head>
	<body>
		<div id="spinner" class="spinner" style="display:none;">
			<img src="${resource(dir: 'images', file: 'spinner.gif')}" alt="Spinner"/>
		</div>
		<header>
			<a href="http://grails.org" id="grailsLogo" class="logo"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails" border="0"/></a>
		</header>
		<g:layoutBody/>
	</body>
</html>