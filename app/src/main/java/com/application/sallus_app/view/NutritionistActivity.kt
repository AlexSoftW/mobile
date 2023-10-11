package com.application.sallus_app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityNutricionistaBinding
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.view.fragments.FragmentAddFood
import com.application.sallus_app.view.fragments.FragmentCreateRoutine
import com.application.sallus_app.view.fragments.FragmentFoods
import com.application.sallus_app.view.fragments.FragmentNutritionist
import com.application.sallus_app.view.fragments.FragmentSelectFoodCreateDiary
import com.application.sallus_app.view.fragments.FragmentYoursPatients
import com.application.sallus_app.viewmodel.NutritionistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NutritionistActivity : AppCompatActivity() {

    private val nutritionistViewModel: NutritionistViewModel by viewModel()
    private lateinit var binding: ActivityNutricionistaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutricionistaBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.includeBadgeNutricionista.imagebuttonHome.setOnClickListener {
            replaceFragmentManager(FragmentNutritionist())
        }

        binding.includeBadgeNutricionista.imagebuttonPatients.setOnClickListener {
            replaceFragmentManager(FragmentYoursPatients())
        }

        binding.includeBadgeNutricionista.imagebuttonFood.setOnClickListener {
            replaceFragmentManager(FragmentFoods())
        }

        binding.includeBadgeNutricionista.imagebuttonAddFood.setOnClickListener {
            replaceFragmentManager(FragmentAddFood())
        }

        binding.includeBadgeNutricionista.imagebuttonAddRoutine.setOnClickListener {
            replaceFragmentManager(FragmentSelectFoodCreateDiary())
        }
    }

    fun setupObservers() {
        nutritionistViewModel.fetchTodosNutricionistas()

        nutritionistViewModel.listNutricionista.observe(this) {
            binding.includeToolbarPages.textviewNameCustomerToolbarPages.text = it[0].nome
        }
    }


}