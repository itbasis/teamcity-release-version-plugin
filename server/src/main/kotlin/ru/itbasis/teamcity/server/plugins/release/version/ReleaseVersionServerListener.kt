package ru.itbasis.teamcity.server.plugins.release.version

import jetbrains.buildServer.log.Loggers
import jetbrains.buildServer.serverSide.BuildServerAdapter
import jetbrains.buildServer.serverSide.SQueuedBuild
import jetbrains.buildServer.serverSide.SRunningBuild
import mu.NamedKLogging

class ReleaseVersionServerListener : BuildServerAdapter() {
	companion object : NamedKLogging(Loggers.SERVER_CATEGORY)

	override fun buildTypeAddedToQueue(queuedBuild: SQueuedBuild) {
		logger.info("buildTypeAddedToQueue: {}", this.javaClass.name)
	}

	override fun buildStarted(build: SRunningBuild) {
		logger.info("buildStarted: {}", this.javaClass.name)
	}
}