package com.application.sallus_app.service

import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.model.PerfilData
import com.application.sallus_app.model.UsuarioData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


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

    @PUT("nutricionistas")
    suspend fun atualizarNutri(@Body dadosNutri : PerfilData)

    @POST("/nutricionistas/atualizacaoSenha")
    suspend fun atualizarSenha(@Body novaSenha : UsuarioData)
}