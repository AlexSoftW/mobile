package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.adapter.CreateRoutineAdapter
import com.application.sallus_app.databinding.FragmentRegisterRoutineBinding
import com.application.sallus_app.viewmodel.FoodViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCreateRoutine : Fragment() {

    private lateinit var binding: FragmentRegisterRoutineBinding
    private lateinit var adapter: CreateRoutineAdapter
    private val viewmodel: FoodViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterRoutineBinding.inflate(inflater, container, false)

//        val bundle = arguments
//        val selectedFoods = bundle?.getString("selectedFoods")
//        viewmodel.tratarAlimentosSelecionados(selectedFoods!!)

//        viewmodel.listaAlimentosCriarRotina.observe(viewLifecycleOwner) {
//            Log.i("alimentList", "alimentos no create routine convertido: $it")
//        }

        adapter = CreateRoutineAdapter()
        binding.recyclerViewRegisterRoutine.adapter = adapter

        val bundle = arguments
        val selectedFoods = bundle?.getString("selectedFoods")
        viewmodel.tratarAlimentosSelecionados(selectedFoods!!)

        viewmodel.listaAlimentosCriarRotina.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            Log.i("alimentList", "alimentos no create routine convertido: $it")

            val valorTotalCarboidratos = it.sumOf { it.carboidrato }
            val valorTotalProteinas = it.sumOf { it.proteina }
            val valorTotalGordurasTotais = it.sumOf { it.gorduraTotal }
            val valorTotalCalorias = it.sumOf { it.calorias!! }

            binding.textviewValueCarboidratoRegisterRoutine.text =
                valorTotalCarboidratos.toString()

            binding.textviewValueProteinaRegisterRoutine.text =
                valorTotalProteinas.toString()

            binding.textviewValueGorduraTotalRegisterRoutine.text =
                valorTotalGordurasTotais.toString()

            binding.textviewValueCaloriasRegisterRoutine.text =
                valorTotalCalorias.toString()
        }

        return binding.root
    }


}