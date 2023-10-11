package com.application.sallus_app.service

import com.application.sallus_app.model.PacienteData
import retrofit2.http.GET

interface PacienteService {

    @GET("clientes")
    suspend fun getTodosPacientes(): List<PacienteData>


}