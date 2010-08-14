<!DOCTYPE html>
<% import grails.persistence.Event %>
<%=packageName%>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <nav>
			<ul>
				<li><a class="home" href="\${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
        </nav>
		<section class="list">
			<header>
				<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			</header>
			<g:if test="\${flash.message}">
			<aside class="message">\${flash.message}</aside>
			</g:if>
			<table>
				<thead>
					<tr>
					<%  excludedProps = Event.allEvents.toList() << 'version'
						props = domainClass.properties.findAll { !excludedProps.contains(it.name) && it.type != Set.class }
						Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
						props.eachWithIndex { p, i ->
							if (i < 6) {
								if (p.isAssociation()) { %>
						<th><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></th>
					<%      } else { %>
						<g:sortableColumn property="${p.name}" title="\${message(code: '${domainClass.propertyName}.${p.name}.label', default: '${p.naturalName}')}" />
					<%  }   }   } %>
					</tr>
				</thead>
				<tbody>
				<g:each in="\${${propertyName}List}" status="i" var="${propertyName}">
					<tr class="\${(i % 2) == 0 ? 'odd' : 'even'}">
					<%  props.eachWithIndex { p, i ->
							if (i == 0) { %>
						<td><g:link action="show" id="\${${propertyName}.id}">\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</g:link></td>
					<%      } else if (i < 6) {
								if (org.springframework.context.MessageSourceResolvable.isAssignableFrom(p.type)) { %>
						<td><g:message message="\${${propertyName}?.${p.name}}"/></td>
					<%			} else if (p.type == Boolean.class || p.type == boolean.class) { %>
						<td><g:formatBoolean boolean="\${${propertyName}.${p.name}}" /></td>
					<%          } else if (p.type == Date.class || p.type == java.sql.Date.class || p.type == java.sql.Time.class || p.type == Calendar.class) { %>
						<td><g:formatDate date="\${${propertyName}.${p.name}}" /></td>
					<%          } else { %>
						<td>\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</td>
					<%  }   }   } %>
					</tr>
				</g:each>
				</tbody>
			</table>
			<nav class="paginateButtons">
				<g:paginate total="\${${propertyName}Total}" />
			</nav>
		</section>
    </body>
</html>
