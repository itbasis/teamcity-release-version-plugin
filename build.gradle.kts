import org.gradle.plugins.ide.idea.model.IdeaModel

buildscript {
	val teamcityPluginVersion = property("com.github.rodm.gradle-teamcity-plugin") as String

	repositories {
		jcenter()
		maven("https://plugins.gradle.org/m2/")
	}
	dependencies {
		classpath("com.github.rodm:gradle-teamcity-plugin:${teamcityPluginVersion}")
	}
}

subprojects {
	repositories {
		jcenter()
		maven("http://download.jetbrains.com/teamcity-repository")
	}

	apply {
		plugin("idea")
	}

	configure<IdeaModel> {
		module.isDownloadJavadoc = false
		module.isDownloadSources = false
	}

	tasks.withType(Test::class.java) {
		useTestNG()
	}

	version = null

}
