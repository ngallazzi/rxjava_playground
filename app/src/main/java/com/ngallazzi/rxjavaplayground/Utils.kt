package com.ngallazzi.rxjavaplayground

import java.time.LocalDateTime
import java.time.ZoneOffset


class Utils {
    fun fromIconIdToImageUrl(iconId: String, baseImageUrl: String): String {
        return "${baseImageUrl}${iconId}@2x.png"
    }

    fun getFormattedTemperature(temp: Double): String {
        return "${temp.toInt()} °C"
    }

    fun getLocalDateTimeFromTimeStamp(timestamp: Long): LocalDateTime {
        return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC)
    }
}