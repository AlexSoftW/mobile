package com.application.sallus_app.view;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityLoginBinding
import com.application.sallus_app.model.UsuarioData
import com.application.sallus_app.viewmodel.LoginViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // pedro@oi.com -> paciente
        // Pedro132

        //teste@teste -> nutricionista
        //teste

//        Entrar na tela de nutricionista sem precisar fazer login para TESTES!!!!!
//        binding.buttonLogin.setOnClickListener {
//            val intent = Intent(this, NutritionistActivity::class.java)
//            startActivity(intent)
//        }

        binding.buttonLogin.setOnClickListener {
            Log.d("MeuApp1", "Botão clicado")
            val textInputEditTextEmail = findViewById<EditText>(R.id.text_field_email)
            val textInputEditTextSenha = findViewById<EditText>(R.id.text_field_senha)

            val email = textInputEditTextEmail.text.toString()
            val senha = textInputEditTextSenha.text.toString()
            Log.d("MeuApp4", "Valor de email: $email")
            Log.d("MeuApp4", "Valor de senha: $senha")

            val dadosUsuario = UsuarioData(email, senha)
            loginViewModel.loginUsuario(dadosUsuario)
            loginViewModel.controle.observe(this) {
                when (it) {
                    1 -> {
                        val intent = Intent(this, PacienteActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        startActivity(intent)
                        finish()
                    }

                    2 -> {
                        val intent = Intent(this, NutritionistActivity::class.java)
                        val gson = Gson()
                        val json = gson.toJson(loginViewModel.nutricionistaData.value)
                        intent.putExtra("nutricionistaDataValue", json)
                        startActivity(intent)
                        finish()
                    }

                    else -> {
                        Log.d("Error", "aconteceu um erro ao tentar logar")
                    }
                }
            }
        }

        binding.textviewButtonCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}