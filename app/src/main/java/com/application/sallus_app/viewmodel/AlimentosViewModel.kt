package com.application.sallus_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.sallus_app.model.Alimentos
import com.application.sallus_app.repository.RetrofitRepository

// nessa Classe ViewModel, aqui que vai ficar toda a nossa regra de negócio.
// tudo que for lógica programação(if/else, laço de repetição, etc)

class AlimentosViewModel : ViewModel() {

    private val repository = RetrofitRepository()

    private val _listaAlimentos = MutableLiveData<List<Alimentos>>()
    val listAlimentos: MutableLiveData<List<Alimentos>> = _listaAlimentos

    fun getTodosAlimentos(): MutableLiveData<List<Alimentos>> {
        return listAlimentos
    }

    fun fetchTodosAlimentos() {
        //aqui vai trazer todos os alimentos do repository
        //exemplo:
        val listaTeste = listOf<Alimentos>()
        _listaAlimentos.postValue(listaTeste)
    }


}