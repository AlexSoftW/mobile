package com.application.sallus_app.view


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.databinding.ActivitySettingsPacienteBinding
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.viewmodel.SettingsViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {

    private val settingsViewModel: SettingsViewModel by viewModel()
    private lateinit var binding: ActivitySettingsPacienteBinding
    private lateinit var dadosNutricionista: NutritionistData;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    fun setupView() {
        val dadosNutri = intent.getStringExtra("nutricionistaDataPerfil")
        dadosNutricionista = tratarNutricionistaJsonToData(dadosNutri!!)
        val activity = this

        binding.includeToolbarSettings.buttonBackToolbarSettings.setOnClickListener {
            activity.finish()
        }

        binding.optionPerfil.setOnClickListener() {
            val intent = Intent(this, SettingsPerfilPacienteActivity::class.java)
            val gson = Gson()
            val json = gson.toJson(dadosNutricionista)
            intent.putExtra("nutricionistaDataPerfil", json)
            startActivity(intent)
        }

        binding.optionNotificacao.setOnClickListener() {
            // ~~
        }

        binding.optionAlterarSenha.setOnClickListener() {
            val intent = Intent(this, SettingsPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.optionSuporte.setOnClickListener() {
            val intent = Intent(this, SettingsSuporteActivity::class.java)
            startActivity(intent)
        }

        binding.optionSair.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.btnSair.setOnClickListener() {
            finish()
        }

    }

    fun tratarNutricionistaJsonToData(nutricionista: String): NutritionistData {
        val gson = Gson()
        val nutricionistaData: NutritionistData =
            gson.fromJson(nutricionista, NutritionistData::class.java)
        return nutricionistaData
    }
}