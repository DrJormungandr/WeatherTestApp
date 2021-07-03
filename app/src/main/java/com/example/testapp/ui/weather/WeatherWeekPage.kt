package com.example.testapp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.ui.weather.adapter.WeatherDaysAdapter

class WeatherWeekPage: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.page_weather_week, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = arguments?.getParcelableArrayList<LocalModel>("weatherWeek") as ArrayList<LocalModel>
        val recyclerView = view.findViewById<RecyclerView>(R.id.days_list)
        val adapter = WeatherDaysAdapter(data)
        recyclerView.adapter = adapter
    }
}