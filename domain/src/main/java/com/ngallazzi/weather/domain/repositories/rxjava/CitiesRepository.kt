package com.ngallazzi.weather.domain.repositories.rxjava

import com.ngallazzi.weather.domain.entities.City
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface CitiesRepository {
    fun getCities(forceUpdate: Boolean): Observable<List<City>>
}