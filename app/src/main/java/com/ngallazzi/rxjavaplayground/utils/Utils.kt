package com.ngallazzi.rxjavaplayground.utils

import com.ngallazzi.rxjavaplayground.BuildConfig

class Utils() {
    fun fromIconIdToImageUrl(iconId: String): String {
        return "${BuildConfig.IMAGES_URL}${iconId}@2x.png"
    }

    fun getFormattedTemperature(temp: Double): String {
        return "${temp.toInt()} °C"
    }
}