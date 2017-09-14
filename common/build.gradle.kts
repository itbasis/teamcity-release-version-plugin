import com.github.rodm.teamcity.TeamCityPluginExtension
import org.gradle.kotlin.dsl.extra
import org.gradle.plugins.ide.idea.model.IdeaModel
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

val javaVersion: String by extra

plugins {
	base
	`java-base`
	kotlin("jvm", "1.1.4-3")
}

apply {
	plugin("kotlin-spring")
	plugin("com.github.rodm.teamcity-common")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = javaVersion
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
