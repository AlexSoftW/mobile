package com.application.sallus_app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.databinding.ActivityHomeBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    fun setupView() {
        //Colocar o click do botão para ir para a próxima tela
    }

}