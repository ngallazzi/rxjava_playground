package com.ngallazzi.rxjavaplayground.domain.entities

import com.squareup.moshi.Json


data class WeatherInfo(
    val temp: Double,
    @Json(name = "temp_min")
    val minTemp: Double,
    @Json(name = "temp_max")
    val maxTemp: Double
)