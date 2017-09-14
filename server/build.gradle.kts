import com.github.rodm.teamcity.*
import org.gradle.plugins.ide.idea.model.IdeaModel
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	val kotlinVersion = rootProject.property("kotlinVersion") as String

	repositories {
		jcenter()
		maven { url = uri("https://plugins.gradle.org/m2/") }
	}
	dependencies {
		classpath(kotlin("gradle-plugin", kotlinVersion))
		classpath(kotlin("noarg", kotlinVersion))
		classpath(kotlin("allopen", kotlinVersion))

		classpath("com.github.rodm:gradle-teamcity-plugin:0.11")
	}
}

val javaVersion: String by extra
val teamcityVersion = rootProject.property("teamcityVersion") as String

repositories {
	jcenter()
	maven { url = uri("http://download.jetbrains.com/teamcity-repository") }
}

plugins {
	base
	`java-base`
	kotlin("jvm", "1.1.4-3")
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
			downloadUrl = "https://github.com/itbasis/teamcity-release-version-plugin"
		})
	})
}

fun Project.teamcity(configuration: TeamCityPluginExtension.() -> Unit) {
	configure(configuration)
}
