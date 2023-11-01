package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.databinding.FragmentAddFoodBinding
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.viewmodel.FoodViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentAddFood : Fragment() {
    private lateinit var binding: FragmentAddFoodBinding

    private val foodViewModel: FoodViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFoodBinding.inflate(inflater, container, false)

        cadastrarNovoAlimento()

        return binding.root
    }

    private fun cadastrarNovoAlimento() {

        binding.buttonCadastrarAlimento.setOnClickListener {

            val nomeAlimento = binding.edittextFoodName.text.toString()
            val tipoAlimento = binding.spinnerTypeFood.text.toString()
            val isDiabetes = binding.switchOptionDiabetes.isChecked
            val isColesterol = binding.switchOptionColesterol.isChecked
            val isHipertensao = binding.switchOptionHipertensao.isChecked
            val proteina = binding.edittextQtdProteina.text.toString().toDouble()
            val carboidrato = binding.edittextQtdCarboidrato.text.toString().toDouble()
            val gordTotal = binding.edittextQtdGordTotal.text.toString().toDouble()
            val calorias = proteina + carboidrato + gordTotal

            val novoAlimento = FoodData(
                null,
                nomeAlimento,
                tipoAlimento,
                isDiabetes,
                isColesterol,
                isHipertensao,
                proteina,
                carboidrato,
                gordTotal,
                calorias,
                null
            )

            Log.i("dadoAlimento", "cadastrarNovoAlimento: $novoAlimento")
//
            foodViewModel.cadastrarNovoAlimento(novoAlimento)
        }
    }
}