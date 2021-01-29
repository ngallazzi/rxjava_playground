package com.ngallazzi.rxjavaplayground.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.R
import com.ngallazzi.rxjavaplayground.WeatherApplication

class ForecastFragment : Fragment() {

    private val forecastViewModel: ForecastViewModel by viewModels {
        ForecastViewModel.ForecastViewModelFactory(
            ((requireActivity().application) as WeatherApplication).getWeatherUseCase,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forecastViewModel.getWeather("Castellanza", BuildConfig.API_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastViewModel.city.observe(viewLifecycleOwner, {

        })

        forecastViewModel.dataLoading.observe(viewLifecycleOwner, {

        })

        forecastViewModel.error.observe(viewLifecycleOwner, {

        })
    }
}