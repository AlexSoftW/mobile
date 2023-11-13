package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.LoginData
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.model.UsuarioData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    // pedro@oi.com
    //pedronutri@oi.com
    // Pedro132

    //gustavera@gmail.com
    //12345

    private val repository = RetrofitRepository()

    private val _responseLogin = MutableLiveData<LoginData>()
    var responseLogin: MutableLiveData<LoginData> = _responseLogin

    private val _pacienteData = MutableLiveData<PacienteData>()
    val pacienteData: MutableLiveData<PacienteData> = _pacienteData

    private val _nutricionistaData = MutableLiveData<NutritionistData>()
    val nutricionistaData: MutableLiveData<NutritionistData> = _nutricionistaData

    var controle = MutableLiveData<Int>()
    fun loginUsuario(dadosUsuario: UsuarioData) {
        viewModelScope.launch {
            try {
                val response = repository.apiLoginService.loginPaciente(dadosUsuario)
                val responsePaciente =
                    repository.apiServicePaciente.getPacientePorId(response.userId)

                _pacienteData.postValue(responsePaciente)
                Log.i("logiPacienteLogin", "loginPaciente: $responsePaciente")
                controle.postValue(1)
            } catch (e: Exception) {
                try {
                    val response = repository.apiLoginService.loginNutri(dadosUsuario)
                    val responseNutricionista =
                        repository.apiServiceNutritionist.getNutricionistaPorId(response.userId)

                    _nutricionistaData.postValue(responseNutricionista)
                    Log.i("logiNutriLogin", "loginNutricionista: $responseNutricionista")
                    controle.postValue(2)

                } catch (e: Exception) {
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