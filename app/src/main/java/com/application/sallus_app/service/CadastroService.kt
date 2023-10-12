package com.application.sallus_app.service

import com.application.sallus_app.model.FoodData
import com.application.sallus_app.model.PacienteData
import retrofit2.http.Body
import retrofit2.http.POST

interface CadastroService {

    @POST("clientes")
    suspend fun adicionarCliente(@Body cliente: PacienteData): PacienteData
}