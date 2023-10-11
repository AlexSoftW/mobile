package com.application.sallus_app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.databinding.ActivitySettingsPasswordBinding

class SettingsPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}