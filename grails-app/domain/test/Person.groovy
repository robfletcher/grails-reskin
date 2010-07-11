package test

class Person {

	String name
	String password
	Date birthdate
	Gender gender
	String email
	URL website
	Person spouse

    static constraints = {
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

enum Gender {
	MALE, FEMALE
}
