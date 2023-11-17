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

    private val _listaAlimentos = MutableLiveData<List<FoodData>>()
    val listAlimentos: MutableLiveData<List<FoodData>> = _listaAlimentos

    private val _listaAlimentoPorCategoria = MutableLiveData<List<FoodData>>()
    val listaAlimentoPorCategoria: MutableLiveData<List<FoodData>> = _listaAlimentoPorCategoria

    private val _alimentoInformadoSearchbar = MutableLiveData<FoodData>()
    val alimentoInformadoSearchbar: MutableLiveData<FoodData> = _alimentoInformadoSearchbar

    private val _listaAlimentosSelecionadosParaCriarRotina = MutableLiveData<List<FoodData>>()
    val listaAlimentosSelecionadosParaCriarRotina: MutableLiveData<List<FoodData>> =
        _listaAlimentosSelecionadosParaCriarRotina

    val responseAdicionarNovoAlimentoBottomSheet = MutableLiveData<Boolean>()

    private val sharedPreferencesManager: SharedPreferencesFoodManager by lazy {
        SharedPreferencesFoodManager(context)
    }

    fun converterAlimentosSelecionadoParaArrayList(listaDeAlimentos: String) {
        val gson = Gson()
        val foodData: List<FoodData> =
            gson.fromJson(listaDeAlimentos, Array<FoodData>::class.java).toList()
        _listaAlimentosSelecionadosParaCriarRotina.postValue(foodData)
    }

    fun removerAlimentoDaRotina(alimento: FoodData) {
        val currentList = _listaAlimentosSelecionadosParaCriarRotina.value.orEmpty().toMutableList()
        currentList.remove(alimento)
        _listaAlimentosSelecionadosParaCriarRotina.postValue(currentList)
    }

    //aqui vai trazer todos os alimentos do repository e vamos salvar em uma mutableLiveData
    //por que os dados sempre vão mudar.
    fun buscarTodosAlimentos() {

        val sharedPreferencesApiData = sharedPreferencesManager.getAPIData()

        if (sharedPreferencesApiData == null) {
            viewModelScope.launch {
                try {
                    val result = repository.apiServiceFood.getTodosAlimentos()
                    val apiData = convertAPIDataToString(result)
                    sharedPreferencesManager.saveAPIData(apiData)
                    _listaAlimentos.value = result
                } catch (e: IOException) {
                    Log.i("tagBuscarTodosAlimentos", "buscarTodosAlimentos: Connection error.")
                } catch (e: HttpException) {
                    Log.i("tagBuscarTodosAlimentos", "buscarTodosAlimentos: API error.")
                } catch (e: Exception) {
                    Log.i("tagBuscarTodosAlimentos", "buscarTodosAlimentos: Unknow error.")
                }
            }
        } else {
            val foodList = sharedPreferencesManager.parseAPIData(sharedPreferencesApiData)
            _listaAlimentos.value = foodList
        }
    }

    fun buscarAlimentoPeloNome(alimentoInformado: String) {
        viewModelScope.launch {
            try {
                val alimentoInformadoRepository =
                    repository.apiServiceFood.getAlimentoPeloNome(alimentoInformado)

                _alimentoInformadoSearchbar.postValue(alimentoInformadoRepository)
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

                _listaAlimentoPorCategoria.postValue(tipoDeAlimento)
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
                responseAdicionarNovoAlimentoBottomSheet.value = true
            } catch (e: Exception) {
                responseAdicionarNovoAlimentoBottomSheet.value = false
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