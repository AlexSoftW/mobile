package com.application.sallus_app.service

import com.application.sallus_app.model.DiarioGetData
import com.application.sallus_app.model.DiarioPostData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DiarioService {

    @GET("diarios")
    suspend fun getDiariosDoPaciente(): List<DiarioGetData>

    @POST("diarios")
    suspend fun adicionarNovoDiario(@Body diario: DiarioPostData): DiarioPostData
}