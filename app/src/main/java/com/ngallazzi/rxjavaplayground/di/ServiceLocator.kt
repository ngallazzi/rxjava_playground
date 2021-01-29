package com.ngallazzi.rxjavaplayground.di

import android.content.Context
import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.data.WeatherRepositoryImpl
import com.ngallazzi.rxjavaplayground.data.api.ApiResponseMapper
import com.ngallazzi.rxjavaplayground.data.api.NetworkModule
import com.ngallazzi.rxjavaplayground.data.api.WeatherRemoteDataSourceRx

object ServiceLocator {

    private val networkModule by lazy {
        NetworkModule()
    }

    private val apiMapper by lazy {
        ApiResponseMapper()
    }

    @Volatile
    var weatherRepository: WeatherRepositoryImpl? = null

    fun provideWeatherRepository(context: Context): WeatherRepositoryImpl {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return weatherRepository ?: createWeatherRepository(context)
        }
    }

    private fun createWeatherRepository(context: Context): WeatherRepositoryImpl {
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