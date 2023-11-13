package com.application.sallus_app.view.fragmentsNutricionista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentHomeNutricionistaBinding
import com.application.sallus_app.view.fragments.FragmentFoods
import com.application.sallus_app.viewmodel.NutritionistViewModel

class FragmentNutritionist(private val nutritionistViewModel: NutritionistViewModel) : Fragment() {
    private lateinit var binding: FragmentHomeNutricionistaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeNutricionistaBinding.inflate(inflater, container, false)
        setupView()

        return binding.root
    }

    private fun setupView() {
        binding.buttonCardOneRoutine.setOnClickListener {
            nutritionistViewModel.alterarCorBadgeNutricionista(1)
            fragmentReplaceManager(FragmentYoursPatients())
        }

        binding.buttonCardTwoRoutine.setOnClickListener {
            nutritionistViewModel.alterarCorBadgeNutricionista(2)
            fragmentReplaceManager(FragmentFoods())
        }

        binding.buttonCardThreeRoutine.setOnClickListener {
            nutritionistViewModel.alterarCorBadgeNutricionista(3)
            fragmentReplaceManager(FragmentAddFood())
        }

        binding.buttonCardFourRoutine.setOnClickListener {
            nutritionistViewModel.alterarCorBadgeNutricionista(4)
            fragmentReplaceManager(FragmentSelectFoodCreateDiary())
        }
    }

    private fun fragmentReplaceManager(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_nutricionista, fragment)
            .addToBackStack(null)
            .commit()
    }
}