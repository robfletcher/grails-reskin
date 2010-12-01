<%@ page import="grails.util.GrailsUtil" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
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


<body>

  <div id="container">
    <header>

    </header>

    <div id="main">

    </div>

    <footer>

    </footer>
  </div> <!--! end of #container -->


  <!-- Javascript at the bottom for fast page loading -->

  <!-- Grab Google CDN's jQuery. fall back to local if necessary -->
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.js"></script>
  <script>!window.jQuery && document.write(unescape('%3Cscript src="js/libs/jquery-1.4.2.js"%3E%3C/script%3E'))</script>


  <!-- scripts concatenated and minified via ant build script-->
  <script src="js/plugins.js"></script>
  <script src="js/script.js"></script>
  <!-- end concatenated and minified scripts-->


  <!--[if lt IE 7 ]>
    <script src="js/libs/dd_belatedpng.js"></script>
    <script> DD_belatedPNG.fix('img, .png_bg'); //fix any <img> or .png_bg background-images </script>
  <![endif]-->

  <!-- yui profiler and profileviewer - remove for production -->
  <script src="js/profiling/yahoo-profiling.min.js"></script>
  <script src="js/profiling/config.js"></script>
  <!-- end profiling code -->


  <!-- asynchronous google analytics: mathiasbynens.be/notes/async-analytics-snippet
       change the UA-XXXXX-X to be your site's ID -->
  <script>
   var _gaq = [['_setAccount', 'UA-XXXXX-X'], ['_trackPageview']];
   (function(d, t) {
    var g = d.createElement(t),
        s = d.getElementsByTagName(t)[0];
    g.async = true;
    g.src = ('https:' == location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    s.parentNode.insertBefore(g, s);
   })(document, 'script');
  </script>

</body>
</html>