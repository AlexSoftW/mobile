package com.application.sallus_app.service

import com.application.sallus_app.model.EsqueceuSenhaData
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface EsqueceuSenhaService {

    @POST("clientes/enviar-email")
    suspend fun postEsqueceuSenha(@Path("destinatario") esqueceuSenhaDados: String)
}