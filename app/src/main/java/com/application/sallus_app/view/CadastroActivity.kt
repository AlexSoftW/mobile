package com.application.sallus_app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityCadastroBinding
import com.application.sallus_app.databinding.ActivityNutricionistaBinding
import com.application.sallus_app.view.fragments.FragmentAddFood
import com.application.sallus_app.view.fragments.FragmentCreateRoutine
import com.application.sallus_app.view.fragments.FragmentFoods
import com.application.sallus_app.view.fragments.FragmentNutritionist
import com.application.sallus_app.view.fragments.FragmentYoursPatients
import com.application.sallus_app.viewmodel.CadastroViewModel
import com.application.sallus_app.viewmodel.NutritionistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastroActivity : AppCompatActivity() {
    private val cadastroViewModel: CadastroViewModel by viewModel()
    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setupView()
       // cadastroViewModel.addingNewPaciente()
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
    /*fun setupView() {
        val fragmentHome = FragmentNutritionist()
        replaceFragmentManager(fragmentHome)



        binding.includeBadgeNutricionista.imagebuttonPatients.setOnClickListener {
            replaceFragmentManager(FragmentYoursPatients())
        }

    }*/


}