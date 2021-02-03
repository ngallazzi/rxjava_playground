package com.ngallazzi.rxjavaplayground.di

import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.data.WeatherRepositoryImpl
import com.ngallazzi.rxjavaplayground.data.api.NetworkModule
import com.ngallazzi.rxjavaplayground.data.api.WeatherRemoteDataSourceRx
import com.ngallazzi.rxjavaplayground.data.mappers.ApiResponseMapper
import com.ngallazzi.rxjavaplayground.utils.Utils

object ServiceLocator {

    private val utils by lazy {
        Utils()
    }

    private val networkModule by lazy {
        NetworkModule()
    }

    private val apiMapper by lazy {
        ApiResponseMapper(utils)
    }

    @Volatile
    var weatherRepository: WeatherRepositoryImpl? = null

    fun provideWeatherRepository(): WeatherRepositoryImpl {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return weatherRepository ?: createWeatherRepository()
        }
    }

    private fun createWeatherRepository(): WeatherRepositoryImpl {
        val newRepo =
            WeatherRepositoryImpl(createWeatherRemoteDataSource())
        weatherRepository = newRepo
        return newRepo
    }

    private fun createWeatherRemoteDataSource(): WeatherRemoteDataSourceRx {
        return WeatherRemoteDataSourceRx(
            networkModule.createWeatherApiRx(BuildConfig.API_URL),
            apiMapper
        )
    }
}