package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.lang.Exception

// nessa Classe ViewModel, aqui que vai ficar toda a nossa regra de negócio.
// tudo que for lógica programação(if/else, laço de repetição, etc)

class FoodViewModel : ViewModel() {

    private val repository = RetrofitRepository()

    private val _listaAlimentos = MutableLiveData<List<FoodData>>()
    val listAlimentos: MutableLiveData<List<FoodData>> = _listaAlimentos

    private val _alimentoPorId = MutableLiveData<FoodData>()
    val alimentoPorId: MutableLiveData<FoodData> = _alimentoPorId

    fun obterTodosAlimentos(): MutableLiveData<List<FoodData>> {
        return listAlimentos
    }

    fun obterAlimentoPorId(): MutableLiveData<FoodData> {
        return alimentoPorId
    }

    //aqui vai trazer todos os alimentos do repository e vamos salvar em uma mutableLiveData
    //por que os dados sempre vão mudar.
    fun fetchTodosAlimentos() {
        viewModelScope.launch {
            try {
                val todosAlimentos = repository.apiServiceFood.getTodosAlimentos()
                _listaAlimentos.postValue(todosAlimentos)
                Log.i(
                    "logTodosAlimentos",
                    "fetchTodosAlimentos: lista de todos alimentos: $todosAlimentos"
                )
            } catch (e: Exception) {
                Log.i("ERROR_FETCH_FOOD", "fetchTodosAlimentos: algo inesperado aconteceu")
            }
        }
    }

    fun addingNewFood(novoAlimento: FoodData) {
        viewModelScope.launch {
            try {
                val response = repository.apiServiceFood.adicionarNovoAlimento(novoAlimento)
                Log.i("logAddingNewFood", "makeNewFood: alimento cadastrado com sucesso!")
            } catch (e: Exception) {
                Log.i(
                    "logAddingNewFood",
                    "makeNewFood: ocorreu algum erro ao cadastrar novo alimento $e"
                )
            }
        }
    }

}