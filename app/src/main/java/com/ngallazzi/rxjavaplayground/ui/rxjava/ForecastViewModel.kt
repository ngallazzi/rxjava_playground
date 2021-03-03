package com.ngallazzi.rxjavaplayground.ui.rxjava

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.entities.CityForecast
import com.ngallazzi.rxjavaplayground.entities.DailyForecast
import com.ngallazzi.rxjavaplayground.mappers.ForecastsMapper
import com.ngallazzi.weather.domain.usecases.rxjava.GetWeatherUseCase
import com.ngallazzi.weather.domain.usecases.rxjava.GetWeekWeatherUseCase
import com.ngallazzi.weather.domain.usecases.rxjava.SaveWeatherUseCase
import com.ngallazzi.weather.domain.usecases.rxjava.SaveWeekWeatherUseCase
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable

class ForecastViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeekWeatherUseCase: GetWeekWeatherUseCase,
    private val saveWeatherUseCase: SaveWeatherUseCase,
    private val saveWeekWeatherUseCase: SaveWeekWeatherUseCase,
    private val mapper: ForecastsMapper
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading
    private val disposables = arrayListOf<Disposable>()

    private val _city: MutableLiveData<Pair<CityForecast, List<DailyForecast>>> = MutableLiveData()
    val city = _city

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getWeather(
        city: String = "Castellanza"
    ) {
        _dataLoading.postValue(true)
        val weatherObservable = getWeatherUseCase.invoke(city)
        val dailyForecastsObservable = weatherObservable.flatMap { weather ->
            getWeekWeatherUseCase.invoke(weather.coordinates)
        }

        val disposable = Single.zip(
            weatherObservable,
            dailyForecastsObservable,
            { weather, dailyForecast -> Pair(weather, dailyForecast) })
            .subscribe(
                { success ->
                    saveWeatherUseCase.invoke(success.first)
                    saveWeekWeatherUseCase.invoke(success.first.coordinates, success.second)

                    val cityForecast = mapper.fromCurrentWeatherToCityForecast(
                        success.first,
                        BuildConfig.IMAGES_URL
                    )
                    val dailyForecast = mapper.fromWeekWeatherToDailyForecastList(
                        success.second,
                        BuildConfig.IMAGES_URL
                    )
                    _city.postValue(Pair(cityForecast, dailyForecast))
                    _dataLoading.postValue(false)
                },
                { error ->
                    _error.postValue(error.message)
                    _dataLoading.postValue(false)
                }
            )
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        for (disposable in disposables) {
            disposable.dispose()
        }
    }
}
