package com.application.sallus_app.view.fragments

import FragmentDadosPessoais
import FragmentDadosPessoaisNutri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentCadastroEscolhaBinding

class FragmentEscolherPerfil : Fragment() {

    private lateinit var binding: FragmentCadastroEscolhaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCadastroEscolhaBinding.inflate(inflater, container, false)

        setupView()

        return binding.root
    }

    private fun setupView() {

        binding.buttonEscolherPaciente.setOnClickListener {

            val fragmentPaciente = FragmentDadosPessoais()
            val bundle = Bundle()

            fragmentPaciente.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_cadastro, fragmentPaciente)
                .addToBackStack(null)
                .commit()
        }

        binding.buttonEscolherNutricionista.setOnClickListener {

            val fragmentNutricionista = FragmentDadosPessoaisNutri()
            val bundle = Bundle()

            fragmentNutricionista.arguments = bundle


            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_cadastro, fragmentNutricionista)
                .addToBackStack(null)
                .commit()

        }

    }


}