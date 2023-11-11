package com.application.sallus_app.service

import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.model.PacienteDetailsData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PacienteService {
    @GET("clientes")
    suspend fun getTodosPacientes(): List<PacienteData>

    @GET("clientes/{id}")
    suspend fun getPacientePorId(@Path("id") id: Long): PacienteData

    @GET("clientes/com-vinculo/por-campos/{id}")
    suspend fun getPacientesComVinculoNutricionista(@Path("id") id: Long): List<PacienteDetailsData>

    @GET("clientes/busca-por-nome/{nome}")
    suspend fun getPacientePorNome(@Path("nome") nome: String): PacienteDetailsData

    @POST("clientes/cadastrar")
    suspend fun adicionarCliente(@Body cliente: PacienteData): PacienteData

}