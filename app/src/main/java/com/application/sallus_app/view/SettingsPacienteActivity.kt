package com.application.sallus_app.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.databinding.ActivitySettingsPacienteBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.viewmodel.SettingsViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsPacienteActivity : AppCompatActivity() {
    private val settingsViewModel: SettingsViewModel by viewModel()
    private lateinit var binding: ActivitySettingsPacienteBinding
    private lateinit var dadosPaciente: PacienteData;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    fun setupView() {
        val dadosDoPaciente = intent.getStringExtra("pacienteDataPerfil")
        dadosPaciente = tratarPacienteJsonToData(dadosDoPaciente!!)
        val activity = this

        binding.includeToolbarSettings.buttonBackToolbarSettings.setOnClickListener {
            activity.finish()
        }

        binding.optionPerfil.setOnClickListener() {
            val intent = Intent(this, SettingsPerfilPacienteActivity::class.java)
            val gson = Gson()
            val json = gson.toJson(dadosPaciente)
            intent.putExtra("pacienteDataPerfil", json)
            startActivity(intent)
        }

        binding.optionAlterarSenha.setOnClickListener() {
            val intent = Intent(this, SettingsPasswordPacienteActivity::class.java)
            val gson = Gson()
            val json = gson.toJson(dadosPaciente)
            intent.putExtra("pacienteDataSenha", json)
            startActivity(intent)
        }

        binding.optionSuporte.setOnClickListener() {
            val intent = Intent(this, SettingsSuportePacienteActivity::class.java)
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

    fun tratarPacienteJsonToData(paciente: String): PacienteData {
        val gson = Gson()
        val pacienteData: PacienteData =
            gson.fromJson(paciente, PacienteData::class.java)
        return pacienteData;
    }

}