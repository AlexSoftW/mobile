package com.application.sallus_app.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentItensHomePacienteBinding
import com.application.sallus_app.databinding.FragmentTodosNutricionistaBinding

class FragmentHomePaciente : Fragment() {


    private lateinit var binding: FragmentItensHomePacienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentItensHomePacienteBinding.inflate(inflater, container, false)

        binding.btnNutricionistas.setOnClickListener {
//            val FragmentNutritionist = Intent(this, FragmentNutritionist::class.java)
            val FragmentNutritionist = Intent(this, FragmentNutritionist::class.java)
            startActivity(FragmentNutritionist)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_paciente, fragment)
                startActivity(FragmentNutritionist)
                .commit()
        }

        return binding.root
    }

}