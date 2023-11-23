package com.application.sallus_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityEsqueceuSenhaBinding
import com.application.sallus_app.model.EsqueceuSenhaData
import com.application.sallus_app.viewmodel.EsqueceuSenhaViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EsqueceuSenhaActivity : AppCompatActivity() {
    private val esqueceuSenhaViewModel: EsqueceuSenhaViewModel by viewModel()
    private lateinit var binding: ActivityEsqueceuSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEsqueceuSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.includeToolbarEsqueceuSenha.buttonBackTelaEsqueceuSenha.setOnClickListener {
            finish()
        }

        binding.buttonSolicitarSenha.setOnClickListener {
            Log.d("MeuApp1", "Botão clicado")
            val textInputEditTextEmailCadastrado =
                findViewById<EditText>(R.id.textFieldEmailCadastrado)

            val emailCadastrado = textInputEditTextEmailCadastrado.text.toString()
            Log.d("MeuApp4", "Valor de email: $emailCadastrado")

            Log.d("MeuApp4", "API Acionada")
            esqueceuSenhaViewModel.esqueceuSenha(emailCadastrado)
        }
    }
}