package com.example.testapp.services

import com.example.testapp.network.ResponseModels.WeatherResponseModel
import com.example.testapp.network.ResponseModels.WeeklyResponse
import com.example.testapp.network.WeatherApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherService @Inject constructor(
    private val weatherApi: WeatherApi
){
    fun getTodaysWeather(city: String): Observable<WeeklyResponse> {
       return weatherApi.getCityWeather(city, "metric").flatMap {
           return@flatMap weatherApi.getWeeklyWeather(it.lat, it.lon, "metric", "minutely,hourly,alerts")
       }
    }
}