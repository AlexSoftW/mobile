package com.application.sallus_app.view.fragmentsPaciente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.databinding.FragmentHistoricoPacienteBinding

class FragmentHistoricoAlimentarPaciente : Fragment() {

    private lateinit var binding: FragmentHistoricoPacienteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHistoricoPacienteBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setupView() {

    }

    private fun setupObservers() {

    }
}