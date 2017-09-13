import com.github.rodm.teamcity.TeamCityPluginExtension
import org.gradle.plugins.ide.idea.model.IdeaModel

buildscript {
	val kotlinVersion = property("kotlinVersion") as String

	repositories {
		jcenter()
		maven { url = uri("https://plugins.gradle.org/m2/") }
	}

	dependencies {
		classpath(kotlin("gradle-plugin", kotlinVersion))
		classpath(kotlin("noarg", kotlinVersion))
		classpath(kotlin("allopen", kotlinVersion))

		classpath("com.github.rodm:gradle-teamcity-plugin:1.0")
	}
}

plugins {
	base
	`java-base`
	kotlin("jvm")
}

apply {
	plugin("kotlin-spring")
	plugin("com.github.rodm.teamcity-common")
}


dependencies {
	val kotlinVersion = extra["kotlin.version"] as String

	compile(kotlin("runtime", kotlinVersion))
	compile(kotlin("stdlib-jre8", kotlinVersion))

	compile(group = "io.github.microutils", name = "kotlin-logging", version = "latest.release")
	compile(group = "org.slf4j", name = "slf4j-log4j12", version = "1.7.25") { isTransitive = false }
	compile(group = "org.slf4j", name = "slf4j-api", version = "1.7.25")
}

configure<TeamCityPluginExtension> {
	version = extra["teamcity.version"] as String
}
