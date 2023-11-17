package com.application.sallus_app.service

import com.application.sallus_app.model.DiarioGetData
import com.application.sallus_app.model.DiarioPostData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DiarioService {

    @GET("diarios/listar-consumir/{id}/{data}")
    suspend fun getDiariosDoPaciente(
        @Path("id") id: Long,
        @Path("data") data: String,
        @Query("id") queryId: Long,
        @Query("data") queryData: String
    ): List<DiarioGetData>

    @GET("diarios/historico/{idCliente}")
    suspend fun getHistoricoDoPaciente(
        @Path("idCliente") idCliente: Long,
        @Query("idCliente") queryIdCliente: Long
    ): List<DiarioGetData>

    @POST("diarios")
    suspend fun adicionarNovoDiario(@Body diario: DiarioPostData): DiarioPostData

    @PUT("diarios/consumido/{idCliente}/{idDiario}")
    suspend fun consumirAlimento(
        @Path("idCliente") idCliente: Long,
        @Path("idDiario") idDiario: Long,
        @Query("idCliente") queryIdCliente: Long,
        @Query("idDiario") queryIdDiario: Long
    ): DiarioGetData

    @PUT("diarios/desfazer-consumido/{diarioId}/{usuarioId}")
    suspend fun desfazerUltimoConsumido(
        @Path("diarioId") idDiario: Long,
        @Path("usuarioId") idCliente: Long,
        @Query("diarioId") queryIdDiario: Long,
        @Query("usuarioId") queryIdCliente: Long
    ): DiarioGetData
}