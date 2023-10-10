package com.application.sallus_app.repository

import com.application.sallus_app.service.FoodService
import com.application.sallus_app.service.NutritionistService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// nessa Classe a gente vai fazer a chamada no nosso end-point principal(https://nossaAPI/)
// utilizaremos o retrofit para fazer as chamadas HTTP,
// ela ja faz a conversão automaticamente a conversão do JSON para um objeto kotlin.

class RetrofitRepository {

    private val producao = "https://sallus.sytes.net/api/" //Esse é o IP(MAIN)
    private val test = "http://52.44.147.150:8080/" //Esse é o IP do back-end individual da AWS

    private val retrofit = Retrofit.Builder()
        .baseUrl(test)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiServiceNutritionist: NutritionistService =
        retrofit.create(NutritionistService::class.java)

    val apiServiceFood: FoodService =
        retrofit.create(FoodService::class.java)

}