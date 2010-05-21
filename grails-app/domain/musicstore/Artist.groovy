package musicstore

class Artist {
	
	String name
	Date dateCreated
	Date lastUpdated

    static constraints = {
		name blank: false, unique: true
    }

	boolean equals(Object o) {
		if (o == null) return false
		if (o.is(this)) return true
		if (!(o instanceof Artist)) return false
		return name == o.name
	}
	
	int hashCode() {
		name.hashCode()
	}

	String toString() {
		name
	}
}
