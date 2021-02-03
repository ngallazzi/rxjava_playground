package com.ngallazzi.rxjavaplayground.domain

import com.ngallazzi.rxjavaplayground.domain.entities.Weather
import com.ngallazzi.rxjavaplayground.domain.entities.WeatherInfo
import com.squareup.moshi.Json

data class CurrentWeatherApiResponse(
    @Json(name = "weather") val weather: List<Weather>,
    @Json(name = "main") val weatherInfo: WeatherInfo,
    @Json(name = "id") val cityId: Int,
    @Json(name = "name") val cityName: String
)