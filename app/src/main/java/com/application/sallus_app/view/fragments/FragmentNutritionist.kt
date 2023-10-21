package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.adapter.NutricionistaAdapter
import com.application.sallus_app.databinding.FragmentHomeNutricionistaBinding
import com.application.sallus_app.databinding.FragmentTodosNutricionistaBinding
import com.application.sallus_app.databinding.FragmentYoursPatientsBinding
import com.application.sallus_app.viewmodel.NutritionistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentNutritionist : Fragment() {
    private lateinit var binding: FragmentTodosNutricionistaBinding
    private lateinit var adapter: NutricionistaAdapter
    private val viewmodel: NutritionistViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodosNutricionistaBinding.inflate(inflater, container, false)

        adapter = NutricionistaAdapter()
        binding.recyclerViewTodosNutricionistas.adapter = adapter

        viewmodel.fetchTodosNutricionistas()

        viewmodel.listNutricionista.observe(viewLifecycleOwner) {
            adapter.subitList(it)
        }

        // if else para quando clickar no botao de busca do edit text entrar nessa viewmodel
//        viewmodel.fetchNutricionistaPorNome(binding.searchBarTodosNutricionistas.text.toString())
//
//        viewmodel.listTodosNutricionistaPorNome.observe(viewLifecycleOwner){
//            adapter.subitList(it)
//        }


        return binding.root
    }
}