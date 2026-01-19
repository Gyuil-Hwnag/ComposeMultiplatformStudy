package com.example.cmpstudy.utils

import kotlinx.datetime.*
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun getTodayDate(): String {
    fun LocalDateTime.format() = toString().substringBefore('T')
    val now = Clock.System.now()
    val zone = TimeZone.currentSystemDefault()
    return now.toLocalDateTime(zone).format()
}

@OptIn(ExperimentalTime::class)
fun getCurrentTimeAt(location: String, zone: TimeZone): String {
    return try {
        val time = Clock.System.now()
        val localTime = time.toLocalDateTime(zone).time
        return "The time in $location is ${localTime.formatted()}"
    } catch (ex: IllegalTimeZoneException) {
        "Unexpected timezone"
    }
}

fun LocalTime.formatted() = "$hour:$minute:$second"
