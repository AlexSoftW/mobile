package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class NutritionistViewModel : ViewModel() {

    private val repository = RetrofitRepository()

    private val _listaNutricionista = MutableLiveData<List<NutritionistData>>()
    val listNutricionista: MutableLiveData<List<NutritionistData>> = _listaNutricionista

    fun obterTodosNutricionista(): MutableLiveData<List<NutritionistData>> {
        return listNutricionista
    }

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


}