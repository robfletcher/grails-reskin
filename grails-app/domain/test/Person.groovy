package test

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

enum Title {
	MR, MRS, MS, DR
}

enum Gender {
	MALE, FEMALE
}
