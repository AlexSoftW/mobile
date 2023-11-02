package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.sallus_app.R
import com.application.sallus_app.databinding.BottomSheetLoadingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalLoadingBottomSheet(private val titulo: String) : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ModalLoadingBottomSheet"
    }

    private lateinit var binding: BottomSheetLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetLoadingBinding.inflate(inflater, container, false)

        setupView()

        binding.buttonBottomsheetLoading.setOnClickListener {
            dismiss()
            retornarFragment()
        }

        return binding.root
    }

    private fun setupView() {
        binding.buttonBottomsheetLoading.background = null

        if (titulo.isNullOrEmpty()) {
            binding.textviewBottomsheetLoading.text = "Aguarde..."
        } else {
            binding.textviewBottomsheetLoading.text = titulo
        }
    }

    fun mostrarMensagemDeSucesso() {
        binding.progressbarBottomsheetLoading.visibility = View.GONE
        binding.textviewBottomsheetLoading.text = "Diário criado com sucesso!"
        binding.imageviewBottomshetLoading.setImageResource(R.drawable.ic_sucess)
        binding.imageviewBottomshetLoading.visibility = View.VISIBLE
        binding.buttonBottomsheetLoading.visibility = View.VISIBLE
    }

    fun mostrarMensagemDeErro() {
        binding.progressbarBottomsheetLoading.visibility = View.GONE
        binding.textviewBottomsheetLoading.text = "Erro ao criar o diário alimentar!"
        binding.imageviewBottomshetLoading.setImageResource(R.drawable.ic_failed)
        binding.imageviewBottomshetLoading.visibility = View.VISIBLE
        binding.buttonBottomsheetLoading.visibility = View.VISIBLE
    }

    fun retornarFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack()
    }
}