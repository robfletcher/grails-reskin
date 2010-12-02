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
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'handheld.css')}" media="handheld">
		<g:layoutHead/>
		<g:javascript library="modernizr"/>
		<g:javascript library="jquery" plugin="jquery"/>
		<jqui:resources components="datepicker" mode="normal"/>
		<nav:resources override="true"/>
	</head>
	<body>
		<div id="container">
			<header>
				<a href="http://grails.org" id="grailsLogo" class="logo"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a>
				<nav>
					<ul>
						<li class="${request.requestURI == '/' ? 'active' : ''}">
							<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
						</li>
						<nav:eachItem var="item">
							<li class="${item.active ? 'active' : ''}">
								<g:link controller="${item.controller}" action="${item.action}">${item.title}</g:link>
								<g:if test="${item.active}">
									<ul>
										<nav:eachSubItem var="subItem">
											<li class="${subItem.active ? 'active' : ''}">
												<g:link controller="${subItem.controller}" action="${subItem.action}">${subItem.title}</g:link>
											</li>
										</nav:eachSubItem>
									</ul>
								</g:if>
							</li>
						</nav:eachItem>
					</ul>
				</nav>
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
