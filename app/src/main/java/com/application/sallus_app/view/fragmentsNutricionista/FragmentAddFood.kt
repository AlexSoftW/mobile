package com.application.sallus_app.view.fragmentsNutricionista

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.databinding.FragmentAddFoodBinding
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.view.fragments.ModalLoadingBottomSheet
import com.application.sallus_app.viewmodel.FoodViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentAddFood : Fragment() {
    private lateinit var binding: FragmentAddFoodBinding

    private val foodViewModel: FoodViewModel by viewModel()

    private lateinit var modalLoadingBottomSheet: ModalLoadingBottomSheet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFoodBinding.inflate(inflater, container, false)

        setupView()
        setupObservers()

        return binding.root
    }

    private fun setupView() {
        modalLoadingBottomSheet = ModalLoadingBottomSheet("Adicionando novo alimento, aguarde...")

        binding.buttonCadastrarAlimento.setOnClickListener {

            val nomeAlimento = binding.edittextFoodName.text.toString()
            val tipoAlimento = binding.spinnerTypeFood.text.toString()
            val isDiabetes = binding.switchOptionDiabetes.isChecked
            val isColesterol = binding.switchOptionColesterol.isChecked
            val isHipertensao = binding.switchOptionHipertensao.isChecked

            val proteinaText = binding.edittextQtdProteina.text.toString()
            val proteina = if (proteinaText.isNotEmpty()) proteinaText.toDouble() else 0.0

            val carboidratoText = binding.edittextQtdCarboidrato.text.toString()
            val carboidrato = if (carboidratoText.isNotEmpty()) carboidratoText.toDouble() else 0.0

            val gordTotalText = binding.edittextQtdGordTotal.text.toString()
            val gordTotal = if (gordTotalText.isNotEmpty()) gordTotalText.toDouble() else 0.0

            val calorias = proteina + carboidrato + gordTotal

            if (nomeAlimento.isBlank()) {
                binding.edittextFoodName.error = "Preencha o nome do alimento."
            } else if (tipoAlimento.isBlank()) {
                binding.spinnerTypeFood.error = "Preencha o tipo do alimento."
            } else if (carboidrato <= 0.0) {
                binding.edittextQtdCarboidrato.error = "Quantidade de carboidrato inferior a 1."
            } else if (proteina <= 0.0) {
                binding.edittextQtdProteina.error = "Quantidade de proteina inferior a 1."
            } else if (gordTotal <= 0.0) {
                binding.edittextQtdGordTotal.error = "Quantidade de gordura total inferior a 1."
            } else {
                val novoAlimento = FoodData(
                    null,
                    nomeAlimento,
                    tipoAlimento,
                    isDiabetes,
                    isColesterol,
                    isHipertensao,
                    proteina,
                    carboidrato,
                    gordTotal,
                    calorias,
                    null
                )

                Log.i("dadoAlimento", "cadastrarNovoAlimento: $novoAlimento")

                modalLoadingBottomSheet.show(childFragmentManager, ModalLoadingBottomSheet.TAG)
                foodViewModel.cadastrarNovoAlimento(novoAlimento)
            }

        }
    }

    private fun setupObservers() {
        foodViewModel.responseAdicionarNovoAlimentoBottomSheet.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)

                if (it) {
                    modalLoadingBottomSheet.mostrarMensagemDeSucesso("Alimento cadastrado com sucesso!")
                    modalLoadingBottomSheet.retornarTelaAlimento()
                } else {
                    modalLoadingBottomSheet.mostrarMensagemDeErro("Houve um erro ao cadastrar este alimento!")
                    modalLoadingBottomSheet.retornarTelaAlimento()
                }
            }
        }
    }
}