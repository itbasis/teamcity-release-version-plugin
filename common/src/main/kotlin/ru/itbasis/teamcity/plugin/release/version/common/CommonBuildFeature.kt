package ru.itbasis.teamcity.plugin.release.version.common

import jetbrains.buildServer.util.StringUtil

object CommonBuildFeature {
	const val FEATURE_NAME = "release-version-generator"

	const val VARIABLE_RELEASE_NAME = "release.version"

	init {
		assert(StringUtil.isTrue("true"))
	}
}