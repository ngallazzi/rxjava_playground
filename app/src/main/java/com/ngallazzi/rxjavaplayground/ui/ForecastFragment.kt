package com.ngallazzi.rxjavaplayground.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.WeatherApplication
import com.ngallazzi.rxjavaplayground.databinding.FragmentForecastBinding

class ForecastFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding

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
        binding = FragmentForecastBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastViewModel.city.observe(viewLifecycleOwner, { city ->
            binding.tvCityName.text = city.name
            binding.tvMinTemp.text = city.weatherInfo.minTemp.toInt().toString()
            binding.tvMaxTemp.text = city.weatherInfo.maxTemp.toInt().toString()
            binding.ivCurrentWeather.load(city.weather.iconUrl)
        })

        forecastViewModel.dataLoading.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    binding.clData.visibility = View.GONE
                    binding.pbLoading.visibility = View.VISIBLE
                }
                false -> {
                    binding.clData.visibility = View.VISIBLE
                    binding.pbLoading.visibility = View.GONE
                }
            }
        })

        forecastViewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }
}