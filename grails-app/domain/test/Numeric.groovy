package test

class Numeric {

	Byte simpleByte
	Integer simpleInt
	Integer intInRange
	Integer intInList
	Integer intWithMin
	Integer intWithMax
	Integer intWithMinAndMax

    static constraints = {
		simpleByte()
		simpleInt()
		intInRange range: 1..10
		intInList inList: [2, 4, 6, 8, 10]
		intWithMin min: 1
		intWithMax max: 10
		intWithMinAndMax min: 1, max: 10
    }
}
