package com.application.sallus_app.service

import com.application.sallus_app.model.HistoricData
import com.application.sallus_app.model.NutritionistData
import retrofit2.http.GET

interface HistoricoService {

    @GET("diarios/historico")
    suspend fun getHistorico(): List<HistoricData>
}