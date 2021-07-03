package com.example.testapp.network

import com.example.testapp.network.ResponseModels.WeatherResponseModel
import com.example.testapp.network.ResponseModels.WeeklyResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather") fun getCityWeather(
        @Query("q") city: String,
        @Query("units") units: String,
    ): Observable<WeatherResponseModel>

    @GET ("onecall") fun getWeeklyWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("exclude") exclude: String,
    ): Observable<WeeklyResponse>
}