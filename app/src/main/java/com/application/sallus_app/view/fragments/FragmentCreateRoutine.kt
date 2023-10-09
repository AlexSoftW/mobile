package com.application.sallus_app.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.databinding.FragmentRegisterRoutineBinding
import com.application.sallus_app.model.FoodData
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

class FragmentCreateRoutine : Fragment() {

    private lateinit var binding: FragmentRegisterRoutineBinding
    private var foodDataList = mutableListOf<FoodData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterRoutineBinding.inflate(inflater, container, false)

        val sharedPreferences =
            activity?.getSharedPreferences("key_value", Context.MODE_PRIVATE)
        val listaDeAlimentosString = sharedPreferences?.getString("listaDeAlimentos", null)

        val jsonElement = JsonParser().parse(listaDeAlimentosString)
        if (jsonElement.isJsonArray) {
            // É um array JSON, você pode analisá-lo como uma lista de objetos
            foodDataList =
                Gson().fromJson(jsonElement, object : TypeToken<List<FoodData>>() {}.type)
            Log.i("respostaFragment", "onCreateView: no if esta caindo isso aqui: $foodDataList")
        } else if (jsonElement.isJsonObject) {
            // É um objeto JSON, você pode analisá-lo como um objeto único
            val foodData = Gson().fromJson(jsonElement, FoodData::class.java)
            // Faça o que for necessário com o objeto FoodData
            Log.i("respostaFragment", "onCreateView: esta chegando isso aqui: $foodData")
        }

        return binding.root
    }
}