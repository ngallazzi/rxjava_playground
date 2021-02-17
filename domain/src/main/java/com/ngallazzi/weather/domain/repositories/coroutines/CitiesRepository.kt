package com.ngallazzi.weather.domain.repositories.coroutines

import com.ngallazzi.weather.domain.common.Result
import com.ngallazzi.weather.domain.entities.City
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface CitiesRepository {
    fun getCities(forceUpdate: Boolean): Result<List<City>>
}