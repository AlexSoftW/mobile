package com.application.sallus_app.model

data class DiarioPostData(
    val descricao: String?,
    val qtdCalorias: Double,
    val periodo: String,
    val alimentos: String,
    val idNutricionista: Long,
    val idCliente: Long,
    val dataConsumir: String
)
