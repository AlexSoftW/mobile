package com.application.sallus_app.service

import com.application.sallus_app.model.FoodData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FoodService {

    @GET("alimentos")
    suspend fun getTodosAlimentos(): List<FoodData>

    @POST("alimentos")
    suspend fun adicionarNovoAlimento(@Body food: FoodData): FoodData
}