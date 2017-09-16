package ru.itbasis.teamcity.server.plugins.release.version

import jetbrains.buildServer.log.Loggers
import jetbrains.buildServer.serverSide.SBuild
import jetbrains.buildServer.serverSide.parameters.AbstractBuildParametersProvider
import mu.NamedKLogging
import org.springframework.beans.factory.annotation.Autowired

open class ReleaseVersionBuildPropertiesProvider @Autowired constructor(
	private val buildFeatures: List<AbstractBuildFeature>
                                                                       ) : AbstractBuildParametersProvider() {

	companion object : NamedKLogging(Loggers.SERVER_CATEGORY)

	override fun getParameters(build: SBuild, emulationMode: Boolean): MutableMap<String, String> {
		for (buildFeature in buildFeatures) {
			logger.debug { "build params from buildFeature: ${buildFeature.javaClass}" }

			val buildFeaturesOfType = build.getBuildFeaturesOfType(buildFeature.type)
			logger.debug { "found count: ${buildFeaturesOfType.size}" }
			if (buildFeaturesOfType.isNotEmpty()) {
				return buildFeature.buildParameters(build)
			}
		}

		return hashMapOf()
	}

}