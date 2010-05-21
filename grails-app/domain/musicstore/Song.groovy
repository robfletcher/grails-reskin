package musicstore

class Song {
	
	static belongsTo = [artist: Artist]
	String title
	Integer durationSeconds
	Date dateCreated
	Date lastUpdated

    static constraints = {
		artist nullable: false
		title blank: false
    }
}
