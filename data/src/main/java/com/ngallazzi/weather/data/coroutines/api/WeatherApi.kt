package com.ngallazzi.weather.data.coroutines.api

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("weather")
    fun getCityWeather(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") units: String = "metric"
    ): Response<CurrentWeather>

    @GET("onecall")
    fun getWeekWeatherByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String = "minutely,hourly,alerts,current",
        @Query("appid") appid: String,
        @Query("units") units: String = "metric"
    ): Response<WeekWeather>
}