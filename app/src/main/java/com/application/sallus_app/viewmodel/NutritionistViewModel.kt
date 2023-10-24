package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch
import kotlin.Exception

class NutritionistViewModel : ViewModel() {

    private val repository = RetrofitRepository()

    private val _listaNutricionista = MutableLiveData<List<NutritionistData>>()
    val listNutricionista: MutableLiveData<List<NutritionistData>> = _listaNutricionista

    private val _listNutricionistaPorNome = MutableLiveData<List<NutritionistData>>()
    val listTodosNutricionistaPorNome: MutableLiveData<List<NutritionistData>> = _listNutricionistaPorNome

    fun fetchTodosNutricionistas() {
        viewModelScope.launch {
            try {
                val todosNutricionistas = repository.apiServiceNutritionist.getTodosNutricionistas()
                _listaNutricionista.postValue(todosNutricionistas)
            } catch (e: Exception) {
                Log.i(
                    "ERROR_FETCH_NUTRI",
                    "fetchTodosNutri: algo inesperado aconteceu no metodo fetchTodosNutricionistas: $e"
                )
            }
        }
    }

    fun fetchNutricionistaPorNome(nomeNutricionista: String){
        viewModelScope.launch{
            try {
                val nutricionistaPorNome = repository.apiServiceNutritionist.getNutricionistaPorNome(nomeNutricionista)
                _listNutricionistaPorNome.postValue(nutricionistaPorNome)

            } catch (e: Exception){
                Log.i(
                    "ERROR_FETCH_NUTRI",
                    "fetchTodosNutri: algo inesperado aconteceu no metodo fetchNutricionistaPorNome: $e"
                )
            }
        }
    }

    fun addingNewNutricionista(novoNutri: NutritionistData) {
        println(novoNutri)
        viewModelScope.launch {
            println(novoNutri)
            try {
                val response = repository.apiServiceNutritionist.adicionarNutri(novoNutri)
                Log.i(
                    "logAddingNutricionista",
                    "makeNewNutricionista: Nutricionista cadastrado com sucesso! $novoNutri"
                )
            } catch (e: java.lang.Exception) {
                Log.i(
                    "logAddingNewNutricionista",
                    "makeNewNutricionista: ocorreu algum erro ao cadastrar novo Nutricionista $e"
                )
            }
        }
    }

}