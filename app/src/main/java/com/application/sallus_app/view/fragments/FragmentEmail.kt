package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentCadastroComorbidadeBinding
import com.application.sallus_app.databinding.FragmentCadastroEmailBinding

class FragmentEmail : Fragment() {

    private lateinit var binding: FragmentCadastroEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroEmailBinding.inflate(inflater, container, false)
        val email =  binding.email
        val senha = binding.senha
        val confirmar = binding.confirmarSenha


        binding.nextButton1.setOnClickListener{

            Log.d("MeuFragmentDestino", "Dados teste: ")

        }
        return binding.root
    }
}