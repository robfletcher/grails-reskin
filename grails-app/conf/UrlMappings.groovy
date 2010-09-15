class UrlMappings {
	static mappings = {
		"/$controller/$action?/$id?" {
			constraints {
				// apply constraints here
			}
		}
    	"/" view: "/index"
		"404" view: "/notfound"
		"500" view: "/error"
	}
}
