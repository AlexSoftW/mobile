package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.sallus_app.R
import com.application.sallus_app.databinding.BottomSheetTwoOptionsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalTwoOptionsBottomSheet(
    private val titulo: String,
    private val listener: BottomSheetListener
) :
    BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ModalTwoOptionsBottomSheet"
    }

    interface BottomSheetListener {
        fun onButtonYesClick()
        fun onButtonNoClick()
    }

    private lateinit var binding: BottomSheetTwoOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BottomSheetTwoOptionsBinding.inflate(inflater, container, false)

        setupView()

        binding.buttonYesTwoOptions.setOnClickListener {
            listener.onButtonYesClick()
        }

        binding.buttonNoTwoOptions.setOnClickListener {
            listener.onButtonNoClick()
            dismiss()
        }

        return binding.root
    }

    private fun setupView() {
        binding.buttonBottomsheetTwoOptions.background = null

        if (titulo.isNullOrEmpty()) {
            binding.textviewBottomsheetTwoOptions.text = "Aguarde..."
        } else {
            binding.textviewBottomsheetTwoOptions.text = titulo
        }
    }

    fun mostrarMensagemDeSucesso(message: String) {
        binding.progressbarBottomsheetTwoOptions.visibility = View.GONE
        binding.textviewBottomsheetTwoOptions.text = message
        binding.imageviewBottomshetTwoOptions.setImageResource(R.drawable.ic_sucess)
        binding.imageviewBottomshetTwoOptions.visibility = View.VISIBLE
        binding.buttonBottomsheetTwoOptions.visibility = View.VISIBLE
        binding.buttonYesTwoOptions.visibility = View.GONE
        binding.buttonNoTwoOptions.visibility = View.GONE
    }

    fun mostrarMensagemDeErro(message: String) {
        binding.progressbarBottomsheetTwoOptions.visibility = View.GONE
        binding.textviewBottomsheetTwoOptions.text = message
        binding.imageviewBottomshetTwoOptions.setImageResource(R.drawable.ic_failed)
        binding.imageviewBottomshetTwoOptions.visibility = View.VISIBLE
        binding.buttonBottomsheetTwoOptions.visibility = View.VISIBLE
        binding.buttonYesTwoOptions.visibility = View.GONE
        binding.buttonNoTwoOptions.visibility = View.GONE
    }

    fun mostrarCarregamento(message: String) {
        binding.textviewBottomsheetTwoOptions.text = message
        binding.buttonYesTwoOptions.visibility = View.GONE
        binding.buttonNoTwoOptions.visibility = View.GONE
        binding.progressbarBottomsheetTwoOptions.visibility = View.VISIBLE
    }

//    fun retornarTelaAlimento() {
//        binding.buttonBottomsheetLoading.setOnClickListener {
//            val fragmentFoods = FragmentFoods()
//            val transaction = requireActivity().supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container_nutricionista, fragmentFoods)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
//    }

//    fun retornarTelaLogin() {
//        binding.buttonBottomsheetLoading.setOnClickListener {
//            activity?.finish()
//        }
//    }

    fun retornarFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack()
    }

    fun fecharModal() {
        binding.buttonBottomsheetTwoOptions.setOnClickListener {
            dismiss()
        }
    }

}