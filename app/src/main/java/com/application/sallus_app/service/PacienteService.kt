package com.application.sallus_app.service

import com.application.sallus_app.model.PacienteData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PacienteService {
    @GET("clientes")
    suspend fun getTodosPacientes(): List<PacienteData>

    @GET("clientes/{id}")
    suspend fun getPacientePorId(@Path("id") id: Long): PacienteData
    @POST("clientes/cadastrar")
    suspend fun adicionarCliente(@Body cliente: PacienteData): PacienteData

}