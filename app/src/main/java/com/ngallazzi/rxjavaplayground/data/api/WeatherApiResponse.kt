package com.ngallazzi.rxjavaplayground.data.api

import com.ngallazzi.rxjavaplayground.domain.Weather
import com.ngallazzi.rxjavaplayground.domain.WeatherInfo
import com.squareup.moshi.Json

data class WeatherApiResponse(
    @Json(name = "weather") val weather: List<Weather>,
    @Json(name = "main") val weatherInfo: WeatherInfo,
    @Json(name = "id") val cityId: Int,
    @Json(name = "name") val cityName: String
)