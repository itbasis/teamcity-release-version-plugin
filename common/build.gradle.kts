import com.github.rodm.teamcity.TeamCityPluginExtension
import org.gradle.kotlin.dsl.extra
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	repositories {
		jcenter()
		maven("https://plugins.gradle.org/m2/")
	}

	dependencies {
		classpath(kotlin("gradle-plugin"))
		classpath(kotlin("noarg"))
		classpath(kotlin("allopen"))

		classpath("com.github.rodm:gradle-teamcity-plugin:1.0")
	}
}

val javaVersion: String by extra
val teamcityVersion: String by extra

plugins {
	base
	`java-base`
	kotlin("jvm")
}

apply {
	plugin("kotlin-spring")
	plugin("com.github.rodm.teamcity-common")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = javaVersion
}

dependencies {
	compile(kotlin("runtime"))
	compile(kotlin("stdlib-jre8"))

	compile(group = "io.github.microutils", name = "kotlin-logging", version = "latest.release")
	compile(group = "org.slf4j", name = "slf4j-log4j12", version = "1.7.25") { isTransitive = false }
	compile(group = "org.slf4j", name = "slf4j-api", version = "1.7.25")
}

configure<TeamCityPluginExtension> {
	version = teamcityVersion
}
