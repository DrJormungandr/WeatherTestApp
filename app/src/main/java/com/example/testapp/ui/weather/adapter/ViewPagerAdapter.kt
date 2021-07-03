package com.example.testapp.ui.weather.adapter

import android.content.Context
import android.os.Bundle
import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testapp.R
import com.example.testapp.ui.weather.LocalModel
import com.example.testapp.ui.weather.WeatherNowPage
import com.example.testapp.ui.weather.WeatherWeekPage


class ViewPagerAdapter(val dataset: ArrayList<LocalModel>, fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment
        if (position == 0) {
            fragment = WeatherNowPage()
            fragment.arguments = Bundle().apply {
                this.putParcelable("weatherNow", dataset[0])
            }
        } else {
            fragment = WeatherWeekPage()
            fragment.arguments = Bundle().apply {
                this.putParcelableArrayList("weatherWeek", dataset)
            }
        }
        return fragment
    }

}