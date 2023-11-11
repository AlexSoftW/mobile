package com.application.sallus_app.view.fragmentsPaciente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.databinding.FragmentDiarioAlimentarPacienteBinding

class FragmentDiarioAlimentarPaciente : Fragment() {

    private lateinit var binding: FragmentDiarioAlimentarPacienteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiarioAlimentarPacienteBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setupView() {

    }

    private fun setupObservers() {

    }
}