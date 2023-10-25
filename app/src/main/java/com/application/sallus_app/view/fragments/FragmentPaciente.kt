package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentHomeNutricionistaBinding
import com.application.sallus_app.databinding.FragmentHomePacienteBinding

class FragmentPaciente : Fragment() {
    private lateinit var binding: FragmentHomePacienteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePacienteBinding.inflate(inflater, container, false)
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

    }

    private fun fragmentReplaceManager(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_paciente, fragment)
            .addToBackStack(null)
            .commit()
    }
}