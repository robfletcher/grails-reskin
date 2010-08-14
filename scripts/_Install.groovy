def scaffoldingSourceDir = new File(reskinPluginDir, "src/templates/scaffolding")
def scaffoldingTargetDir = new File(basedir, "src/templates/scaffolding")

event "StatusUpdate", ["Installing scaffolding templates"]
scaffoldingTargetDir.mkdirs()
scaffoldingSourceDir.eachFile { File sourceFile ->
	def targetFile = new File(scaffoldingTargetDir, sourceFile.name)
	if (!targetFile.exists() || userChoosesToOverwrite(targetFile)) {
		ant.copy file: sourceFile, todir: scaffoldingTargetDir, overwrite: true
	}
}

boolean userChoosesToOverwrite(File file) {
	if (ant.antProject.properties."overwrite.all" == "true") return true
	ant.input message: "Overwrite $file.name? [y/n/a]", addProperty: "overwrite.$file.name"
	if ("a".equalsIgnoreCase(ant.antProject.properties."overwrite.$file.name")) {
		ant.property name: "overwrite.all", value: "true"
		return true
	}
	"y".equalsIgnoreCase(ant.antProject.properties."overwrite.$file.name")
}