package com.ngallazzi.weather.data.rxjava.persistence

import com.ngallazzi.weather.data.rxjava.WeatherDataSource
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Maybe

class WeatherLocalDataSource : WeatherDataSource {
    // in memory persistence for simplicity
    private val citiesCurrentWeathers: ArrayList<Pair<String, CurrentWeather>> = arrayListOf()
    private val citiesWeekWeathers: ArrayList<Pair<CurrentWeather.Coordinates, WeekWeather>> =
        arrayListOf()

    override fun getCityWeather(cityName: String): Maybe<CurrentWeather> {
        citiesCurrentWeathers.find { it.first == cityName }?.let {
            return Maybe.just(it.second)
        } ?: kotlin.run {
            return Maybe.empty()
        }
    }

    override fun saveCityWeather(cityName: String, currentWeather: CurrentWeather) {
        citiesCurrentWeathers.find { it.first == cityName }?.let {
            it.second.weatherInfo = currentWeather.weatherInfo
            it.second.weather = currentWeather.weather
        } ?: kotlin.run {
            citiesCurrentWeathers.add(Pair(cityName, currentWeather))
        }
    }

    override fun getWeekWeather(coordinates: CurrentWeather.Coordinates): Maybe<WeekWeather> {
        citiesWeekWeathers.find { it.first.lat == coordinates.lat && it.first.lon == coordinates.lon }
            ?.let {
                return Maybe.just(it.second)
            } ?: kotlin.run {
            return Maybe.empty()
        }
    }

    override fun saveWeekWeather(coordinates: CurrentWeather.Coordinates, weather: WeekWeather) {
        citiesWeekWeathers.find { it.first.lat == coordinates.lat && it.first.lon == coordinates.lon }
            ?.let {
                it.second.dayForecasts = weather.dayForecasts
            } ?: kotlin.run {
            citiesWeekWeathers.add(Pair(coordinates, weather))
        }
    }
}