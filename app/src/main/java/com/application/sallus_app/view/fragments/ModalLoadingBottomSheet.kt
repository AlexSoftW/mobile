package com.application.sallus_app.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.sallus_app.R
import com.application.sallus_app.databinding.BottomSheetLoadingBinding
import com.application.sallus_app.view.LoginActivity
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

    fun mostrarMensagemDeSucesso(message: String) {
        binding.progressbarBottomsheetLoading.visibility = View.GONE
        binding.textviewBottomsheetLoading.text = message
        binding.imageviewBottomshetLoading.setImageResource(R.drawable.ic_sucess)
        binding.imageviewBottomshetLoading.visibility = View.VISIBLE
        binding.buttonBottomsheetLoading.visibility = View.VISIBLE
    }

    fun mostrarMensagemDeErro(message: String) {
        binding.progressbarBottomsheetLoading.visibility = View.GONE
        binding.textviewBottomsheetLoading.text = message
        binding.imageviewBottomshetLoading.setImageResource(R.drawable.ic_failed)
        binding.imageviewBottomshetLoading.visibility = View.VISIBLE
        binding.buttonBottomsheetLoading.visibility = View.VISIBLE
    }

    fun retornarTelaAlimento() {
        binding.buttonBottomsheetLoading.setOnClickListener {
            val fragmentFoods = FragmentFoods()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_nutricionista, fragmentFoods)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    fun retornarActivityAnterior() {
        binding.buttonBottomsheetLoading.setOnClickListener {
            activity?.finish()
        }
    }

    fun retornarTelaLogin() {
        binding.buttonBottomsheetLoading.text = "Desconectar"
        binding.buttonBottomsheetLoading.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            activity?.finish()
        }
    }

    fun retornarFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack()
    }

}