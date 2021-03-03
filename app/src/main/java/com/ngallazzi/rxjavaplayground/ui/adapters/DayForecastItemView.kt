package com.ngallazzi.rxjavaplayground.ui.adapters

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.ngallazzi.rxjavaplayground.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class DayForecastItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val ivWeather: ImageView
    private val tvDate: TextView
    private val tvTemp: TextView
    private val tvWeather: TextView

    init {
        inflate(context, R.layout.item_day_forecast, this)
        ivWeather = findViewById(R.id.ivWeather)
        tvDate = findViewById(R.id.tvDate)
        tvTemp = findViewById(R.id.tvTemp)
        tvWeather = findViewById(R.id.tvWeather)
    }

    @ModelProp
    fun setWeatherImage(imageUrl: String) {
        ivWeather.load(imageUrl)
    }

    @ModelProp
    fun setDate(dateTime: LocalDateTime) {
        tvDate.text = formatter.format(dateTime)
    }

    @TextProp
    fun setTemperature(temp: CharSequence) {
        tvTemp.text = temp
    }

    @TextProp
    fun setWeather(temp: CharSequence) {
        tvWeather.text = temp
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("HH:mm")
    }
}