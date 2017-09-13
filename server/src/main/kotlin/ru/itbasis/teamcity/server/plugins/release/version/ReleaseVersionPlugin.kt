package ru.itbasis.teamcity.server.plugins.release.version

import jetbrains.buildServer.log.Loggers
import jetbrains.buildServer.serverSide.SBuildServer
import jetbrains.buildServer.serverSide.ServerExtensionHolder
import mu.NamedKLogging
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import ru.itbasis.teamcity.plugin.release.version.common.CommonBuildFeature

open class ReleaseVersionPlugin @Autowired constructor(extensionHolder: ServerExtensionHolder,
                                                       buildServer: SBuildServer,
                                                       releaseVersionBuildPropertiesProvider: ReleaseVersionBuildPropertiesProvider,
                                                       releaseVersionServerListener: ReleaseVersionServerListener) {
	companion object : NamedKLogging(Loggers.SERVER_CATEGORY)

	init {
		extensionHolder.registerExtension(releaseVersionBuildPropertiesProvider.javaClass,
		                                  CommonBuildFeature.FEATURE_NAME,
		                                  releaseVersionBuildPropertiesProvider)
		buildServer.addListener(releaseVersionServerListener)
	}

}