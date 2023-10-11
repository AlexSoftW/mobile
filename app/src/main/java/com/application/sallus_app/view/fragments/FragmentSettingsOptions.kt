package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.databinding.FragmentSettingsOptionsBinding

class FragmentSettingsOptions : Fragment() {
    private lateinit var binding: FragmentSettingsOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }
}