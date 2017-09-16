package ru.itbasis.teamcity.server.plugins.release.version

import jetbrains.buildServer.agent.Constants
import jetbrains.buildServer.log.Loggers
import jetbrains.buildServer.serverSide.BuildFeature
import jetbrains.buildServer.serverSide.SBuild
import jetbrains.buildServer.util.StringUtils
import mu.NamedKLogging

abstract class AbstractBuildFeature : BuildFeature() {
	companion object : NamedKLogging(Loggers.SERVER_CATEGORY) {
		const val RELEASE_VERSION_PROPERTY = "release.version"
		const val RELEASE_VERSION_FORMAT_PROPERTY = "release.version.format"
	}

	abstract fun buildParameters(build: SBuild): MutableMap<String, String>

	protected fun convertAsEnvironmentName(parameterName: String): String {
		return Constants.ENV_PREFIX + StringUtils.replaceNonAlphaNumericChars(parameterName, '_').toUpperCase()
	}
}