package com.application.sallus_app.service

import com.application.sallus_app.model.NutritionistData
import retrofit2.http.GET

// nessa Interface é onde vai ficar nossos endpoints personalizados.
// exemplo:@GET('alimentos/nome'), @POST(), etc...

interface NutritionistService {

    @GET("nutricionistas")
    suspend fun getTodosNutricionistas(): List<NutritionistData>
}