<%@ page import="grails.util.GrailsUtil" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'basic.css')}">
		<g:layoutHead/>
		<g:javascript src="enhancejs/enhance.min.js"/>
		<g:javascript>
			enhance({
				loadStyles: [
					'${resource(dir: 'css', file: 'boilerplate.css')}',
					'${resource(dir: 'css', file: 'main.css')}',
					'${resource(dir: 'jquery-ui/themes/smoothness', file: 'jquery-ui-1.8.6.custom.css', plugin: 'jquery-ui')}'
				],
				loadScripts: [
					'${resource(dir: 'js/modernizr', file: 'modernizr-1.6.min.js')}',
					'${resource(dir: 'js/jquery', file: 'jquery-1.4.3.min.js', plugin: 'jquery')}',
					'${resource(dir: 'jquery-ui/js', file: 'jquery-ui-1.8.6.custom.min.js', plugin: 'jquery-ui')}',
					{href: '${resource(dir: 'js/belatedpng', file: 'dd_belatedpng.js')}', iecondition: 6},
					'${resource(dir: 'js', file: 'application.js')}'
				],
				queueLoading: false,
				forcePassText: "${message(code: 'enhanced.version.label', default: 'Enhanced version')}",
				forceFailText: "${message(code: 'basic.version.label', default: 'Basic version')}"
			});
		</g:javascript>
		<nav:resources override="true"/>
	</head>
	<body>
		<div id="header">
			<a href="http://grails.org" class="logo"><img src="${resource(dir: 'images', file: 'logo.png')}" alt=""/></a>
			<div class="nav">
				<nav:render group="main"/>
				<g:if test="${controllerName}"><nav:renderSubItems group="main"/></g:if>
			</div>
		</div>
		<div id="main">
			<g:layoutBody/>
		</div>
		<div id="footer">
		</div>
	</body>
</html>
