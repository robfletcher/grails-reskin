<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta charset="UTF-8"/>
		<title>Grails Runtime Exception</title>
		<style type="text/css">
		.message {
			border: 1px solid black;
			padding: 5px;
			background-color: #E9E9E9;
		}

		.stack {
			border: 1px solid black;
			padding: 5px;
			overflow: auto;
			height: 300px;
		}

		.snippet {
			padding: 5px;
			background-color: white;
			border: 1px solid black;
			margin: 3px;
			font-family: courier, monospace;
		}
		</style>
	</head>

	<body>
		<div class="header">
			<h1>Grails Runtime Exception</h1>
		</div>

		<div class="section message">
			<h2>Error Details</h2>
			<strong>Error ${request.'javax.servlet.error.status_code'}:</strong> ${request.'javax.servlet.error.message'.encodeAsHTML()}<br/>
			<strong>Servlet:</strong> ${request.'javax.servlet.error.servlet_name'}<br/>
			<strong>URI:</strong> ${request.'javax.servlet.error.request_uri'}<br/>
			<g:if test="${exception}">
				<strong>Exception Message:</strong> ${exception.message?.encodeAsHTML()} <br/>
				<strong>Caused by:</strong> ${exception.cause?.message?.encodeAsHTML()} <br/>
				<strong>Class:</strong> ${exception.className} <br/>
				<strong>At Line:</strong> [${exception.lineNumber}] <br/>
				<strong>Code Snippet:</strong><br/>
				<div class="section snippet">
					<g:each var="cs" in="${exception.codeSnippet}">
						${cs?.encodeAsHTML()}<br/>
					</g:each>
				</div>
			</g:if>
		</div>
		<g:if test="${exception}">
			<div class="section exception">
				<h2>Stack Trace</h2>
				<div class="section stack">
					<pre><g:each in="${exception.stackTraceLines}">${it.encodeAsHTML()}<br/></g:each></pre>
				</div>
			</div>
		</g:if>
	</body>
</html>