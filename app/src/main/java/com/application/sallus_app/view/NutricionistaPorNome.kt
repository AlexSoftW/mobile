package com.application.sallus_app.view

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.databinding.FragmentTodosNutricionistaBinding
import com.application.sallus_app.viewmodel.NutritionistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NutricionistaPorNome:AppCompatActivity() {
    private val nutritionistViewModel: NutritionistViewModel by viewModel()
    private lateinit var binding: FragmentTodosNutricionistaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}