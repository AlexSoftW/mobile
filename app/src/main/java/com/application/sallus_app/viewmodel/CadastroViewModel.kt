package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class CadastroViewModel : ViewModel() {
    private val repository = RetrofitRepository()

    fun addingNewPaciente(novoPaciente : PacienteData) {
        viewModelScope.launch {
            try {
                val response = repository.apiServiceCadastro.adicionarCliente(novoPaciente)
                Log.i("logAddingPaciente", "makeNewPaciente: paciente cadastrado com sucesso!")
            } catch (e: Exception) {
                Log.i(
                        "logAddingNewPaciente",
                        "makeNewPaciente: ocorreu algum erro ao cadastrar novo paciente $e"
                )
            }
        }
    }

}