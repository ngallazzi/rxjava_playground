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
class ForecastItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val tvDate: TextView
    private val tvMinTemp: TextView
    private val tvMaxTemp: TextView
    private val ivWeather: ImageView

    init {
        inflate(context, R.layout.item_forecast, this)
        tvDate = findViewById(R.id.tvDate)
        tvMinTemp = findViewById(R.id.tvMinTemp)
        tvMaxTemp = findViewById(R.id.tvMaxTemp)
        ivWeather = findViewById(R.id.ivWeather)
    }

    @ModelProp
    fun setDate(dateTime: LocalDateTime) {
        tvDate.text = formatter.format(dateTime)
    }

    @TextProp
    fun setMinTemp(temp: CharSequence) {
        tvMinTemp.text = temp
    }

    @TextProp
    fun setMaxTemp(temp: CharSequence) {
        tvMaxTemp.text = temp
    }

    @ModelProp
    fun setIcon(iconUrl: String) {
        ivWeather.load(iconUrl)
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("E dd")
    }
}