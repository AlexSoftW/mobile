package com.application.sallus_app.service

import com.application.sallus_app.model.DiarioGetData
import com.application.sallus_app.model.DiarioPostData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DiarioService {

    @GET("diarios/listar-consumir/{id}/{data}")
    suspend fun getDiariosDoPaciente(
        @Path("id") id: Long,
        @Path("data") data: String
    ): List<DiarioGetData>

    @POST("diarios")
    suspend fun adicionarNovoDiario(@Body diario: DiarioPostData): DiarioPostData
}