package com.ngallazzi.rxjavaplayground.domain

import com.ngallazzi.rxjavaplayground.domain.entities.Daily
import com.squareup.moshi.Json

data class DailyWeatherApiResponse(@Json(name = "daily") val dayForecasts: List<Daily>)