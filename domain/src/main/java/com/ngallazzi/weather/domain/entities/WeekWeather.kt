package com.ngallazzi.weather.domain.entities

import com.squareup.moshi.Json


data class WeekWeather(@Json(name = "daily") var dayForecasts: List<Daily>)