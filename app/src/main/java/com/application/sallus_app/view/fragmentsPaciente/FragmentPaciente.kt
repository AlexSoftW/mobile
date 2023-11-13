package com.application.sallus_app.view.fragmentsPaciente

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentHomePacienteBinding
import com.application.sallus_app.view.fragments.FragmentFoods
import com.application.sallus_app.viewmodel.PacienteViewModel

class FragmentPaciente(private val pacienteViewModel: PacienteViewModel) : Fragment() {
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
            pacienteViewModel.alterarCorBadgePaciente(1)
            fragmentReplaceManager(FragmentTodosNutricionistas())
        }

        binding.buttonCardTwoRoutine.setOnClickListener {
            pacienteViewModel.alterarCorBadgePaciente(2)
            fragmentReplaceManager(FragmentFoods())
        }

        binding.buttonCardThreeRoutine.setOnClickListener {
            pacienteViewModel.alterarCorBadgePaciente(3)
            fragmentReplaceManager(FragmentDiarioAlimentarPaciente())
        }

        binding.buttonCardFourRoutine.setOnClickListener {
            pacienteViewModel.alterarCorBadgePaciente(4)
            fragmentReplaceManager(FragmentHistoricoAlimentarPaciente())
        }

    }

    private fun fragmentReplaceManager(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_paciente, fragment)
            .addToBackStack(null)
            .commit()
    }
}