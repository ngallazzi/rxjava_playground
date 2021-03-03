package com.ngallazzi.weather.data.rxjava.persistence

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.DayWeather
import com.ngallazzi.weather.domain.entities.Hourly
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Single

class WeatherLocalDataSource {
    // in memory persistence for simplicity
    private val citiesCurrentWeathers: ArrayList<CurrentWeather> = arrayListOf()
    private val citiesWeekWeathers: ArrayList<Pair<CurrentWeather.Coordinates, WeekWeather>> =
        arrayListOf()
    private var dayWeather: List<Hourly> = listOf()

    fun getCityWeather(cityName: String): CurrentWeather? {
        citiesCurrentWeathers.find { it.cityName == cityName }?.let { weather ->
            return weather
        } ?: kotlin.run {
            return null
        }
    }

    fun saveCityWeather(currentWeather: CurrentWeather) {
        citiesCurrentWeathers.find { it.cityName == currentWeather.cityName }?.let { weather ->
            weather.weatherInfo = currentWeather.weatherInfo
            weather.weather = currentWeather.weather
        } ?: kotlin.run {
            citiesCurrentWeathers.add(currentWeather)
        }
    }

    fun getWeekWeather(coordinates: CurrentWeather.Coordinates): WeekWeather? {
        citiesWeekWeathers.find { it.first.lat == coordinates.lat && it.first.lon == coordinates.lon }
            ?.let {
                return it.second
            } ?: kotlin.run {
            return null
        }
    }

    fun saveWeekWeather(coordinates: CurrentWeather.Coordinates, weather: WeekWeather) {
        citiesWeekWeathers.find { it.first.lat == coordinates.lat && it.first.lon == coordinates.lon }
            ?.let {
                it.second.dayForecasts = weather.dayForecasts
            } ?: kotlin.run {
            citiesWeekWeathers.add(Pair(coordinates, weather))
        }
    }

    fun getDayWeather(): List<Hourly> {
        return dayWeather
    }

    fun saveDayWeather(weather: List<Hourly>) {
        dayWeather = weather
    }
}