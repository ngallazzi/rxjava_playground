package com.ngallazzi.rxjavaplayground.data.api

import com.ngallazzi.rxjavaplayground.domain.Weather
import com.ngallazzi.rxjavaplayground.domain.WeatherInfo
import com.squareup.moshi.Json

class WeatherApiResponse(
    @field:Json(name = "weather")
    val weather: List<Weather>,
    @field:Json(name = "main")
    val weatherInfo: WeatherInfo,
    @field:Json(name = "id")
    val cityId : String,
    @field:Json(name = "name")
    val cityName : String
)