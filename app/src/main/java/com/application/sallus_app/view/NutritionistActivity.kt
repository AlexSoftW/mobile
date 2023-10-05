package com.application.sallus_app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityNutricionistaBinding
import com.application.sallus_app.view.fragments.FragmentNutritionist
import com.application.sallus_app.viewmodel.NutritionistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NutritionistActivity : AppCompatActivity() {

    private val nutritionistViewModel: NutritionistViewModel by viewModel()
    private lateinit var binding: ActivityNutricionistaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutricionista)
        setupView()
        setupObservers()

        val fragmentHome = FragmentNutritionist()
        replaceFragmentManager(fragmentHome)
    }

    fun replaceFragmentManager(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_nutricionista, fragment)
        fragmentTransaction.commit()
    }

    fun setupView() {
        //Tudo que for manipular da view
        // (se quiser inicialiar alguma textView com um valor específico, etc)
        // (adicionar a ação do botão para ir para outro fragment é aqui tbm)
    }

    fun setupObservers() {
        nutritionistViewModel.fetchTodosNutricionistas()
        nutritionistViewModel.obterTodosNutricionista().observe(this) {

        }
    }


}