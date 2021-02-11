package com.ngallazzi.weather.data.persistence

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather

class WeatherLocalDataSource {
    fun saveCurrentWeather(weather: CurrentWeather) {

    }

    fun getCurrentWeather(city: String): CurrentWeather? {
        return null
    }

    fun saveWeekWeather(weather: WeekWeather) {

    }

    fun getWeekWeather(): WeekWeather? {
        return null
    }
}