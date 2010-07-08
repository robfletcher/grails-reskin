package test

class ErrorController {

    def index = {
		throw new IllegalStateException("Deliberate exception")
	}
}
