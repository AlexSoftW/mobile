package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentCadastroBinding
import com.application.sallus_app.databinding.FragmentCadastroComorbidadeBinding
import com.application.sallus_app.databinding.FragmentHomeNutricionistaBinding

class FragmentDadosPessoais : Fragment(){

    private lateinit var binding: FragmentCadastroBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroBinding.inflate(inflater, container, false)
        val nome = binding.nomePaciente
        val telefone = binding.telefone
        val endereco = binding.endereco
        val genero = binding.genero
        val fragment = FragmentComorbidade()

        binding.proximo1.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_cadastro, fragment)
                .addToBackStack(null)
                .commit()

            val fragment = FragmentComorbidade()
            val bundle = Bundle()
            bundle.putString("Data", nome.toString())

            val fragmentDestino = FragmentComorbidade()
            fragmentDestino.arguments = bundle

            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_cadastro, fragmentDestino)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return binding.root
    }
}


