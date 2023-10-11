package com.application.sallus_app.view.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.application.sallus_app.model.FoodData

class FoodDetailsDialogFragment : DialogFragment() {

    private var selectedFood: FoodData? = null

    fun setFoodData(foodData: FoodData) {
        selectedFood = foodData
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage(
                "Alimento: ${selectedFood?.nome}\n\n" +
                        "Qtd de calorias:${selectedFood?.calorias}\n" +
                        "Qtd de carboidratos:${selectedFood?.carboidrato}\n" +
                        "Qtd de proteina:${selectedFood?.proteina}\n" +
                        "Gordura total:${selectedFood?.gorduraTotal}\n" +
                        "Indicado para diabéticos? ${
                            if (selectedFood?.indicadoDiabete == true) "sim" else "não"
                        }\n" +
                        "Indicado para hipertensos? ${
                            if (selectedFood?.indicadoHipertensao == true) "sim" else "não"
                        }\n" +
                        "Indicado para quem tem colesterol? ${
                            if (selectedFood?.indicadoColesterol == true) "sim" else "não"
                        }\n"
            )

            .setPositiveButton("Fechar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}