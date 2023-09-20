package com.application.sallus_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.sallus_app.R
import com.application.sallus_app.viewmodel.AlimentosViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val alimentoViewModel: AlimentosViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        setupObservers()
    }

    private fun setupObservers() {
        alimentoViewModel.fetchTodosAlimentos()
        alimentoViewModel.fetchAlimentoPorId(1)

        //todos os alimentos
        alimentoViewModel.obterTodosAlimentos().observe(this) {
            it[0].nome //pegando o nome do alimento da primeira posição da lista
            it[0].indicadoDiabete //pegando o primeiro alimento e verificando o boolean de diabetes
        }

        //alimento por id
        alimentoViewModel.obterAlimentoPorId().observe(this) {
            it.nome // como eu só to pegando uma instancia de objeto
            //então eu não preciso pegar a posição.
        }
    }
}