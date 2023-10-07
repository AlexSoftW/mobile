package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class PacienteViewModel: ViewModel() {

    private val repository = RetrofitRepository()

    private val _listaPacientes = MutableLiveData<List<PacienteData>>()
    val listPacientes: MutableLiveData<List<PacienteData>> = _listaPacientes
    var error:String = ""

    fun fetchTodosPacientes() {
        viewModelScope.launch {
            try {
                val todosPacientes = repository.apiServicePaciente.getTodosPacientes()
                _listaPacientes.postValue(todosPacientes)
            } catch (e: Exception) {
                error = "Lista não encontrada"
                Log.i("ERROR_FETCH_PACIENTE", "fetchTodosPacientes: algo inesperado aconteceu")
            }
        }
    }
}