import org.gradle.api.internal.artifacts.Module
import org.gradle.jvm.tasks.Jar
import org.gradle.plugins.ide.idea.model.IdeaModel

buildscript {
	val teamcityPluginVersion = property("com.github.rodm.gradle-teamcity-plugin") as String
//	val kotlinVersion = property("kotlinVersion") as String

	repositories {
		jcenter()
		maven { url = uri("https://plugins.gradle.org/m2/") }
	}
	dependencies {
		classpath("com.github.rodm:gradle-teamcity-plugin:${teamcityPluginVersion}")
//		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
	}
}

val javaVersion: String by extra

subprojects {

	extra["kotlin.version"] = rootProject.property("kotlinVersion") as String
	extra["teamcity.version"] = rootProject.property("teamcityVersion") as String

	repositories {
		jcenter()
		maven { url = uri("http://download.jetbrains.com/teamcity-repository") }
	}

//	plugins{
//		id("org.jetbrains.kotlin.jvm") version extra["kotlin.version"] as String
//	}
	apply {
		plugin("idea")
//		plugin(kotlin("jvm", extra["kotlin.version"] as String))
//		plugin("org.jetbrains.kotlin.jvm", extra["kotlin.version"])
	}


//	plugins.apply("org.jetbrains.kotlin.jvm")

	configure<IdeaModel> {
		module.isDownloadJavadoc = false
		module.isDownloadSources = false
	}

//	tasks.withType<KotlinCompile> {
//		kotlinOptions.jvmTarget = javaVersion
//	}

	tasks.withType(Test::class.java) {
		useTestNG()
	}

	version = null

}
