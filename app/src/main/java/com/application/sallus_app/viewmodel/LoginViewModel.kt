package com.application.sallus_app.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.model.LoginData
import com.application.sallus_app.model.UsuarioData
import com.application.sallus_app.repository.RetrofitRepository
import com.application.sallus_app.view.NutritionistActivity
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class LoginViewModel: ViewModel() {
    // pedro@oi.com
    //pedronutri@oi.com
    // Pedro132
    private val repository = RetrofitRepository()
    private val _responseLogin = MutableLiveData<LoginData>()
    var responseLogin: MutableLiveData<LoginData> = _responseLogin
    var controle = MutableLiveData<Int>()
    fun loginUsuario(dadosUsuario: UsuarioData) {
        viewModelScope.launch {
            Log.d("ViewModle", "Entrou na ViewModle")
            try {
                Log.d("ViewModlePaciente", "Dados_Paciente== $dadosUsuario")
                val response = repository.apiLoginService.loginPaciente(dadosUsuario)
                Log.i("responsePaciente", "responsePaciente========: $response")
                controle.postValue(1)
            } catch (e: Exception) {
                try {
                    Log.d("ViewModleNutri", "Dados_Nutri== $dadosUsuario")
                    val response = repository.apiLoginService.loginNutri(dadosUsuario)
                    Log.i("responseNutri", "responseNutri========: $response")
                    controle.postValue(2)
                }catch (e: Exception){
                    controle.postValue(0)
                    Log.i(
                        "logAddingNewFood",
                        "Login: ocorreu algum erro ao realizar o login $e"
                    )
                }
            }
        }
    }
}