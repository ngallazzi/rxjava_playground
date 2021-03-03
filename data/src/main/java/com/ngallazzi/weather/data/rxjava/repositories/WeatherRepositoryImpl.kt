package com.ngallazzi.weather.data.rxjava.repositories

import com.ngallazzi.weather.data.rxjava.persistence.WeatherLocalDataSource
import com.ngallazzi.weather.data.rxjava.remote.WeatherRemoteDataSource
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.DayWeather
import com.ngallazzi.weather.domain.entities.Hourly
import com.ngallazzi.weather.domain.entities.WeekWeather
import com.ngallazzi.weather.domain.repositories.rxjava.WeatherRepository
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource
) :
    WeatherRepository {
    override fun getCurrentWeather(city: String): Single<CurrentWeather> {
        localDataSource.getCityWeather(city)?.let { weather ->
            return Single.just(weather)
        } ?: run {
            return remoteDataSource.getCityWeather(city)
        }
    }

    override fun getDayWeather(
        coordinates: CurrentWeather.Coordinates,
        currentDate: LocalDate
    ): Observable<List<Hourly>> {
        val cachedWeather = localDataSource.getDayWeather()
        val today = LocalDate.now()
        return if (cachedWeather.isEmpty()) {
            remoteDataSource.getDayWeather(coordinates)
        } else {
            if (LocalDate.ofEpochDay(cachedWeather.first().date).isBefore(today)) {
                remoteDataSource.getDayWeather(coordinates)
            } else {
                Observable.just(localDataSource.getDayWeather())
            }
        }

    }

    override fun saveDayWeather(weather: List<Hourly>) {
        localDataSource.saveDayWeather(weather)
    }

    override fun getWeekWeather(coordinates: CurrentWeather.Coordinates): Single<WeekWeather> {
        localDataSource.getWeekWeather(coordinates)?.let { weather ->
            return Single.just(weather)
        } ?: run {
            return remoteDataSource.getWeekWeather(coordinates)
        }
    }

    override fun saveCityWeather(weather: CurrentWeather) {
        localDataSource.saveCityWeather(weather)
    }

    override fun saveWeekWeather(
        coordinates: CurrentWeather.Coordinates,
        weekWeather: WeekWeather
    ) {
        localDataSource.saveWeekWeather(coordinates, weekWeather)
    }
}