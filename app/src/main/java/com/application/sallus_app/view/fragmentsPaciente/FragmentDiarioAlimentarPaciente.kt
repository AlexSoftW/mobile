package com.application.sallus_app.view.fragmentsPaciente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.databinding.FragmentDiarioAlimentarBinding

class FragmentDiarioAlimentarPaciente : Fragment() {

    private lateinit var binding: FragmentDiarioAlimentarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiarioAlimentarBinding.inflate(inflater, container, false)

        setupView()
        setupObservers()

        return binding.root
    }

    private fun setupView() {

    }

    private fun setupObservers() {

    }
}