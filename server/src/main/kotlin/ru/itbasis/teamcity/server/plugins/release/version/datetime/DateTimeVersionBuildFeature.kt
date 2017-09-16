package ru.itbasis.teamcity.server.plugins.release.version.datetime

import jetbrains.buildServer.agent.Constants.SYSTEM_PREFIX
import jetbrains.buildServer.log.Loggers
import jetbrains.buildServer.serverSide.SBuild
import khronos.toString
import mu.NamedKLogging
import ru.itbasis.teamcity.server.plugins.release.version.AbstractBuildFeature
import java.util.*

class DateTimeVersionBuildFeature : AbstractBuildFeature() {
	companion object : NamedKLogging(Loggers.SERVER_CATEGORY) {
		const val DEFAULT_FORMAT = "YYYYMMddHHmmss"
	}

	override fun buildParameters(build: SBuild): MutableMap<String, String> {
		logger.debug { "sBuild: $build" }

		val now = Calendar.getInstance()
		val dateFormatted = buildParameterValue(build, now, RELEASE_VERSION_FORMAT_PROPERTY)

		return hashMapOf(
			Pair(RELEASE_VERSION_PROPERTY, dateFormatted),
			Pair(convertAsEnvironmentName(RELEASE_VERSION_PROPERTY), dateFormatted),
			Pair(SYSTEM_PREFIX + RELEASE_VERSION_PROPERTY, dateFormatted)
		                )
	}

	private fun buildParameterValue(build: SBuild,
	                                date: Calendar,
	                                formatParameterName: String): String {
		val parametersProvider = build.parametersProvider
		logger.debug { "formatParameterName: $formatParameterName" }

		val buildNumberFormat = parametersProvider.get(formatParameterName)
		                        ?: parametersProvider.get(convertAsEnvironmentName(formatParameterName))
		                        ?: parametersProvider.get(SYSTEM_PREFIX + formatParameterName)
		                        ?: DEFAULT_FORMAT
		logger.debug { "format: $buildNumberFormat" }

		val dateFormatted = date.time.toString(buildNumberFormat)
		logger.debug { "dateFormatted: $dateFormatted" }
		return dateFormatted
	}

	override fun getEditParametersUrl(): String? = null

	override fun getDisplayName(): String = "Date/Time Build Version Generator"

	override fun getType(): String = DateTimeVersionBuildFeature::class.java.simpleName

	override fun isMultipleFeaturesPerBuildTypeAllowed(): Boolean = false

}
