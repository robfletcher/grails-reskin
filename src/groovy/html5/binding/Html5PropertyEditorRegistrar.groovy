package html5.binding

import org.springframework.beans.PropertyEditorRegistrar
import org.springframework.beans.PropertyEditorRegistry
import org.springframework.beans.propertyeditors.CustomDateEditor
import java.text.SimpleDateFormat

class Html5PropertyEditorRegistrar implements PropertyEditorRegistrar {

	void registerCustomEditors(PropertyEditorRegistry registry) {
		def datetimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
		datetimeFormat.timeZone = TimeZone.getTimeZone("UTC")
		registry.registerCustomEditor Date, new CustomDateEditor(datetimeFormat, true)
	}

}
