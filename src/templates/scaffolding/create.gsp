<!DOCTYPE html>
<% import grails.persistence.Event %>
<% import org.codehaus.groovy.grails.plugins.PluginManagerHolder %>
<%=packageName%>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <nav>
			<ul>
				<li><a class="home" href="\${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
        </nav>
        <article>
			<header>
            	<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			</header>
            <g:if test="\${flash.message}">
            <div class="message">\${flash.message}</div>
            </g:if>
            <g:hasErrors bean="\${${propertyName}}">
            <div class="errors">
                <g:renderErrors bean="\${${propertyName}}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
                <section class="dialog">
                    <fieldset>
						<ul>
                        <%  excludedProps = Event.allEvents.toList() << 'version' << 'id' << 'dateCreated' << 'lastUpdated'
                            props = domainClass.properties.findAll { !excludedProps.contains(it.name) }
                            Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                            display = true
                            boolean hasHibernate = PluginManagerHolder.pluginManager.hasGrailsPlugin('hibernate')
                            props.each { p ->
                                if (!Collection.class.isAssignableFrom(p.type)) {
									if (hasHibernate) {
										cp = domainClass.constrainedProperties[p.name]
										display = (cp ? cp.display : true)
									}
                                    if (display) { %>
                            <li class="prop \${hasErrors(bean: ${propertyName}, field: '${p.name}', 'errors')}">
								<label for="${p.name}"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></label>
								${renderEditor(p)}
                            </li>
                        <%  }   }   } %>
						</ul>
                    </fieldset>
					<fieldset class="buttons">
						<g:submitButton name="create" class="save" value="\${message(code: 'default.button.create.label', default: 'Create')}" />
					</fieldset>
                </section>
            </g:form>
        </article>
    </body>
</html>
