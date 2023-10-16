package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.HistoricData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch

class HistoricoViewModel : ViewModel(){

    private val repository = RetrofitRepository()

    private val _listaHistorico = MutableLiveData<List<HistoricData>>()
    val listaHistorico: MutableLiveData<List<HistoricData>> = _listaHistorico

    //aqui vai trazer todos o histórico das refeições do repository e vamos salvar em uma mutableLiveData
    //por que os dados sempre vão mudar.
    fun fetchTodosHistoricos() {
        viewModelScope.launch {
            try {
                val todosHistoricos = repository.apiServiceHistorico.getHistorico()
                _listaHistorico.postValue(todosHistoricos)
                Log.i(
                    "logTodosHistoricos",
                    "fetchTodosHistoricos: lista com o historico do diário alimentar: $todosHistoricos"
                )
            } catch (e: Exception) {
                Log.i("ERROR_FETCH_HISTORICO", "fetchTodosHistoricos: algo inesperado aconteceu")
            }
        }
    }
}