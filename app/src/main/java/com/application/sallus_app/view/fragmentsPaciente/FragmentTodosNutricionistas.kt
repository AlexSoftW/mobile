package com.application.sallus_app.view.fragmentsPaciente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.adapter.NutricionistaAdapter
import com.application.sallus_app.databinding.FragmentTodosNutricionistaBinding
import com.application.sallus_app.viewmodel.NutritionistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentTodosNutricionistas : Fragment() {
    private lateinit var binding: FragmentTodosNutricionistaBinding
    private lateinit var adapter: NutricionistaAdapter
    private val viewmodel: NutritionistViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodosNutricionistaBinding.inflate(inflater, container, false)

        setupView()
        setupObservers()

        return binding.root
    }

    private fun setupView() {
        adapter = NutricionistaAdapter()
        binding.recyclerViewTodosNutricionistas.adapter = adapter
    }

    private fun setupObservers() {
        viewmodel.fetchTodosNutricionistas()

        viewmodel.listNutricionista.observe(viewLifecycleOwner) {
            adapter.subitList(it)
        }
    }
}