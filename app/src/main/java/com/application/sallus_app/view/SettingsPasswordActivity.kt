package com.application.sallus_app.view

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivitySettingsPasswordBinding
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.model.UsuarioData
import com.application.sallus_app.viewmodel.NutritionistViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsPasswordActivity : AppCompatActivity() {

    private val nutricionistViewModel: NutritionistViewModel by viewModel()
    private lateinit var binding: ActivitySettingsPasswordBinding
    private lateinit var dadosNutricionista : NutritionistData;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dadosNutri = intent.getStringExtra("nutricionistaDataSenha")
        dadosNutricionista = tratarNutricionistaJsonToData(dadosNutri!!)

        setupView()
    }

    fun setupView(){
        binding.includeToolbarSettings.textviewToolbarSettings.text = "Alteração de senha"

        val activity = this
        binding.includeToolbarSettings.buttonBackToolbarSettings.setOnClickListener {
            activity.finish()
        }

        binding.btnVoltar.setOnClickListener {
            activity.finish()
        }

        binding.btnSalvarAlteracao.setOnClickListener {
            val inputEditTextSenhaNova = findViewById<EditText>(R.id.edittext_nova_senha)
            val inputEditTextConfirmSenha = findViewById<EditText>(R.id.edittext_confirmar_nova_senha)

            val email = dadosNutricionista.email
            val senhaNova = inputEditTextSenhaNova.text.toString()
            val confirmacaoSenha = inputEditTextConfirmSenha.text.toString()

            val data = UsuarioData(email, senhaNova)
            nutricionistViewModel.alterarSenha(data)
        }
    }

    fun tratarNutricionistaJsonToData(nutricionista: String): NutritionistData {
        val gson = Gson()
        val nutricionistaData: NutritionistData =
            gson.fromJson(nutricionista, NutritionistData::class.java)
        return nutricionistaData
    }

}