package musicstore

class Artist {
	
	String name
	Date dateCreated
	Date lastUpdated

    static constraints = {
		name blank: false, unique: true
    }
}
