package com.example.testapp.ui.choose_city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testapp.R
import com.example.testapp.ui.weather.LocalModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class ChooseCityFragment : Fragment() {

    private val viewModel: ChooseCityViewModel by viewModels {
        defaultViewModelProviderFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onSuccess.observe(viewLifecycleOwner) {
            val args = Bundle()
            args.putParcelableArrayList("data", ArrayList<LocalModel>(it))
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, args)
        }
        viewModel.onError.observe(viewLifecycleOwner) {
            Snackbar.make(view, it, Snackbar.LENGTH_SHORT)
        }

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            viewModel.getWeatherForSetCity(view.findViewById<EditText>(R.id.edit_text).text.toString())
        }
    }
}