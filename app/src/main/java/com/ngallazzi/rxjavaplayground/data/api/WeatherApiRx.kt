package com.ngallazzi.rxjavaplayground.data.api

import com.ngallazzi.rxjavaplayground.domain.CurrentWeatherApiResponse
import com.ngallazzi.rxjavaplayground.domain.DailyWeatherApiResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiRx {
    @GET("weather")
    fun getCityWeather(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") units: String = "metric"
    ): Single<CurrentWeatherApiResponse>

    @GET("onecall")
    fun getDailyWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String = "minutely,hourly,alerts,current",
        @Query("appid") appid: String,
        @Query("units") units: String = "metric"
    ): Single<DailyWeatherApiResponse>
}