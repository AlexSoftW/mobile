package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.adapter.NutricionistasAdapter
import com.application.sallus_app.databinding.FragmentTodosNutricionistaBinding
import com.application.sallus_app.viewmodel.NutritionistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentNutritionist : Fragment() {
    private lateinit var binding: FragmentTodosNutricionistaBinding
    private lateinit var adapter: NutricionistasAdapter
    private val viewModel: NutritionistViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodosNutricionistaBinding.inflate(inflater, container, false)
        adapter = NutricionistasAdapter()
        binding.recyclerViewTodosNutricionistas.adapter = adapter
        viewModel.listNutricionista.observe(viewLifecycleOwner){
            adapter.updateItems(it)
        }

        return binding.root
    }
}