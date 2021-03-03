package com.ngallazzi.weather.data.rxjava.api

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.DayWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("weather")
    fun getCityWeather(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") units: String = "metric"
    ): Single<CurrentWeather>

    @GET("onecall")
    fun getWeekWeatherByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String = "minutely,alerts,current",
        @Query("appid") appid: String,
        @Query("units") units: String = "metric"
    ): Single<WeekWeather>

    @GET("onecall")
    fun getDayWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String = "minutely,alerts,current",
        @Query("appid") appid: String,
        @Query("units") units: String = "metric"
    ): Observable<DayWeather>
}