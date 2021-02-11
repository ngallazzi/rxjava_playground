package com.ngallazzi.rxjavaplayground


class Utils {
    fun fromIconIdToImageUrl(iconId: String, baseImageUrl: String): String {
        return "${baseImageUrl}${iconId}@2x.png"
    }

    fun getFormattedTemperature(temp: Double): String {
        return "${temp.toInt()} °C"
    }
}