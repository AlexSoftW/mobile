package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.DiarioGetData
import com.application.sallus_app.model.DiarioPostData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch

class DiarioViewModel : ViewModel() {

    val repository = RetrofitRepository()

    private val _listaDiariosDoPaciente = MutableLiveData<List<DiarioGetData>>()
    val listaDiariosDoPaciente: MutableLiveData<List<DiarioGetData>> = _listaDiariosDoPaciente

    //Variavel LiveData para controlar a exibição do bottomsheet
    val responseCriarDiarioAlimentar = MutableLiveData<Boolean>()

    fun buscarTodosDiariosDoPaciente() {
        viewModelScope.launch {
            try {
                val result = repository.apiServiceDiary.getDiariosDoPaciente()
                _listaDiariosDoPaciente.value = result
            } catch (e: Exception) {
                Log.i("logErrorBuscarTodosDiariosDoPaciente", "Unknow error.")
            }
        }
    }

    fun cadastrarNovoDiario(novoDiario: DiarioPostData) {
        viewModelScope.launch {
            try {
                repository.apiServiceDiary.adicionarNovoDiario(novoDiario)
                responseCriarDiarioAlimentar.value = true
                Log.i("logAddingNewDiary", "makeNewDiary: $novoDiario!")
            } catch (e: Exception) {
                responseCriarDiarioAlimentar.value = false
                Log.i(
                    "logAddingNewDiary",
                    "fun cadastrarNovoDiario: ocorreu algum erro ao cadastrar novo diario $e"
                )
            }
        }
    }

}