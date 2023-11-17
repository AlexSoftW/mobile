package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.DiarioGetData
import com.application.sallus_app.model.DiarioPostData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiarioViewModel : ViewModel() {

    val repository = RetrofitRepository()

    private val _listaDiariosDoPaciente = MutableLiveData<List<DiarioGetData>>()
    val listaDiariosDoPaciente: MutableLiveData<List<DiarioGetData>> = _listaDiariosDoPaciente

    private val _listaHistoricoAlimentarPaciente = MutableLiveData<List<DiarioGetData>>()
    val listaHistoricoAlimentarPaciente: MutableLiveData<List<DiarioGetData>> =
        _listaHistoricoAlimentarPaciente

    //Variavel LiveData para controlar a exibição do bottomsheet
    val responseCriarDiarioAlimentarBottomSheet = MutableLiveData<Boolean>()

    //variavel para controlar a qtd de alimentos consumidos
    val alimentosConsumidos = MutableLiveData<List<DiarioGetData>>()
    val atualizarListaDiario = MutableLiveData<List<DiarioGetData>>()


    fun buscarTodosDiariosDoPaciente(id: Long, date: String) {
        viewModelScope.launch {
            try {
                val result = repository.apiServiceDiary.getDiariosDoPaciente(id, date, id, date)
                _listaDiariosDoPaciente.value = result?.let { it } ?: emptyList()
                Log.i("logErrorBuscarTodosDiariosDoPaciente", "resultado: $result")
            } catch (e: Exception) {
                _listaDiariosDoPaciente.value = emptyList()
                Log.i("logErrorBuscarTodosDiariosDoPaciente", "Unknow error.: $e")
            }
        }
    }

    fun buscarHistoricoAlimentarDoPaciente(idPaciente: Long) {
        viewModelScope.launch {
            try {
                val result =
                    repository.apiServiceDiary.getHistoricoDoPaciente(idPaciente, idPaciente)
                _listaHistoricoAlimentarPaciente.value = result?.let { it } ?: emptyList()
                Log.i("logBuscarHistoricoAlimentarDoPaciente", "resultado: $result")
            } catch (e: java.lang.Exception) {
                _listaHistoricoAlimentarPaciente.value = emptyList()
                Log.i("logBuscarHistoricoAlimentarDoPaciente", "error: $e")
            }
        }
    }

    fun cadastrarNovoDiario(novoDiario: DiarioPostData) {
        viewModelScope.launch {
            try {
                repository.apiServiceDiary.adicionarNovoDiario(novoDiario)
                responseCriarDiarioAlimentarBottomSheet.value = true
                Log.i("logAddingNewDiary", "makeNewDiary: $novoDiario!")
            } catch (e: Exception) {
                responseCriarDiarioAlimentarBottomSheet.value = false
                Log.i(
                    "logAddingNewDiary",
                    "fun cadastrarNovoDiario: ocorreu algum erro ao cadastrar novo diario $e"
                )
            }
        }
    }

    fun consumirAlimento(idPaciente: Long, idDiario: Long, date: String) {
        viewModelScope.launch {
            try {

                val response = repository.apiServiceDiary.consumirAlimento(
                    idPaciente, idDiario, idPaciente, idDiario
                )

                val currentList = alimentosConsumidos.value ?: emptyList()
                val updateList = currentList.toMutableList()
                updateList.add(response)

                alimentosConsumidos.value = updateList

                val responseAtualizarLista = repository.apiServiceDiary.getDiariosDoPaciente(
                    idPaciente, date, idPaciente, date
                )

                atualizarListaDiario.value = responseAtualizarLista

                Log.i("logConsumirAlimento", "msg: alimento consumido com sucesso")
            } catch (e: Exception) {
                Log.i(
                    "logConsumirAlimento",
                    "fun consumirAlimento: ocorreu algum erro ao consumir um alimento $e"
                )
            }
        }
    }

    fun desfazerUltimoAlimentoConsumido(idPaciente: Long, date: String) {

        viewModelScope.launch {
            try {
                val listaAtual = alimentosConsumidos.value
                val ultimoDado: DiarioGetData? = listaAtual?.lastOrNull()

                Log.i("tagUltimoDado", "ultimodado: $ultimoDado")

                repository.apiServiceDiary.desfazerUltimoConsumido(
                    ultimoDado?.id!!,
                    idPaciente,
                    ultimoDado.id,
                    idPaciente
                )

                val novaLista = listaAtual!!.toMutableList()
                novaLista.remove(ultimoDado)
                alimentosConsumidos.value = novaLista

                val responseAtualizarLista = repository.apiServiceDiary.getDiariosDoPaciente(
                    idPaciente, date, idPaciente, date
                )

                atualizarListaDiario.value = responseAtualizarLista

            } catch (e: Exception) {
                Log.i(
                    "logDesfazerUltimoAlimentoConsumido",
                    "fun desfazerUltimoAlimentoConsumido: ocorreu algum erro ao desconsumir um alimento $e"
                )
            }
        }


    }

}