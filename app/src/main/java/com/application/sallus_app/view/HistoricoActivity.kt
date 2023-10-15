package com.application.sallus_app.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityNutricionistaBinding
import com.application.sallus_app.databinding.FragmentHistoricoBinding
import com.application.sallus_app.databinding.ItemRecyclerViewHistoricoBinding
import com.application.sallus_app.view.fragments.FragmentAddFood
import com.application.sallus_app.view.fragments.FragmentFoods
import com.application.sallus_app.view.fragments.FragmentNutritionist
import com.application.sallus_app.view.fragments.FragmentSelectFoodCreateDiary
import com.application.sallus_app.view.fragments.FragmentYoursPatients
import com.application.sallus_app.viewmodel.HistoricoViewModel
import com.application.sallus_app.viewmodel.NutritionistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoricoActivity : AppCompatActivity() {

    private val HistoricoViewModel: HistoricoViewModel by viewModel()
    private lateinit var binding: ItemRecyclerViewHistoricoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemRecyclerViewHistoricoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun replaceFragmentManager(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_paciente, fragment)
        fragmentTransaction.commit()

        setupView()
        setupObservers()
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
        HistoricoViewModel.fetchTodosHistoricos()

        HistoricoViewModel.listHistorico.observe(this) {
            binding.constraintLayout.textviewTagIndicacaoItemAlimento.text = it[0].alimentos

//            binding.includeToolbarPages.textviewNameCustomerToolbarPages.text = it[0].nome
        }
    }
}