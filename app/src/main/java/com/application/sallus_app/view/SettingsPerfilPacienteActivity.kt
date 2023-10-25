package com.application.sallus_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityPerfilPacienteBinding
import com.application.sallus_app.databinding.ActivitySettingsPacienteBinding
import com.application.sallus_app.model.NutritionistData
import com.google.gson.Gson

class SettingsPerfilPacienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilPacienteBinding
    private lateinit var dadosNutricionista : NutritionistData;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    fun setupView(){

        val dadosNutri = intent.getStringExtra("nutricionistaDataPerfil")
        dadosNutricionista = tratarNutricionistaJsonToData(dadosNutri!!)

        binding.includeToolbarSettings.textviewToolbarSettings.text = "Perfil"
        binding.nomeUsuario.text = dadosNutricionista.nome
        binding.edittextEmailPacienteSettings.setText(dadosNutricionista.email)
        binding.edittextNomePacienteSettings.setText(dadosNutricionista.nome)
        binding.edittextSenhaPacienteSettings.setText(dadosNutricionista.senha)

        val activity = this
        binding.includeToolbarSettings.buttonBackToolbarSettings.setOnClickListener {
            activity.finish()
        }

        binding.btnVoltar.setOnClickListener {
            activity.finish()
        }

        binding.btnRedefinirSenha.setOnClickListener {
            val intent = Intent(this, SettingsPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    fun tratarNutricionistaJsonToData(nutricionista: String): NutritionistData {
        val gson = Gson()
        val nutricionistaData: NutritionistData =
            gson.fromJson(nutricionista, NutritionistData::class.java)
        return nutricionistaData
    }
}