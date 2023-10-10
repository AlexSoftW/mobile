package com.application.sallus_app.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityHomeBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupView()
    }

    fun setupView() {
        binding.buttonHomeIndex.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}