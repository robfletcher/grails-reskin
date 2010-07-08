<!DOCTYPE html>
<% import grails.persistence.Event %>
<%=packageName%>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <nav>
			<ul>
				<li><a class="home" href="\${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
        </nav>
        <article>
			<header>
            	<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			</header>
            <g:if test="\${flash.message}">
            <div class="message">\${flash.message}</div>
            </g:if>
            <section class="dialog">
                <dl>
                    <%  excludedProps = Event.allEvents.toList() << 'version'
                        props = domainClass.properties.findAll { !excludedProps.contains(it.name) }
                        Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                        props.each { p -> %>
						<dt class="name"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></dt>
						<%  if (p.isEnum()) { %>
						<dd class="value">\${${propertyName}?.${p.name}?.encodeAsHTML()}</dd>
						<%  } else if (p.oneToMany || p.manyToMany) { %>
						<dd style="text-align: left;" class="value">
							<ul>
							<g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
								<li><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link></li>
							</g:each>
							</ul>
						</dd>
						<%  } else if (p.manyToOne || p.oneToOne) { %>
						<dd class="value"><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link></dd>
						<%  } else if (p.type == Boolean.class || p.type == boolean.class) { %>
						<dd class="value"><g:formatBoolean boolean="\${${propertyName}?.${p.name}}" /></dd>
						<%  } else if (p.type == Date.class || p.type == java.sql.Date.class || p.type == java.sql.Time.class || p.type == Calendar.class) { %>
						<dd class="value"><g:formatDate date="\${${propertyName}?.${p.name}}" /></dd>
						<%  } else { %>
						<dd class="value">\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</dd>
						<%  } %>
                    <%  } %>
				</dl>
				<fieldset class="buttons">
					<g:form>
						<g:hiddenField name="id" value="\${${propertyName}?.id}" />
						<g:actionSubmit class="edit" action="edit" value="\${message(code: 'default.button.edit.label', default: 'Edit')}" />
						<g:actionSubmit class="delete" action="delete" value="\${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</g:form>
				</fieldset>
			</section>
        </article>
    </body>
</html>
