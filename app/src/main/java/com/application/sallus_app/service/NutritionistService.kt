package com.application.sallus_app.service

import com.application.sallus_app.model.NutritionistData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


// nessa Interface é onde vai ficar nossos endpoints personalizados.
// exemplo:@GET('alimentos/nome'), @POST(), etc...

interface NutritionistService {

    @GET("nutricionistas")
    suspend fun getTodosNutricionistas(): List<NutritionistData>

    @GET("nutricionistas/{id}")
    suspend fun getNutricionistaPorId(@Path("id") id: Long): NutritionistData

    @GET("nutricionistas/search/{nome}")
    suspend fun getNutricionistaPorNome(nome: String): List<NutritionistData>

    @POST("nutricionistas/cadastrar")
    suspend fun adicionarNutri(@Body nutricionista : NutritionistData): NutritionistData
}