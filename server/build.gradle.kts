import com.github.rodm.teamcity.*
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

		classpath("com.github.rodm:gradle-teamcity-plugin:0.11")
	}
}

val javaVersion: String by extra
val teamcityVersion: String by extra
val vcsUrl: String by extra

plugins {
	base
	`java-base`
	kotlin("jvm")
}

apply {
	plugin("kotlin-spring")

	plugin("com.github.rodm.teamcity-server")
}

dependencies {
	compile(project(":common"))

	compile(group = "com.github.hotchemi", name = "khronos", version = "0.9.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = javaVersion
}

configure<TeamCityPluginExtension> {
	version = teamcityVersion

	server(closureOf<ServerPluginConfiguration> {
		descriptor(closureOf<ServerPluginDescriptor> {
			name = property("plugin.name") as String
			displayName = property("plugin.name") as String
			version = rootProject.version as String
			vendorName = property("developerName") as String
			email = property("developerEmail") as String
			description = rootProject.description
			useSeparateClassloader = true
			downloadUrl = vcsUrl
		})
	})
}