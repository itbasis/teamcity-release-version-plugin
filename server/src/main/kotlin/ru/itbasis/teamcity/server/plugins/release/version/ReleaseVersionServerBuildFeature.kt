package ru.itbasis.teamcity.server.plugins.release.version

import jetbrains.buildServer.serverSide.BuildFeature
import ru.itbasis.teamcity.plugin.release.version.common.CommonBuildFeature

class ReleaseVersionServerBuildFeature : BuildFeature() {
	override fun describeParameters(params: MutableMap<String, String>): String = "Release Version Generator Plugin"

	override fun getEditParametersUrl(): String? = null

	override fun getDisplayName(): String = "Release Version Generator"

	override fun getType(): String = CommonBuildFeature.FEATURE_NAME

	override fun isMultipleFeaturesPerBuildTypeAllowed(): Boolean = false
}