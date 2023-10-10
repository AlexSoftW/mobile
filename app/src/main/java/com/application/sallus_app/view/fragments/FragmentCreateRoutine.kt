package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.application.sallus_app.databinding.FragmentRegisterRoutineBinding
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.viewmodel.FoodViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCreateRoutine : Fragment() {

    private lateinit var binding: FragmentRegisterRoutineBinding
    private val viewmodel: FoodViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterRoutineBinding.inflate(inflater, container, false)

        val bundle = arguments
        val selectedFoods = bundle?.getString("selectedFoods")

        if (selectedFoods != null) {
            val gson = Gson()
            val foodData: List<FoodData> =
                gson.fromJson(selectedFoods, Array<FoodData>::class.java).toList()
//            Log.i("alimentList", "alimentos no create routine: $selectedFoods")
            Log.i("alimentList", "alimentos no create routine convertido: $foodData")
            binding.textviewTitleRegisterRoutine.text = foodData[0].nome
        } else {
            Log.i("alimentList", "onCreateView: esta chegando vazio a lista")
        }

        return binding.root
    }
}