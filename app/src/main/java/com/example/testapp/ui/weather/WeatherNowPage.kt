package com.example.testapp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testapp.R

class WeatherNowPage: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.page_weather_now, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val model = arguments?.getParcelable<LocalModel>("weatherNow")
        view.findViewById<TextView>(R.id.temp_text).text = model?.temperature.toString()
    }
}