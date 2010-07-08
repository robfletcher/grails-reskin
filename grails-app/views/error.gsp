<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title>Grails Runtime Exception</title>
		<style type="text/css">
		header, article, section, aside, nav {
			display: block;
		}

		section.message {
			border: 1px solid black;
			padding: 5px;
			background-color: #E9E9E9;
		}

		section.stack {
			border: 1px solid black;
			padding: 5px;
			overflow: auto;
			height: 300px;
		}

		section.snippet {
			padding: 5px;
			background-color: white;
			border: 1px solid black;
			margin: 3px;
			font-family: courier;
		}
		</style>
	</head>

	<body>
		<header>
			<hgroup>
				<h1>Grails Runtime Exception</h1>
				<h2>Error Details</h2>
			</hgroup>
		</header>

		<section class="message">
			<strong>Error ${request.'javax.servlet.error.status_code'}:</strong> ${request.'javax.servlet.error.message'.encodeAsHTML()}<br/>
			<strong>Servlet:</strong> ${request.'javax.servlet.error.servlet_name'}<br/>
			<strong>URI:</strong> ${request.'javax.servlet.error.request_uri'}<br/>
			<g:if test="${exception}">
				<strong>Exception Message:</strong> ${exception.message?.encodeAsHTML()} <br/>
				<strong>Caused by:</strong> ${exception.cause?.message?.encodeAsHTML()} <br/>
				<strong>Class:</strong> ${exception.className} <br/>
				<strong>At Line:</strong> [${exception.lineNumber}] <br/>
				<strong>Code Snippet:</strong><br/>
				<section class="snippet">
					<g:each var="cs" in="${exception.codeSnippet}">
						${cs?.encodeAsHTML()}<br/>
					</g:each>
				</section>
			</g:if>
		</section>
		<g:if test="${exception}">
			<section class="exception">
				<header>
					<h2>Stack Trace</h2>
				</header>
				<section class="stack">
				<pre><g:each in="${exception.stackTraceLines}">${it.encodeAsHTML()}<br/></g:each></pre>
				</section>
			</section>
		</g:if>
	</body>
</html>