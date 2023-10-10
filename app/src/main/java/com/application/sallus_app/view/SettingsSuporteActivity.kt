package com.application.sallus_app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.databinding.ActivitySettingsSuporteBinding

class SettingsSuporteActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsSuporteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsSuporteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}