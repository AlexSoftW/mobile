package com.application.sallus_app.view.fragmentsNutricionista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentHomeNutricionistaBinding
import com.application.sallus_app.view.fragments.FragmentFoods

class FragmentNutritionist : Fragment() {
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
            fragmentReplaceManager(FragmentYoursPatients())
        }

        binding.buttonCardTwoRoutine.setOnClickListener {
            fragmentReplaceManager(FragmentFoods())
        }

        binding.buttonCardThreeRoutine.setOnClickListener {
            fragmentReplaceManager(FragmentAddFood())
        }

        binding.buttonCardFourRoutine.setOnClickListener {
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