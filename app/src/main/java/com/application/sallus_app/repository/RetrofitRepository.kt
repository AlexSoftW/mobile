package com.application.sallus_app.repository

import com.application.sallus_app.service.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// nessa Classe a gente vai fazer a chamada no nosso end-point principal(https://nossaAPI/)
// utilizaremos o retrofit para fazer as chamadas HTTP,
// ela ja faz a conversão automaticamente a conversão do JSON para um objeto kotlin.

class RetrofitRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://sallusapp.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: Service = retrofit.create(Service::class.java)

}