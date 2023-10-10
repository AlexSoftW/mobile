package com.application.sallus_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityPerfilPacienteBinding
import com.application.sallus_app.databinding.ActivitySettingsPacienteBinding

class SettingsPerfilPacienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilPacienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}