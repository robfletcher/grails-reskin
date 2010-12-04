<!DOCTYPE html>
<% import grails.persistence.Event %>
<% import org.codehaus.groovy.grails.plugins.PluginManagerHolder %>
<%=packageName%>
<bean:requiredIndicator>required</bean:requiredIndicator>
<bean:inputTemplate>\${label}\${field}<g:if test="\${errors}">\${errors}</g:if></bean:inputTemplate>
<bean:customTemplate>\${label}\${field}<g:if test="\${errors}">\${errors}</g:if></bean:customTemplate>
<bean:selectTemplate>\${label}\${field}<g:if test="\${errors}">\${errors}</g:if></bean:selectTemplate>
<bean:labelTemplate><label for="\${fieldId}" class="\${errorClassToUse} \${required}">\${label}</label></bean:labelTemplate>
<bean:errorTemplate><div class="errorMessage">\${message.encodeAsHTML()}</div></bean:errorTemplate>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
		<div class="section">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="\${flash.message}">
			<div class="message">\${flash.message}</div>
			</g:if>
			<g:hasGlobalErrors bean="\${${propertyName}}">
			<div class="errors">
				<g:renderGlobalErrors bean="\${${propertyName}}" />
			</div>
			</g:hasGlobalErrors>
			<g:form method="post" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
				<g:hiddenField name="id" value="\${${propertyName}?.id}" />
				<g:hiddenField name="version" value="\${${propertyName}?.version}" />
					<ul>
					<%  excludedProps = Event.allEvents.toList() << 'version' << 'id' << 'dateCreated' << 'lastUpdated'
						props = domainClass.properties.findAll { !excludedProps.contains(it.name) }
						Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
						display = true
						boolean hasHibernate = PluginManagerHolder.pluginManager.hasGrailsPlugin('hibernate')
						props.each { p ->
							if (hasHibernate) {
								cp = domainClass.constrainedProperties[p.name]
								display = (cp?.display ?: true)
							}
							if (display) { %>
						<li>${renderEditor(p)}</li>
						<%  }   } %>
					</ul>
					<fieldset class="buttons">
						<g:actionSubmit class="save" action="update" value="\${message(code: 'default.button.update.label', default: 'Update')}" />
						<g:actionSubmit class="delete" action="delete" value="\${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
			</g:form>
		</div>
    </body>
</html>
