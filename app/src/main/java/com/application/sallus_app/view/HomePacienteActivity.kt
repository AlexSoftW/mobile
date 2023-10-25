package com.application.sallus_app.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityLoginBinding
import com.application.sallus_app.databinding.ActivityPacienteBinding
import com.application.sallus_app.databinding.FragmentItensHomePacienteBinding
import com.application.sallus_app.view.fragments.FragmentFoods
import com.application.sallus_app.view.fragments.FragmentHomePaciente
import com.application.sallus_app.view.fragments.FragmentNutritionist

class HomePacienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPacienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPacienteBinding.inflate(layoutInflater)
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
        val fragmentHome = FragmentHomePaciente()
        replaceFragmentManager(fragmentHome)

        val redColor = ContextCompat.getColor(this, R.color.red_default)

        binding.includeBadgePaciente.imagebuttonHome.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgePaciente.imagebuttonHome.setColorFilter(redColor)
            replaceFragmentManager(FragmentHomePaciente())
        }

        binding.includeBadgePaciente.imagebuttonNutricionista.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgePaciente.imagebuttonNutricionista.setColorFilter(redColor)
            replaceFragmentManager(FragmentNutritionist())
        }

        binding.includeBadgePaciente.imagebuttonFood.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgePaciente.imagebuttonFood.setColorFilter(redColor)
            replaceFragmentManager(FragmentFoods())
        }

        binding.includeBadgePaciente.imagebuttonDiarioAlimentar.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgePaciente.imagebuttonDiarioAlimentar.setColorFilter(redColor)
//            replaceFragmentManager(FragmentDiarioAlimentar())
        }

        binding.includeBadgePaciente.imagebuttonHistorico.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgePaciente.imagebuttonHistorico.setColorFilter(redColor)
//            replaceFragmentManager(FragmentHistorico())
        }
    }

    fun restoreOriginColor() {
        val originalColor = ContextCompat.getColor(this, R.color.black_100)
        binding.includeBadgePaciente.imagebuttonHome.setColorFilter(originalColor)
        binding.includeBadgePaciente.imagebuttonNutricionista.setColorFilter(originalColor)
        binding.includeBadgePaciente.imagebuttonFood.setColorFilter(originalColor)
        binding.includeBadgePaciente.imagebuttonDiarioAlimentar.setColorFilter(originalColor)
        binding.includeBadgePaciente.imagebuttonHistorico.setColorFilter(originalColor)
    }


}