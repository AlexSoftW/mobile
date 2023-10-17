package com.application.sallus_app.view;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityHomeBinding
import com.application.sallus_app.databinding.ActivityLoginBinding
import com.application.sallus_app.model.LoginData
import com.application.sallus_app.model.UsuarioData
import com.application.sallus_app.view.MainActivity
import com.application.sallus_app.view.NutritionistActivity
import com.application.sallus_app.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // pedro@oi.com
        // Pedro132

        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, NutritionistActivity::class.java)
            startActivity(intent)
        }

//        binding.buttonLogin.setOnClickListener {
//            Log.d("MeuApp1", "Botão clicado")
//            val textInputEditTextEmail = findViewById<EditText>(R.id.text_field_email)
//            val textInputEditTextSenha = findViewById<EditText>(R.id.text_field_senha)
//
//            val email = textInputEditTextEmail.text.toString()
//            val senha = textInputEditTextSenha.text.toString()
//            Log.d("MeuApp4", "Valor de email: $email")
//            Log.d("MeuApp4", "Valor de senha: $senha")
//
//            val dadosUsuario = UsuarioData(email, senha)
//            loginViewModel.loginUsuario(dadosUsuario)
//            loginViewModel.controle.observe(this){
//                when(it){
//                    1 -> {
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                    }
//                    2 -> {
//                        val intent = Intent(this, NutritionistActivity::class.java)
//                        startActivity(intent)
//                    }
//                    else -> {
//                        Log.d("Gambeta", "deu ganbeta")
//                    }
//                }
//            }
//        }
    }
}