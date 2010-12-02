package test

class PersonController {

	static scaffold = true
	static navigation = [
			group: "main",
			order: 1,
			subItems: ["list", "create"]
	]

}
