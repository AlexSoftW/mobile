package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentCadastroBinding
import com.application.sallus_app.databinding.FragmentCadastroCrnBinding

class FragmentCrn : Fragment() {
    private lateinit var binding: FragmentCadastroCrnBinding

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
        binding = FragmentCadastroCrnBinding.inflate(inflater, container, false)

        val fragmentDestino = FragmentEmailNutri()

        binding.nextButton1.setOnClickListener {
            val crn = binding.crn.text.toString()


            Log.d("MeuFragmentDestino", "Dados teste: $crn ")


            bundle.putString("Crn", crn)


            fragmentDestino.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_cadastro, fragmentDestino)
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }
}