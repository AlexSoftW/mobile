package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentCadastroBinding
import com.application.sallus_app.databinding.FragmentCadastroComorbidadeBinding
import com.application.sallus_app.databinding.FragmentCadastroEmailBinding

class FragmentComorbidade : Fragment(){

    private lateinit var binding: FragmentCadastroComorbidadeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments
        if (args != null) {
            val dados = args.getString("chave")

            // Exibe os dados no console
            Log.d("MeuFragmentDestino", "Dados recebidos: $dados")
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentCadastroComorbidadeBinding.inflate(inflater, container, false)
        val diabete =  binding.diabete
        val colesterol = binding.colesterol
        val hipertensao = binding.hipertensao
        val nenhum = binding.nenhum
        val fragment = FragmentEmail()

        binding.nextButton1.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_cadastro, fragment)
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }
}