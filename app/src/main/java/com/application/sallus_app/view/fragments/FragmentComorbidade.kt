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
    val bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments
        if (args != null) {
            val nome = args.getString("Nome")
            val telefone = args.getString("Telefone")
            val endereco = args.getString("Endereco")
            val genero = args.getString("Genero")
            bundle.putString("Nome" ,nome)
            bundle.putString("Telefone" ,telefone)
            bundle.putString("Endereco" ,endereco)
            bundle.putString("Genero" ,genero)

            Log.d("MeuFragmentDestino", "Dados recebidos: $nome $endereco $telefone $genero")
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
        val fragmentDestino = FragmentEmail()

        binding.nextButton1.setOnClickListener{

            bundle.putBoolean("Diabete", binding.diabete.isChecked)
            bundle.putBoolean("Colesterol", binding.colesterol.isChecked)
            bundle.putBoolean("Hipertensao", binding.hipertensao.isChecked)
            bundle.putBoolean("Nenhum", binding.nenhum.isChecked)
            fragmentDestino.arguments = bundle
            Log.d("Valores CheckBoxes", "Diabete = ${diabete.isChecked}, Hipertensao = ${hipertensao.isChecked}")
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_cadastro, fragmentDestino)
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }
}