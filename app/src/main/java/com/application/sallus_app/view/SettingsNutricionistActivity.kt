package com.application.sallus_app.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.databinding.ActivitySettingsNutricionistaBinding
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.viewmodel.SettingsViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsNutricionistActivity : AppCompatActivity() {

    private val settingsViewModel: SettingsViewModel by viewModel()
    private lateinit var binding: ActivitySettingsNutricionistaBinding
    private lateinit var dadosNutricionista: NutritionistData;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsNutricionistaBinding.inflate(layoutInflater)
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
            val intent = Intent(this, SettingsPerfilNutricionistActivity::class.java)
            val gson = Gson()
            val json = gson.toJson(dadosNutricionista)
            intent.putExtra("nutricionistaDataPerfil", json)
            startActivity(intent)
        }

        binding.optionAlterarSenha.setOnClickListener() {
            val intent = Intent(this, SettingsPasswordNutricionistActivity::class.java)
            val gson = Gson()
            val json = gson.toJson(dadosNutricionista)
            intent.putExtra("nutricionistaDataSenha", json)
            startActivity(intent)
        }

        binding.optionSuporte.setOnClickListener() {
            val intent = Intent(this, SettingsSuporteNutricionistActivity::class.java)
            startActivity(intent)
        }

        binding.optionSair.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
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