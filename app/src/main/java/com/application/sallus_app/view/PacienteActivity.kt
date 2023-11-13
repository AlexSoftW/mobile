package com.application.sallus_app.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityPacienteBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.view.fragments.FragmentFoods
import com.application.sallus_app.view.fragmentsPaciente.FragmentDiarioAlimentarPaciente
import com.application.sallus_app.view.fragmentsPaciente.FragmentHistoricoAlimentarPaciente
import com.application.sallus_app.view.fragmentsPaciente.FragmentPaciente
import com.application.sallus_app.view.fragmentsPaciente.FragmentTodosNutricionistas
import com.application.sallus_app.viewmodel.PacienteViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class PacienteActivity : AppCompatActivity() {

    private val pacienteViewModel: PacienteViewModel by viewModel()
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
        setupObservers()
    }

    private fun setupView() {

        val fragmentHome = FragmentPaciente(pacienteViewModel)
        replaceFragmentManager(fragmentHome)

        val redColor = ContextCompat.getColor(this, R.color.red_default)
        binding.includeBadgePaciente.imagebuttonHomePaciente.setColorFilter(redColor)

        binding.includeToolbarHomePaciente.imageviewCustomerToolbarPages.setImageResource(
            R.mipmap.imagem_profile_paciente_default
        )

        binding.includeToolbarHomePaciente.textviewTagToolbarPages.text = "Paciente"

        binding.includeToolbarHomePaciente.imagebuttonSettingsToolbarPages.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        binding.includeBadgePaciente.imagebuttonHomePaciente.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgePaciente.imagebuttonHomePaciente.setColorFilter(redColor)
            replaceFragmentManager(FragmentPaciente(pacienteViewModel))
        }

        binding.includeBadgePaciente.imagebuttonNutritionistPaciente.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgePaciente.imagebuttonNutritionistPaciente.setColorFilter(redColor)
            replaceFragmentManager(FragmentTodosNutricionistas())
        }

        binding.includeBadgePaciente.imagebuttonFoodPaciente.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgePaciente.imagebuttonFoodPaciente.setColorFilter(redColor)
            replaceFragmentManager(FragmentFoods())
        }

        binding.includeBadgePaciente.imagebuttonDiarioAlimentarPatient.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgePaciente.imagebuttonDiarioAlimentarPatient.setColorFilter(redColor)
            replaceFragmentManager(FragmentDiarioAlimentarPaciente())
        }

        binding.includeBadgePaciente.imagebuttonHistoricoPatient.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgePaciente.imagebuttonHistoricoPatient.setColorFilter(redColor)
            replaceFragmentManager(FragmentHistoricoAlimentarPaciente())
        }

    }

    private fun setupObservers() {
        val redColor = ContextCompat.getColor(this, R.color.red_default)

        pacienteViewModel.corAtual.observe(this) {
            when (it) {
                1 -> {
                    restoreOriginColor()
                    binding.includeBadgePaciente.imagebuttonNutritionistPaciente.setColorFilter(
                        redColor
                    )
                }

                2 -> {
                    restoreOriginColor()
                    binding.includeBadgePaciente.imagebuttonFoodPaciente.setColorFilter(redColor)
                }

                3 -> {
                    restoreOriginColor()
                    binding.includeBadgePaciente.imagebuttonDiarioAlimentarPatient.setColorFilter(
                        redColor
                    )
                }

                4 -> {
                    restoreOriginColor()
                    binding.includeBadgePaciente.imagebuttonHistoricoPatient.setColorFilter(redColor)
                }
            }
            Log.i("tagCorId", "id da cor atual: $it")
        }

    }

    fun restoreOriginColor() {
        val originalColor = ContextCompat.getColor(this, R.color.black_100)

        binding.includeBadgePaciente.imagebuttonHomePaciente.setColorFilter(originalColor)
        binding.includeBadgePaciente.imagebuttonNutritionistPaciente.setColorFilter(originalColor)
        binding.includeBadgePaciente.imagebuttonFoodPaciente.setColorFilter(originalColor)
        binding.includeBadgePaciente.imagebuttonDiarioAlimentarPatient.setColorFilter(originalColor)
        binding.includeBadgePaciente.imagebuttonHistoricoPatient.setColorFilter(originalColor)
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