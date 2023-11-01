package com.application.sallus_app.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityPacienteBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.view.fragments.FragmentFoods
import com.application.sallus_app.view.fragments.FragmentPaciente
import com.application.sallus_app.view.fragmentsNutricionista.FragmentYoursPatients
import com.google.gson.Gson

class PacienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPacienteBinding
//    private lateinit var dadosPaciente: PacienteData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val dadosPacienteEmString = intent.getStringExtra("pacienteDataValue")
//        dadosPaciente = tratarPacienteJsonToData(dadosPacienteEmString!!)

//        binding.includeToolbarHomePaciente.textviewNameCustomerToolbarPages.text =
//            dadosPaciente.nome

        setupView()
    }

    private fun setupView() {
        val fragmentHome = FragmentPaciente()
        replaceFragmentManager(fragmentHome)

        binding.includeToolbarHomePaciente.imageviewCustomerToolbarPages.setImageResource(
            R.mipmap.imagem_profile_paciente_default
        )

        binding.includeToolbarHomePaciente.textviewNameCustomerToolbarPages.text = "Paciente"

        binding.includeToolbarHomePaciente.textviewTagToolbarPages.text = "Paciente"

        binding.includeBadgePaciente.imagebuttonHomePaciente.setOnClickListener {
            replaceFragmentManager(FragmentPaciente())
        }

        binding.includeToolbarHomePaciente.imagebuttonSettingsToolbarPages.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        binding.includeBadgePaciente.imagebuttonNutritionistPaciente.setOnClickListener {
            replaceFragmentManager(FragmentYoursPatients())
        }

        binding.includeBadgePaciente.imagebuttonFoodPaciente.setOnClickListener {
            replaceFragmentManager(FragmentFoods())
        }

        binding.includeBadgePaciente.imagebuttonDiarioAlimentarPatient.setOnClickListener {
            //tela em andamento...
        }

        binding.includeBadgePaciente.imagebuttonHistoricoPatient.setOnClickListener {
            //tela em andamento...
        }

    }

    fun replaceFragmentManager(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_paciente, fragment)
        fragmentTransaction.commit()
    }

    fun tratarPacienteJsonToData(paciente: String): PacienteData {
        val gson = Gson()
        val pacienteData: PacienteData =
            gson.fromJson(paciente, PacienteData::class.java)
        return pacienteData
    }
}