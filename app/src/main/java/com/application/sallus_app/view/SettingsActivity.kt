package com.application.sallus_app.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivitySettingsPacienteBinding
import com.application.sallus_app.view.fragments.FragmentSettingsOptions
import com.application.sallus_app.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {

    private val settingsViewModel: SettingsViewModel by viewModel()
    private lateinit var binding: ActivitySettingsPacienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun replaceFragmentManager(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_nutricionista, fragment)
        fragmentTransaction.commit()
    }

    fun setupView() {
        val fragmentHome = FragmentSettingsOptions()
        replaceFragmentManager(fragmentHome)

        binding.optionPerfil.setOnClickListener(){
            val intent = Intent(this, SettingsPerfilPacienteActivity::class.java)
            startActivity(intent)
        }

        binding.optionNotificacao.setOnClickListener(){
            // ~~
        }

        binding.optionAlterarSenha.setOnClickListener(){
            val intent = Intent(this, SettingsPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.optionSuporte.setOnClickListener(){
            val intent = Intent(this, SettingsSuporteActivity::class.java)
            startActivity(intent)
        }

        binding.optionSair.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSair.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}