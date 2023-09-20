package com.application.sallus_app.service

import com.application.sallus_app.model.Alimentos
import retrofit2.http.GET
import retrofit2.http.Path

// nessa Interface é onde vai ficar nossos endpoints personalizados.
// exemplo:@GET('alimentos/nome'), @POST(), etc...

interface Service {

    @GET("alimentos/{id}")
    suspend fun getAlimentosPorId(@Path("id") id: Long): Alimentos

    @GET("alimentos/")
    suspend fun getTodosAlimentos(): List<Alimentos>
}