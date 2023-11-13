package com.application.sallus_app.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityPacienteBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.view.fragments.FragmentFoods
import com.application.sallus_app.view.fragmentsNutricionista.FragmentNutritionist
import com.application.sallus_app.view.fragmentsPaciente.FragmentPaciente
import com.application.sallus_app.view.fragmentsNutricionista.FragmentYoursPatients
import com.application.sallus_app.view.fragmentsPaciente.FragmentDiarioAlimentarPaciente
import com.application.sallus_app.view.fragmentsPaciente.FragmentHistoricoAlimentarPaciente
import com.application.sallus_app.view.fragmentsPaciente.FragmentTodosNutricionistas
import com.application.sallus_app.viewmodel.PacienteViewModel
import com.google.gson.Gson

class PacienteActivity : AppCompatActivity() {

    private lateinit var pacienteViewModel: PacienteViewModel
    private lateinit var binding: ActivityPacienteBinding
    private lateinit var dadosPaciente: PacienteData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dadosPacienteEmString = intent.getStringExtra("pacienteDataValue")
        dadosPaciente = tratarPacienteJsonToData(dadosPacienteEmString!!)

        Log.i("logiDadosPaciente", "dados: $dadosPaciente")

        binding.includeToolbarHomePaciente.textviewNameCustomerToolbarPages.text =
                dadosPaciente.nome

        setupView()
    }

    private fun setupView() {
        val fragmentHome = FragmentPaciente()
        replaceFragmentManager(fragmentHome)

        binding.includeToolbarHomePaciente.imageviewCustomerToolbarPages.setImageResource(
            R.mipmap.imagem_profile_paciente_default
        )

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
            replaceFragmentManager(FragmentTodosNutricionistas())
        }

        binding.includeBadgePaciente.imagebuttonFoodPaciente.setOnClickListener {
            replaceFragmentManager(FragmentFoods())
        }

        binding.includeBadgePaciente.imagebuttonDiarioAlimentarPatient.setOnClickListener {
            replaceFragmentManager(FragmentDiarioAlimentarPaciente())
        }

        binding.includeBadgePaciente.imagebuttonHistoricoPatient.setOnClickListener {
            replaceFragmentManager(FragmentHistoricoAlimentarPaciente())
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