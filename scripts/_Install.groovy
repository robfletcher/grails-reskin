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
	ant.input message: "Overwrite $file.name? [y/n]", addProperty: "overwrite.$file.name"
	"y".equalsIgnoreCase(ant.antProject.properties."overwrite.$file.name")
}