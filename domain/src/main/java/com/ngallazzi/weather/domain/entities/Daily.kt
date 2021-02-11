package com.ngallazzi.weather.domain.entities

import com.squareup.moshi.Json

class Daily(    @Json(name = "dt")
    val date: Long,
    @Json(name = "temp")
    val temperature: Temperature,
    val weather: List<CurrentWeather.Weather>
)