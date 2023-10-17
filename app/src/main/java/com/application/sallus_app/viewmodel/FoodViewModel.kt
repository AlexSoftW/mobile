package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.repository.RetrofitRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch

// nessa Classe ViewModel, aqui que vai ficar toda a nossa regra de negócio.
// tudo que for lógica programação(if/else, laço de repetição, etc)

class FoodViewModel : ViewModel() {

    private val repository = RetrofitRepository()

    private val _listaAlimentos = MutableLiveData<List<FoodData>>()
    val listAlimentos: MutableLiveData<List<FoodData>> = _listaAlimentos

    private val _listaAlimentosCriarRotina = MutableLiveData<List<FoodData>>()
    val listaAlimentosCriarRotina: MutableLiveData<List<FoodData>> = _listaAlimentosCriarRotina

    fun tratarAlimentosSelecionados(listaDeAlimentos: String) {
        val gson = Gson()
        val foodData: List<FoodData> =
            gson.fromJson(listaDeAlimentos, Array<FoodData>::class.java).toList()
        _listaAlimentosCriarRotina.postValue(foodData)
    }

    fun removerAlimentoDaRotina(alimento: FoodData) {
        val currentList = _listaAlimentosCriarRotina.value.orEmpty().toMutableList()
        currentList.remove(alimento)
        _listaAlimentosCriarRotina.postValue(currentList)
    }


    //aqui vai trazer todos os alimentos do repository e vamos salvar em uma mutableLiveData
    //por que os dados sempre vão mudar.
    fun buscarTodosAlimentos() {
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

    fun cadastrarNovoAlimento(novoAlimento: FoodData) {
        viewModelScope.launch {
            try {
                repository.apiServiceFood.adicionarNovoAlimento(novoAlimento)
                Log.i("logAddingNewFood", "makeNewFood: $novoAlimento!")
            } catch (e: Exception) {
                Log.i(
                    "logAddingNewFood",
                    "makeNewFood: ocorreu algum erro ao cadastrar novo alimento $e"
                )
            }
        }
    }

}