package com.ngallazzi.weather.data.rxjava.remote

import com.ngallazzi.weather.data.rxjava.api.WeatherApi
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.DayWeather
import com.ngallazzi.weather.domain.entities.Hourly
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class WeatherRemoteDataSource(
    private val service: WeatherApi,
    private val apiKey: String
) {

    fun getCityWeather(cityName: String): Single<CurrentWeather> {
        return service.getCityWeather(cityName, apiKey)
    }

    fun getWeekWeather(coordinates: CurrentWeather.Coordinates): Single<WeekWeather> {
        return service.getWeekWeatherByCoordinates(coordinates.lat, coordinates.lon, appid = apiKey)
    }

    fun getDayWeather(coordinates: CurrentWeather.Coordinates): Observable<List<Hourly>> {
        return service.getDayWeather(coordinates.lat, coordinates.lon, appid = apiKey).map {
            it.hourly
        }
    }
}