package com.application.sallus_app.service

import com.application.sallus_app.model.FoodData
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodService {

    @GET("alimentos")
    suspend fun getTodosAlimentos(): List<FoodData>

    @GET("alimentos/{id}")
    suspend fun getAlimentoPorId(@retrofit2.http.Path("id") id: Long): FoodData
}