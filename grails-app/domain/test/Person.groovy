package test

class Person {

	String name
	Date dateOfBirth
	String email
	URL website
	Person spouse
	Integer numberOfChildren

    static constraints = {
		name blank: false, unique: true
		dateOfBirth nullable: true
		email nullable: true, email: true
		website nullable: true
		spouse nullable: true
		numberOfChildren min: 0
    }
}
