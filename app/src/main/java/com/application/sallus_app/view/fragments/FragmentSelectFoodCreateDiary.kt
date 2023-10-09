package com.application.sallus_app.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.adapter.FoodCreateRoutineAdapter
import com.application.sallus_app.databinding.FragmentFoodsBinding
import com.application.sallus_app.viewmodel.FoodViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentSelectFoodCreateDiary : Fragment() {

    lateinit var binding: FragmentFoodsBinding
    private val viewmodel: FoodViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodsBinding.inflate(inflater, container, false)

        setupView()
        setupObservers()

        return binding.root
    }


    fun setupView() {
        binding.textviewTitleFoods.text = "estou aqui em outra telinha"

        binding.buttonCriarRotina.visibility = View.VISIBLE

    }

    private fun setupObservers() {

        val adapter = FoodCreateRoutineAdapter()
        binding.recyclerViewFood.adapter = adapter

        viewmodel.fetchTodosAlimentos()

        viewmodel.listAlimentos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }


        binding.buttonCriarRotina.setOnClickListener {
            val sharedPreferences =
                activity?.getSharedPreferences("key_value", Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.putString("listaDeAlimentos", Gson().toJson(viewmodel.listAlimentos))
            editor?.apply()

            val fragmentCreateRoutine = FragmentCreateRoutine()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_nutricionista, fragmentCreateRoutine)
            transaction.addToBackStack(null) // Opcional, adiciona a transação à pilha de volta
            transaction.commit()

        }
    }


}