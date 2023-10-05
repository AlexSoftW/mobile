package com.application.sallus_app.di

import com.application.sallus_app.repository.RetrofitRepository
import com.application.sallus_app.viewmodel.AlimentosViewModel
import com.application.sallus_app.viewmodel.NutricionistaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// aqui vai ficar o arquivo responsável pela injeção de dependências do nosso projeto.

val appModule = module {
    single { RetrofitRepository() }
    viewModel { AlimentosViewModel() }
    viewModel { NutricionistaViewModel() }
}