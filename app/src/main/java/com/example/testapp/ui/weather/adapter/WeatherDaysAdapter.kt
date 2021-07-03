package com.example.testapp.ui.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.ui.weather.LocalModel

class WeatherDaysAdapter(private val data: ArrayList<LocalModel>): RecyclerView.Adapter<WeatherDaysAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_day_of_week, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.temp_text).text = data[position].temperature.toString()
        holder.itemView.findViewById<TextView>(R.id.day_of_week).text = data[position].dayOfWeek
        holder.itemView.findViewById<TextView>(R.id.day_of_month).text = data[position].formattedDate
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}