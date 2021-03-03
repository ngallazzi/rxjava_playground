package com.ngallazzi.weather.domain.repositories.rxjava

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.DayWeather
import com.ngallazzi.weather.domain.entities.Hourly
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

interface WeatherRepository {

    fun getCurrentWeather(city: String): Single<CurrentWeather>

    fun getDayWeather(
        coordinates: CurrentWeather.Coordinates,
        currentDate: LocalDate
    ): Observable<List<Hourly>>

    fun saveDayWeather(weather: List<Hourly>)

    fun getWeekWeather(
        coordinates: CurrentWeather.Coordinates
    ): Single<WeekWeather>

    fun saveCityWeather(weather: CurrentWeather)

    fun saveWeekWeather(coordinates: CurrentWeather.Coordinates, weekWeather: WeekWeather)
}