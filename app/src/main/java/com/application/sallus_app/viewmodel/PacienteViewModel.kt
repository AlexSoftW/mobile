package com.application.sallus_app.viewmodel

import android.net.Uri
import android.text.BoringLayout
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.model.PacienteDetailsData
import com.application.sallus_app.model.PerfilData
import com.application.sallus_app.model.UsuarioData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class PacienteViewModel : ViewModel() {
    private val repository = RetrofitRepository()

    private val _listaTodosPacientesComVinculoNutricionista =
        MutableLiveData<List<PacienteDetailsData>>()
    val listaTodosPacientesComVinculoNutricionista: MutableLiveData<List<PacienteDetailsData>> =
        _listaTodosPacientesComVinculoNutricionista

    private val _nutricionistaVinculado = MutableLiveData<NutritionistData>()
    val nutricionistaVinculado: MutableLiveData<NutritionistData> = _nutricionistaVinculado

    //variavel para controlar e verificar se o paciente ja possui algum nutricionista vinculado
    val isVinculado = MutableLiveData<Boolean>()

    //variavel para controlar a cor dos icones da badge do nutricionista pelo fragment_home_paciente
    val corAtual = MutableLiveData<Int>()

    val responseCadastrarPacienteBottomSheet = MutableLiveData<Boolean>()
    val responseEditarDadosPacienteBottomSheet = MutableLiveData<Boolean>()
    val responseEditarSenhaPacienteBottomSheet = MutableLiveData<Boolean>()

    fun addingNewPaciente(novoPaciente: PacienteData) {
        viewModelScope.launch {
            println(novoPaciente)
            try {
                repository.apiServicePaciente.adicionarCliente(novoPaciente)
                responseCadastrarPacienteBottomSheet.value = true
                Log.i(
                    "logAddingPaciente",
                    "makeNewPaciente: paciente cadastrado com sucesso! $novoPaciente"
                )
            } catch (e: Exception) {
                responseCadastrarPacienteBottomSheet.value = false
                Log.i(
                    "logAddingNewPaciente",
                    "makeNewPaciente: ocorreu algum erro ao cadastrar novo paciente $e"
                )
            }
        }
    }

    fun fetchTodosPacientesComVinculoNutricionista(idNutricionista: Long) {
        viewModelScope.launch {
            try {
                val todosPacientesComVinculo =
                    repository.apiServicePaciente.getPacientesComVinculoNutricionista(
                        idNutricionista
                    )
                _listaTodosPacientesComVinculoNutricionista.postValue(todosPacientesComVinculo)
                Log.i(
                    "logTodosPacientesComVinculoNutricionista",
                    "fetchTodosPacientesComVinculoNutricionista: lista de todos pacientes: " +
                            "$todosPacientesComVinculo"
                )
            } catch (e: Exception) {
                Log.i(
                    "ERROR_FETCH_PATIENT",
                    "fetchTodosPacientesComVinculoNutricionista: algo inesperado aconteceu $e"
                )
            }
        }
    }

    fun buscarNutricionistaVinculado(idPaciente: Long) {
        viewModelScope.launch {
            try {
                val response =
                    repository.apiServicePaciente.getBuscarUnicoNutricionistaVinculado(
                        idPaciente,
                        idPaciente
                    )
                _nutricionistaVinculado.postValue(response)
                isVinculado.value = true
                Log.i(
                    "tagBuscarNutricionistaVinculado",
                    "buscarNutricionistaVinculado(): $response "
                )
            } catch (e: Exception) {
                isVinculado.value = false
                Log.i(
                    "tagBuscarNutricionistaVinculado",
                    "buscarNutricionistaVinculado(): algo inesperado aconteceu $e"
                )
            }
        }
    }

    fun alterarDadosPerfil(data: PerfilData, id: Long) {
        viewModelScope.launch {
            try {
                repository.apiServicePaciente.atualizarPaciente(data)
                responseEditarDadosPacienteBottomSheet.value = true
                Log.i("SUCCESS_PUT_PACIENTE", "Dados do paciente atualizado com sucesso !")
            } catch (e: java.lang.Exception) {
                val response = repository.apiServicePaciente.atualizarPaciente(data)
                responseEditarDadosPacienteBottomSheet.value = false
                Log.i("RESPONSE_PUT_PACIENTE", "Response Put Paciente: $response")
                Log.i("ERROR_PUT_PACIENTE", "Não foi possível atualizar os dados: $e")
            }
        }
    }

    fun alterarFoto(id: Long, foto: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                repository.apiServicePaciente.atualizarFoto(id, foto)
                responseEditarDadosPacienteBottomSheet.value = true
                Log.i("SUCCESS_PATCH_FOTO_PACIENTE", "alterarFoto: foto alterada com sucesso")
            } catch (e: Exception) {
                responseEditarDadosPacienteBottomSheet.value = false
                Log.i("ERROR_PATCH_FOTO_PACIENTE", "Não foi possível alterar a foto: $e")
            }
        }
    }

    fun alterarSenha(data: UsuarioData) {
        viewModelScope.launch {
            try {
                repository.apiServicePaciente.atualizarSenha(data)
                responseEditarSenhaPacienteBottomSheet.value = true
                Log.i(
                    "SUCCESS_PUT_PACIENTE_PASSWORD",
                    "Senha do paciente atualizada com sucesso !"
                )
            } catch (e: Exception) {
                responseEditarSenhaPacienteBottomSheet.value = false
                Log.i("ERROR_PUT_PACIENTE_PASSWORD", "Não foi possível atualizar a senha: $e")
            }
        }
    }

    fun vincularComNutricionista(idPaciente: Long, idNutricionista: Long) {
        viewModelScope.launch {
            try {
                repository.apiServicePaciente.vincularComNutricionista(idPaciente, idNutricionista)
                Log.i("tagVincularComNutri", "Nutricionista vinculado com sucesso!")
            } catch (e: java.lang.Exception) {
                Log.i("tagVincularComNutri", "Não foi possível vincular com o nutricionista: $e")
            }
        }
    }

    fun desvincularPacienteComNutricionista(idPaciente: Long) {
        viewModelScope.launch {
            try {
                repository.apiServicePaciente.desvincularPacienteComNutricionista(idPaciente)
                isVinculado.value = false
                Log.i(
                    "tagDesvincularPacienteComNutri",
                    "Paciente desvinculado com sucesso!"
                )
            } catch (e: Exception) {
                Log.i(
                    "tagDesvincularPacienteComNutri",
                    "Não foi possível desvincular o paciente: $e"
                )
            }
        }
    }

    fun alterarCorBadgePaciente(buttonId: Int) {
        corAtual.value = buttonId
    }

}