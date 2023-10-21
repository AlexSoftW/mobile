package com.application.sallus_app.model

data class NutritionistData(
    val nome: String,
    val email: String,
    val senha: String,
    val avaliacao: Double,
    val avatar: Int,
    val genero: String,
    val endereco: String,
    val contagemAvaliacao: Int,
    val telefone: String,
    val id: Long,
    val crn: String,
    val ativo: Boolean
)
