package com.ngallazzi.rxjavaplayground

import android.app.Application
import com.ngallazzi.rxjavaplayground.di.appModule
import com.ngallazzi.rxjavaplayground.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(appModule, viewModelsModule)
        }
    }
}