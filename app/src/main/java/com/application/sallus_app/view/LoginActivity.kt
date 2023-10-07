package com.application.sallus_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityHomeBinding
import com.application.sallus_app.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, NutritionistActivity::class.java)
            startActivity(intent)
        }
    }
}