package com.application.sallus_app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityCadastroBinding
import com.application.sallus_app.view.fragments.FragmentEscolherPerfil

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.includeToolbar.buttonBackToolbarSettings.setOnClickListener {
            retornarFragment()
        }

        binding.textviewButtonSairCriarConta.setOnClickListener {
            finish()
        }

        val primeiroFragment = FragmentEscolherPerfil()
        replaceFragmentManager(primeiroFragment)
    }

    fun replaceFragmentManager(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_cadastro, fragment)
        fragmentTransaction.commit()
    }

    fun retornarFragment() {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStack()
    }
}


