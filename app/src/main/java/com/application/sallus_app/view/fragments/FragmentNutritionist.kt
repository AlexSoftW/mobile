package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.databinding.FragmentYoursPatientsBinding

class FragmentNutritionist : Fragment() {
    private lateinit var binding: FragmentYoursPatientsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYoursPatientsBinding.inflate(inflater, container, false)

        binding.textviewTitleYoursPatients.text = "Test123"

        return binding.root
    }
}