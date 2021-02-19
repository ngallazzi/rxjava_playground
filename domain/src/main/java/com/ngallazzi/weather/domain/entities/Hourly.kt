package com.ngallazzi.weather.domain.entities

import com.squareup.moshi.Json

data class Hourly(
    @Json(name = "dt")
    val date: Long,
    @Json(name = "temp")
    val temperature: Double,
    val weather: List<CurrentWeather.Weather>
)