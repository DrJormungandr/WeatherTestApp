package com.example.testapp.network.ResponseModels

data class WeeklyResponse(
    val daily : ArrayList<WeeklyItem>
)

data class WeeklyItem(
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temperature,
)

data class Temperature(
    val day: Double
)
