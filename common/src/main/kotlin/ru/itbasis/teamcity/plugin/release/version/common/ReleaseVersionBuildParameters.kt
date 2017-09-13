package ru.itbasis.teamcity.plugin.release.version.common

import jetbrains.buildServer.util.StringUtils.replaceNonAlphaNumericChars
import org.jetbrains.annotations.NotNull

class ReleaseVersionBuildParameters {
	private val params: MutableMap<String, String> = hashMapOf()

	@Suppress("unused")
	fun addConfiguration(@NotNull name: String, @NotNull value: String) {
		params[name] = value
	}

	@Suppress("MemberVisibilityCanPrivate")
	fun addSystem(@NotNull name: String, @NotNull value: String) {
		params["system.$name"] = value
	}

	@Suppress("MemberVisibilityCanPrivate")
	fun addEnvironment(@NotNull name: String, @NotNull value: String) {
		val convertedName = replaceNonAlphaNumericChars(name.toUpperCase(), '_')
		params["env.$convertedName"] = value
	}

	fun addEnvironmentAndSystem(@NotNull name: String, @NotNull value: String) {
		addSystem(name, value)
		addEnvironment(name, value)
	}

	fun getParams(): MutableMap<String, String> = params
}