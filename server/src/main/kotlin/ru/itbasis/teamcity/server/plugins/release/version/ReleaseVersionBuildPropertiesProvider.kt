package ru.itbasis.teamcity.server.plugins.release.version

import jetbrains.buildServer.log.Loggers
import jetbrains.buildServer.serverSide.SBuild
import jetbrains.buildServer.serverSide.parameters.AbstractBuildParametersProvider
import khronos.toString
import mu.NamedKLogging
import ru.itbasis.teamcity.plugin.release.version.common.CommonBuildFeature
import ru.itbasis.teamcity.plugin.release.version.common.DateFormatTemplates
import ru.itbasis.teamcity.plugin.release.version.common.ReleaseVersionBuildParameters
import java.util.*

open class ReleaseVersionBuildPropertiesProvider : AbstractBuildParametersProvider() {
	companion object : NamedKLogging(Loggers.SERVER_CATEGORY)

	override fun getParameters(build: SBuild, emulationMode: Boolean): MutableMap<String, String> {
		val buildParameters = ReleaseVersionBuildParameters()

		if (build.getBuildFeaturesOfType(CommonBuildFeature.FEATURE_NAME).isEmpty()) {
			return buildParameters.getParams()
		}

		ReleaseVersionServerListener.logger.trace { "build: ${build.fullName}" }
		buildParameters.addEnvironmentAndSystem(CommonBuildFeature.VARIABLE_RELEASE_NAME,
		                                        Calendar.getInstance().time.toString(DateFormatTemplates.ONE_DOT))
		return buildParameters.getParams()
	}
}