package com.application.sallus_app.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.repository.RetrofitRepository
import com.application.sallus_app.repository.SharedPreferencesFoodManager
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// nessa Classe ViewModel, aqui que vai ficar toda a nossa regra de negócio.
// tudo que for lógica programação(if/else, laço de repetição, etc)

class FoodViewModel(private val context: Context) : ViewModel() {

    private val repository = RetrofitRepository()

    private val sharedPreferencesFoodManager: SharedPreferencesFoodManager by lazy {
        SharedPreferencesFoodManager(context)
    }

    private val _listaAlimentos = MutableLiveData<List<FoodData>>()
    val listAlimentos: MutableLiveData<List<FoodData>> = _listaAlimentos

    private val _alimentoInformadoSearchbar = MutableLiveData<FoodData>()
    val alimentoInformadoSearchbar: MutableLiveData<FoodData> = _alimentoInformadoSearchbar

    private val _tipoAlimentoInformadoCategoria = MutableLiveData<List<FoodData>>()
    val tipoAlimentoInformadoCategoria: MutableLiveData<List<FoodData>> =
        _tipoAlimentoInformadoCategoria

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
        val sharedPreferencesFoodApiData = sharedPreferencesFoodManager.getAPIData()

        if (sharedPreferencesFoodApiData != null) {
            val foodList = sharedPreferencesFoodManager.parseAPIData(sharedPreferencesFoodApiData)
            _listaAlimentos.value = foodList
        } else {
            viewModelScope.launch {
                try {
                    val result = repository.apiServiceFood.getTodosAlimentos()
                    val apiData = convertAPIDataToString(result)
                    sharedPreferencesFoodManager.saveAPIData(apiData)
                    _listaAlimentos.value = result
                    Log.i(
                        "logTodosAlimentos",
                        "fun buscarTodosAlimentos: lista de todos alimentos: $result"
                    )
                } catch (e: IOException) {
                    Log.i("logErrorBuscarTodosAlimentos", "Connection error.")
                } catch (e: HttpException) {
                    Log.i("logErrorBuscarTodosAlimentos", "API error.")
                } catch (e: Exception) {
                    Log.i("logErrorBuscarTodosAlimentos", "Unknow error.")
                }
            }
        }
    }

    fun buscarAlimentoPeloNome(alimentoInformado: String) {
        viewModelScope.launch {
            try {
                val alimentoInformadoRepository =
                    repository.apiServiceFood.getAlimentoPeloNome(alimentoInformado)

                _alimentoInformadoSearchbar.postValue(alimentoInformadoRepository)
                Log.i(
                    "logAlimentoInformado",
                    "fetchAlimentoInformado: alimento informado: $alimentoInformadoRepository"
                )
            } catch (e: Exception) {
                Log.i(
                    "ERROR_FETCH_FOOD_ONLY",
                    "fun buscarAlimentoPeloNome: algo inesperado aconteceu"
                )
            }
        }
    }

    fun buscarAlimentosPorTipo(tipo: String) {
        viewModelScope.launch {
            try {
                val tipoDeAlimento =
                    repository.apiServiceFood.getAlimentoPorTipo(tipo)

                _tipoAlimentoInformadoCategoria.postValue(tipoDeAlimento)
                Log.i(
                    "logTipoAlimento",
                    "fetchTipoAlimento: tipo de alimento: $tipoDeAlimento"
                )
            } catch (e: Exception) {
                Log.i(
                    "ERROR_FETCH_FOOD_TYPE",
                    "fun buscarAlimentosPorTipo: algo inesperado aconteceu"
                )
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
                    "fun cadastrarNovoAlimento: ocorreu algum erro ao cadastrar novo alimento $e"
                )
            }
        }
    }

    private fun convertAPIDataToString(data: List<FoodData>): String {
        val gson = Gson()
        return gson.toJson(data)
    }

}