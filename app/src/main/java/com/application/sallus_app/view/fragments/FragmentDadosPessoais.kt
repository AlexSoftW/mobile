package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.util.Log
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
        val nome = binding.nomePaciente.editText.toString()
        val telefone = binding.telefone.editText.toString()
        val endereco = binding.endereco.editText.toString()
        val genero = binding.genero.editText.toString()
        val fragment = FragmentComorbidade()

        binding.proximo1.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_cadastro, fragment)
                .addToBackStack(null)
                .commit()
            Log.d("MeuFragmentDestino", "Dados teste: $nome $telefone $endereco $genero")
            val fragment = FragmentComorbidade()
            val bundle = Bundle()
            bundle.putString("Data", nome)

            val fragmentDestino = FragmentComorbidade()
            fragmentDestino.arguments = bundle

            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_cadastro, fragmentDestino)
            transaction.addToBackStack(nome)
            transaction.commit()
        }
        return binding.root
    }
}


