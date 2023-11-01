package com.application.sallus_app.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityNutricionistaBinding
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.view.fragments.FragmentAddFood
import com.application.sallus_app.view.fragments.FragmentFoods
import com.application.sallus_app.view.fragmentsNutricionista.FragmentNutritionist
import com.application.sallus_app.view.fragmentsNutricionista.FragmentSelectFoodCreateDiary
import com.application.sallus_app.view.fragmentsNutricionista.FragmentYoursPatients
import com.application.sallus_app.viewmodel.NutritionistViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class NutritionistActivity : AppCompatActivity() {

    private val nutritionistViewModel: NutritionistViewModel by viewModel()
    private lateinit var binding: ActivityNutricionistaBinding
    private lateinit var dadosNutricionista: NutritionistData;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutricionistaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dadosNutricionistaEmString = intent.getStringExtra("nutricionistaDataValue")
        dadosNutricionista = tratarNutricionistaJsonToData(dadosNutricionistaEmString!!)

        Log.i("logiDadosNutri", "dados: $dadosNutricionista")
        binding.includeToolbarPages.textviewNameCustomerToolbarPages.text = dadosNutricionista.nome

        setupView()
        setupObservers()
    }

    fun replaceFragmentManager(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_nutricionista, fragment)
        fragmentTransaction.commit()
    }

    //Tudo que for manipular da view
    // (se quiser inicialiar alguma textView com um valor específico, etc)
    // (adicionar a ação do botão para ir para outro fragment é aqui tbm)
    fun setupView() {
        val fragmentHome = FragmentNutritionist()
        replaceFragmentManager(fragmentHome)

        binding.includeToolbarPages.imageviewCustomerToolbarPages.setImageResource(
            R.mipmap.imagem_profile_nutricionista_default
        )

        val redColor = ContextCompat.getColor(this, R.color.red_default)

        binding.includeBadgeNutricionista.imagebuttonHomeNutritionist.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgeNutricionista.imagebuttonHomeNutritionist.setColorFilter(redColor)
            replaceFragmentManager(FragmentNutritionist())
        }

        binding.includeBadgeNutricionista.imagebuttonPatientsNutritionist.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgeNutricionista.imagebuttonPatientsNutritionist.setColorFilter(
                redColor
            )
            replaceFragmentManager(FragmentYoursPatients())
        }

        binding.includeBadgeNutricionista.imagebuttonFoodNutritionist.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgeNutricionista.imagebuttonFoodNutritionist.setColorFilter(redColor)
            replaceFragmentManager(FragmentFoods())
        }

        binding.includeBadgeNutricionista.imagebuttonAddFoodNutritionist.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgeNutricionista.imagebuttonAddFoodNutritionist.setColorFilter(redColor)
            replaceFragmentManager(FragmentAddFood())
        }

        binding.includeBadgeNutricionista.imagebuttonAddRoutineNutritionist.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgeNutricionista.imagebuttonAddRoutineNutritionist.setColorFilter(
                redColor
            )
            replaceFragmentManager(FragmentSelectFoodCreateDiary())
        }

        binding.includeToolbarPages.imagebuttonSettingsToolbarPages.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            val gson = Gson()
            val json = gson.toJson(dadosNutricionista)
            intent.putExtra("nutricionistaDataPerfil", json)
            startActivity(intent)
        }
    }

    fun setupObservers() {
        nutritionistViewModel.fetchTodosNutricionistas()
    }

    fun restoreOriginColor() {
        val originalColor = ContextCompat.getColor(this, R.color.black_100)

        binding.includeBadgeNutricionista.imagebuttonHomeNutritionist.setColorFilter(
            originalColor
        )
        binding.includeBadgeNutricionista.imagebuttonPatientsNutritionist.setColorFilter(
            originalColor
        )
        binding.includeBadgeNutricionista.imagebuttonFoodNutritionist.setColorFilter(
            originalColor
        )
        binding.includeBadgeNutricionista.imagebuttonAddFoodNutritionist.setColorFilter(
            originalColor
        )
        binding.includeBadgeNutricionista.imagebuttonAddRoutineNutritionist.setColorFilter(
            originalColor
        )
    }

    fun tratarNutricionistaJsonToData(nutricionista: String): NutritionistData {
        val gson = Gson()
        val nutricionistaData: NutritionistData =
            gson.fromJson(nutricionista, NutritionistData::class.java)
        return nutricionistaData
    }
}