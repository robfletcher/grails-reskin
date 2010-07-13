package grails.plugin.reskin.binding

import java.text.SimpleDateFormat
import org.springframework.beans.PropertyEditorRegistrar
import org.springframework.beans.PropertyEditorRegistry
import org.springframework.beans.propertyeditors.CustomDateEditor
import static org.apache.commons.lang.time.DateUtils.UTC_TIME_ZONE

class Html5PropertyEditorRegistrar implements PropertyEditorRegistrar {

	static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
	static final String DATE_FORMAT = "yyyy-MM-dd"

	void registerCustomEditors(PropertyEditorRegistry registry) {
		def datetimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT)
		datetimeFormat.timeZone = UTC_TIME_ZONE
		registry.registerCustomEditor Date, new CustomDateEditor(datetimeFormat, true)

		registry.registerCustomEditor Date, new CustomDateEditor(new SimpleDateFormat(DATE_FORMAT), true)
	}

}
