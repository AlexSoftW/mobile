package com.application.sallus_app.service

import com.application.sallus_app.model.EsqueceuSenhaData
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface EsqueceuSenhaService {

    @POST("clientes/enviar-email/{destinatario}")
    suspend fun postEsqueceuSenha(@Query("destinatario") esqueceuSenhaDados: String)

}