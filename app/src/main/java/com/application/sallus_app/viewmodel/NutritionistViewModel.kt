package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.model.PerfilData
import com.application.sallus_app.model.UsuarioData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import kotlin.Exception

class NutritionistViewModel : ViewModel() {

    private val repository = RetrofitRepository()

    private val _listaNutricionista = MutableLiveData<List<NutritionistData>>()
    val listNutricionista: MutableLiveData<List<NutritionistData>> = _listaNutricionista

    private val _listNutricionistaPorNome = MutableLiveData<List<NutritionistData>>()
    val listTodosNutricionistaPorNome: MutableLiveData<List<NutritionistData>> =
        _listNutricionistaPorNome

    //variavel para controlar a cor dos icones da badge do nutricionista pelo fragment_home_nutricionista
    val corAtual = MutableLiveData<Int>()

    val responseEditarDadosNutricionistaBottomSheet = MutableLiveData<Boolean>()


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

    fun fetchNutricionistaPorNome(nomeNutricionista: String) {
        viewModelScope.launch {
            try {
                val nutricionistaPorNome =
                    repository.apiServiceNutritionist.getNutricionistaPorNome(nomeNutricionista)
                _listNutricionistaPorNome.postValue(nutricionistaPorNome)

            } catch (e: Exception) {
                Log.i(
                    "ERROR_FETCH_NUTRI",
                    "fetchTodosNutri: algo inesperado aconteceu no metodo fetchNutricionistaPorNome: $e"
                )
            }
        }
    }

    fun addingNewNutricionista(novoNutri: NutritionistData) {
        println(novoNutri)
        viewModelScope.launch {
            println(novoNutri)
            try {
                val response = repository.apiServiceNutritionist.adicionarNutri(novoNutri)
                Log.i(
                    "logAddingNutricionista",
                    "makeNewNutricionista: Nutricionista cadastrado com sucesso! $novoNutri"
                )
            } catch (e: java.lang.Exception) {
                Log.i(
                    "logAddingNewNutricionista",
                    "makeNewNutricionista: ocorreu algum erro ao cadastrar novo Nutricionista $e"
                )
            }
        }
    }


    fun alterarDadosPerfil(data: PerfilData) {
        viewModelScope.launch {
            try {
                repository.apiServiceNutritionist.atualizarNutri(data)
                responseEditarDadosNutricionistaBottomSheet.value = true
                Log.i("SUCCESS_PUT_NUTRI", "Dados do nutricionista atualizado com sucesso !")
            } catch (e: java.lang.Exception) {
                responseEditarDadosNutricionistaBottomSheet.value = false
                val response = repository.apiServiceNutritionist.atualizarNutri(data)
                Log.i("RESPONSE_PUT_NUTRI", "Response Put Nutri: $response")
                Log.i("ERROR_PUT_NUTRI", "Não foi possível atualizar os dados: $e")
            }
        }
    }

    fun alterarFoto(id: Long, foto: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                repository.apiServiceNutritionist.atualizarFoto(id, foto)
                responseEditarDadosNutricionistaBottomSheet.value = true
                Log.i("SUCCESS_PATCH_FOTO_PACIENTE", "alterarFoto: foto alterada com sucesso")
            } catch (e: Exception) {
                responseEditarDadosNutricionistaBottomSheet.value = false
                Log.i("ERROR_PATCH_FOTO_PACIENTE", "Não foi possível alterar a foto: $e")
            }
        }
    }

    fun alterarSenha(data: UsuarioData) {
        viewModelScope.launch {
            try {
                repository.apiServiceNutritionist.atualizarSenha(data)
                Log.i(
                    "SUCCESS_PUT_NUTRI_PASSWORD",
                    "Senha do nutricionista atualizada com sucesso !"
                )
            } catch (e: java.lang.Exception) {
                val response = repository.apiServiceNutritionist.atualizarSenha(data)
                Log.i("RESPONSE_PUT_NUTRI_PASSWORD", "Response Put Nutri: $response")
                Log.i("ERROR_PUT_NUTRI_PASSWORD", "Não foi possível atualizar a senha: $e")
            }
        }
    }


    fun alterarCorBadgeNutricionista(buttonId: Int) {
        corAtual.value = buttonId
    }


}