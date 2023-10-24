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
import com.application.sallus_app.view.fragments.FragmentNutritionist
import com.application.sallus_app.view.fragments.FragmentSelectFoodCreateDiary
import com.application.sallus_app.view.fragments.FragmentYoursPatients
import com.application.sallus_app.viewmodel.NutritionistViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class NutritionistActivity : AppCompatActivity() {

    private val nutritionistViewModel: NutritionistViewModel by viewModel()
    private lateinit var binding: ActivityNutricionistaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutricionistaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dadosNutri = intent.getStringExtra("nutricionintDataValue")
        val dadosNutricionista = tratarNutricionistaJsonToData(dadosNutri!!)

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

        val redColor = ContextCompat.getColor(this, R.color.red_default)

        binding.includeBadgeNutricionista.imagebuttonHome.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgeNutricionista.imagebuttonHome.setColorFilter(redColor)
            replaceFragmentManager(FragmentNutritionist())
        }

        binding.includeBadgeNutricionista.imagebuttonPatients.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgeNutricionista.imagebuttonPatients.setColorFilter(redColor)
            replaceFragmentManager(FragmentYoursPatients())
        }

        binding.includeBadgeNutricionista.imagebuttonFood.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgeNutricionista.imagebuttonFood.setColorFilter(redColor)
            replaceFragmentManager(FragmentFoods())
        }

        binding.includeBadgeNutricionista.imagebuttonAddFood.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgeNutricionista.imagebuttonAddFood.setColorFilter(redColor)
            replaceFragmentManager(FragmentAddFood())
        }

        binding.includeBadgeNutricionista.imagebuttonAddRoutine.setOnClickListener {
            restoreOriginColor()
            binding.includeBadgeNutricionista.imagebuttonAddRoutine.setColorFilter(redColor)
            replaceFragmentManager(FragmentSelectFoodCreateDiary())
        }

        binding.includeToolbarPages.imagebuttonSettingsToolbarPages.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    fun setupObservers() {
//        val dadosDoNutricionista = loginViewModel.nutricionistaData.value
        nutritionistViewModel.fetchTodosNutricionistas()

    }


    fun restoreOriginColor() {
        val originalColor = ContextCompat.getColor(this, R.color.black_100)
        binding.includeBadgeNutricionista.imagebuttonHome.setColorFilter(originalColor)
        binding.includeBadgeNutricionista.imagebuttonPatients.setColorFilter(originalColor)
        binding.includeBadgeNutricionista.imagebuttonFood.setColorFilter(originalColor)
        binding.includeBadgeNutricionista.imagebuttonAddFood.setColorFilter(originalColor)
        binding.includeBadgeNutricionista.imagebuttonAddRoutine.setColorFilter(originalColor)
    }

    fun tratarNutricionistaJsonToData(nutricionista: String): NutritionistData {
        val gson = Gson()
        val nutricionistaData: NutritionistData =
            gson.fromJson(nutricionista, NutritionistData::class.java)
        return nutricionistaData
    }
}