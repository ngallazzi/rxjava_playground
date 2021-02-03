package com.ngallazzi.rxjavaplayground.data.api

import com.ngallazzi.rxjavaplayground.domain.CurrentWeatherApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiCoroutines {
    @GET("weather")
    fun getCityWeather(
        @Query("q") city: String,
        @Query("appid") appid: String
    ): Response<CurrentWeatherApiResponse>
}