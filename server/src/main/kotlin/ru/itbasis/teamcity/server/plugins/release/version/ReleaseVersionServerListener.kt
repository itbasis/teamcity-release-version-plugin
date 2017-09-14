package ru.itbasis.teamcity.server.plugins.release.version

import jetbrains.buildServer.log.Loggers
import jetbrains.buildServer.serverSide.BuildServerAdapter
import jetbrains.buildServer.serverSide.SRunningBuild
import mu.NamedKLogging
import ru.itbasis.teamcity.plugin.release.version.common.CommonBuildFeature

class ReleaseVersionServerListener() : BuildServerAdapter() {
	companion object : NamedKLogging(Loggers.SERVER_CATEGORY)

	override fun buildStarted(build: SRunningBuild) {
		if (build.getBuildFeaturesOfType(CommonBuildFeature.FEATURE_NAME).isEmpty()) {
			return
		}

		logger.trace { "build: ${build.fullName}" }
	}
}