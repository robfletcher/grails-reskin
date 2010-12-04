<!DOCTYPE html>
<% import grails.persistence.Event %>
<%=packageName%>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
		<div class="section">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="\${flash.message}">
            <div class="message">\${flash.message}</div>
            </g:if>
			<dl>
				<%  excludedProps = Event.allEvents.toList() << 'version'
					props = domainClass.properties.findAll { !excludedProps.contains(it.name) && !domainClass.constrainedProperties[it.name]?.password }
					Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
					props.each { p -> %>
					<g:if test="\${fieldValue(bean: ${propertyName}, field: '${p.name}')}">
						<dt><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></dt>
						<%  if (org.springframework.context.MessageSourceResolvable.isAssignableFrom(p.type)) { %>
						<dd><g:message message="\${${propertyName}?.${p.name}}"/></dd>
						<%  } else if (p.isEnum()) { %>
						<dd>\${${propertyName}?.${p.name}?.encodeAsHTML()}</dd>
						<%  } else if (p.oneToMany || p.manyToMany) { %>
						<dd style="text-align: left;">
							<ul>
							<g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
								<li><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link></li>
							</g:each>
							</ul>
						</dd>
						<%  } else if (p.manyToOne || p.oneToOne) { %>
						<dd><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link></dd>
						<%  } else if (p.type == Boolean.class || p.type == boolean.class) { %>
						<dd><g:formatBoolean boolean="\${${propertyName}?.${p.name}}" /></dd>
						<%  } else if (p.type == Date.class || p.type == java.sql.Date.class || p.type == java.sql.Time.class || p.type == Calendar.class) { %>
						<dd><g:formatDate date="\${${propertyName}?.${p.name}}" /></dd>
						<%  } else { %>
						<dd>\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</dd>
						<%  } %>
					</g:if>
				<%  } %>
			</dl>
			<fieldset class="buttons">
				<g:form>
					<g:hiddenField name="id" value="\${${propertyName}?.id}" />
					<g:actionSubmit class="edit" action="edit" value="\${message(code: 'default.button.edit.label', default: 'Edit')}" />
					<g:actionSubmit class="delete" action="delete" value="\${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</g:form>
			</fieldset>
		</div>
    </body>
</html>
