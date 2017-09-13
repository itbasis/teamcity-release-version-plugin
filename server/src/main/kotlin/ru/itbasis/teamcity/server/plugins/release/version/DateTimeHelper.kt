package ru.itbasis.teamcity.server.plugins.release.version

import java.text.DateFormat
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {
	private val DF_SIMPLE_STRING = "yyyyMMdd.HHmmss"

	@JvmField
	val DF_SIMPLE_FORMAT = object : ThreadLocal<DateFormat>() {
		override fun initialValue(): DateFormat {
			return SimpleDateFormat(DF_SIMPLE_STRING, Locale.US)
		}
	}


	fun Date.asString(format: DateFormat): String = format.format(this)

	fun Date.asString(format: String): String = asString(SimpleDateFormat(format, Locale.US))

	fun Date.asString(): String = DateTimeHelper.DF_SIMPLE_FORMAT.get().format(this)
}