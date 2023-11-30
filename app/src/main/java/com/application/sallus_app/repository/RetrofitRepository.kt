package com.application.sallus_app.repository

import com.application.sallus_app.service.DiarioService
import com.application.sallus_app.service.EsqueceuSenhaService
import com.application.sallus_app.service.FoodService
import com.application.sallus_app.service.LoginService
import com.application.sallus_app.service.NutritionistService
import com.application.sallus_app.service.PacienteService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// nessa Classe a gente vai fazer a chamada no nosso end-point principal(https://nossaAPI/)
// utilizaremos o retrofit para fazer as chamadas HTTP e HTTPS,
// ela ja faz a conversão automaticamente a conversão do JSON para um objeto kotlin(Data Class).

class RetrofitRepository {

    private val producao = "https://sallus.sytes.net/api/" //Esse é o IP(MAIN)

    private val test = "http://50.17.14.37:8081/" //Esse é o IP do back-end individual da AWS

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(test)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiLoginService: LoginService =
        retrofit.create(LoginService::class.java)

    val apiServiceFood: FoodService =
        retrofit.create(FoodService::class.java)

    val apiServiceNutritionist: NutritionistService =
        retrofit.create(NutritionistService::class.java)

    val apiServicePaciente: PacienteService =
        retrofit.create(PacienteService::class.java)

    val apiServiceDiary: DiarioService =
        retrofit.create(DiarioService::class.java)

    val apiEsqueceuSenhaService: EsqueceuSenhaService =
        retrofit.create(EsqueceuSenhaService::class.java)

}