package com.application.sallus_app.view.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.sallus_app.R
import com.application.sallus_app.databinding.BottomSheetDetailsFoodBinding
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalFoodDetailsBottomSheet(
    private val image: String?,
    private val name: String,
    private val type: String,
    private val qtdCalorias: Double,
    private val qtdCarboidratos: Double,
    private val qtdProteinas: Double,
    private val gorduraTotal: Double,
    private val indicadoDiabetes: Boolean,
    private val indicadoHipertensao: Boolean,
    private val indicadoColesterol: Boolean
) : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ModalFoodDetailsBottomSheet"
    }

    private lateinit var binding: BottomSheetDetailsFoodBinding
    private lateinit var bitmapImage: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetDetailsFoodBinding.inflate(inflater, container, false)

        setupView()

        return binding.root
    }

    private fun setupView() {

        Glide.with(binding.root.context)
            .load(image)
            .placeholder(R.mipmap.food_default)
            .error(R.mipmap.food_default)
            .into(binding.imageviewAlimentoBottomsheetDetailsFood)

        binding.textviewNomeAlimentoBottomsheetDetailsFood.text = name
        binding.textviewTagTipoAlimentoBottomsheetDetailsFood.text = type
        binding.textviewQtdCaloriasBottomsheetDetailsFood.text = qtdCalorias.toString()
        binding.textviewQtdCarboidratosBottomsheetDetailsFood.text = qtdCarboidratos.toString()
        binding.textviewQtdProteinasBottomsheetDetailsFood.text = qtdProteinas.toString()
        binding.textviewGorduraTotalBottomsheetDetailsFood.text = gorduraTotal.toString()

        binding.textviewIndicadoDiabetesBottomsheetDetailsFood.text =
            if (indicadoDiabetes) "sim" else "não"
        binding.textviewIndicadoHipertensaoBottomsheetDetailsFood.text =
            if (indicadoHipertensao) "sim" else "não"
        binding.textviewIndicadoColesterolBottomsheetDetailsFood.text =
            if (indicadoColesterol) "sim" else "não"

    }

}
