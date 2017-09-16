package ru.itbasis.teamcity.plugin.release.version.common

import jetbrains.buildServer.util.StringUtil

object CommonBuildFeature {
	const val FEATURE_NAME = "release-version-generator"

	init {
		assert(StringUtil.isTrue("true"))
	}
}