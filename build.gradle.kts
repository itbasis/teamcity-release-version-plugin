import org.gradle.api.internal.artifacts.Module
import org.gradle.jvm.tasks.Jar
import org.gradle.plugins.ide.idea.model.IdeaModel

buildscript {
	val teamcityPluginVersion = property("com.github.rodm.gradle-teamcity-plugin") as String

	repositories {
		jcenter()
		maven { url = uri("https://plugins.gradle.org/m2/") }
	}
	dependencies {
		classpath("com.github.rodm:gradle-teamcity-plugin:${teamcityPluginVersion}")
	}
}

subprojects {
	extra["kotlin.version"] = rootProject.property("kotlinVersion") as String
	extra["teamcity.version"] = rootProject.property("teamcityVersion") as String

	repositories {
		jcenter()
		maven { url = uri("http://download.jetbrains.com/teamcity-repository") }
	}

	apply {
		plugin("idea")
	}

	configure<IdeaModel> {
		module.isDownloadJavadoc = false
		module.isDownloadSources = false
	}

	tasks.withType(Test::class.java){
		useTestNG()
	}

	version = null
}