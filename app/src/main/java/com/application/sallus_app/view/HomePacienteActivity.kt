package com.application.sallus_app.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityLoginBinding
import com.application.sallus_app.databinding.FragmentItensHomePacienteBinding
import com.application.sallus_app.view.fragments.FragmentNutritionist

class HomePacienteActivity : AppCompatActivity() {

    private lateinit var binding: FragmentItensHomePacienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentItensHomePacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupView()

    }

    fun replaceFragmentManager(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_paciente, fragment)
        fragmentTransaction.commit()
    }

    fun setupView() {
        val fragmentHome = FragmentNutritionist()
        replaceFragmentManager(fragmentHome)

        val redColor = ContextCompat.getColor(this, R.color.red_default)

        binding.btnNutricionistas.setOnClickListener {

        }

    }

}