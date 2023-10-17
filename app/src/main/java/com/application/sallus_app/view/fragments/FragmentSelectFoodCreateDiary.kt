package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.adapter.SelectFoodCreateDiaryAdapter
import com.application.sallus_app.databinding.FragmentFoodsBinding
import com.application.sallus_app.viewmodel.FoodViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentSelectFoodCreateDiary : Fragment() {

    lateinit var binding: FragmentFoodsBinding
    private lateinit var adapter: SelectFoodCreateDiaryAdapter
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
        adapter = SelectFoodCreateDiaryAdapter()

        binding.textviewTitleFoods.text = "estou aqui em outra telinha"
        binding.buttonCriarRotina.visibility = View.VISIBLE

        binding.buttonCriarRotina.setOnClickListener {
            val bundle = Bundle()
            val selectedFoods = adapter.selectedFoods
            val gson = Gson()
            val json = gson.toJson(selectedFoods)
            bundle.putString("selectedFoods", json)

            val fragment = FragmentCreateRoutine()
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_nutricionista, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupObservers() {
        binding.recyclerViewFood.adapter = adapter

        viewmodel.buscarTodosAlimentos()

        viewmodel.listAlimentos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }


}