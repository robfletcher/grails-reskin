package test

import org.apache.commons.lang.WordUtils
import org.springframework.context.MessageSourceResolvable

class Person {

	Title title
	String name
	String password
	Date birthdate
	Gender gender
	String email
	URL website
	Person spouse

	static constraints = {
		title nullable: true
		name blank: false, unique: true
		password blank: false, password: true
		birthdate nullable: true
		gender()
		email nullable: true, email: true
		website nullable: true
		spouse nullable: true
	}

	String toString() {
		name
	}
}

enum Title implements MessageSourceResolvable {
	MR, MRS, MS, DR

	String[] getCodes() {
		["${getClass().name}.${name()}"] as String[]
	}

	Object[] getArguments() {
		[] as Object[]
	}

	String getDefaultMessage() {
		use(WordUtils) {
			name().toLowerCase().replaceAll(/_+/, " ").capitalizeFully()
		}
	}
}

enum Gender implements MessageSourceResolvable {
	MALE, FEMALE

	String[] getCodes() {
		["${getClass().name}.${name()}"] as String[]
	}

	Object[] getArguments() {
		[] as Object[]
	}

	String getDefaultMessage() {
		use(WordUtils) {
			name().toLowerCase().replaceAll(/_+/, " ").capitalizeFully()
		}
	}
}
