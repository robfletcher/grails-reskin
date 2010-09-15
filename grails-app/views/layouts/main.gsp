<!DOCTYPE html>
<html class="no-js">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<%-- <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}"> --%>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'handheld.css')}" media="handheld">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'PIE.htc')}">
		<g:layoutHead/>
		<g:javascript library="modernizr"/>
		<g:javascript library="jquery" plugin="jquery"/>
		<jqui:resources components="datepicker" mode="normal" />
	</head>
	<body>
		<div id="container">
			<header>
				<a href="http://grails.org" id="grailsLogo" class="logo"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a>
			</header>
			<div id="main">
				<g:layoutBody/>
			</div>
			<footer>
			</footer>
		</div>
		<g:javascript src="application.js"/>
		<g:javascript src="plugins.js"/>
		<!--[if lt IE 7 ]>
			<g:javascript library="belatedpng"/>
		<![endif]-->
	</body>
</html>