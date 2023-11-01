package com.application.sallus_app.view.fragmentsNutricionista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.adapter.NutricionistaAdapter
import com.application.sallus_app.databinding.FragmentTodosNutricionistaBinding
import com.application.sallus_app.viewmodel.NutritionistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentYoursPatients : Fragment() {
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

        return binding.root
    }
}