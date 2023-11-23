package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.EsqueceuSenhaData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch

class EsqueceuSenhaViewModel : ViewModel() {

    private val repository = RetrofitRepository()

    fun esqueceuSenha(esqueceuSenhaDados: String) {

        viewModelScope.launch {
            try {

                Log.d("MeuApp4", "API Acionada no ViewModel")
                val response = repository.apiEsqueceuSenhaService.postEsqueceuSenha(esqueceuSenhaDados)

            }catch (e: Exception) {
                Log.i(
                    "logAddingEsqueceuSenha",
                    "Erro esqueceu senha $e"
                )
            }
        }



    }
}