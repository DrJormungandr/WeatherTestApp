package com.example.testapp.ui.choose_city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.services.WeatherService
import com.example.testapp.ui.weather.LocalModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChooseCityViewModel @Inject constructor(private val weatherService: WeatherService): ViewModel() {

    val onSuccess = MutableLiveData<List<LocalModel>>()
    val onError = MutableLiveData<String>()

    fun getWeatherForSetCity (city: String) {
        weatherService.getTodaysWeather(city).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe({
            val weatherData = it.daily.map{ item ->
                val date = Date(item.dt.toLong() * 1000)

                val dayOfWeek = when(date.day) {
                    0 -> "MON"
                    1 -> "TUE"
                    2 -> "WED"
                    3 -> "THU"
                    4 -> "FRI"
                    5 -> "SAT"
                    6 -> "SUN"
                    else -> "???"
                }

                val dayMonthFormat = SimpleDateFormat("dd-MMM", Locale.getDefault())

                val formattedDate = dayMonthFormat.format(date)

                return@map LocalModel(item.dt, item.sunrise, item.sunset, item.temp.day, dayOfWeek, formattedDate)
            }
            onSuccess.postValue(weatherData)
        },{
            onError.postValue((it.message))
        })
    }

}