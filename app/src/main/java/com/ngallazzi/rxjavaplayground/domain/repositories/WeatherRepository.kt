package com.ngallazzi.rxjavaplayground.domain.repositories

import com.ngallazzi.rxjavaplayground.domain.City
import com.ngallazzi.rxjavaplayground.domain.common.Result
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    fun getCityWeather(city: String, apiKey: String): Single<City>
}