package com.application.sallus_app.model

data class DiarioGetData(
    val id: Long?,
    val descricao: String,
    val qtdCalorias: Double,
    val periodo: String,
    val consumido: Boolean,
    val dataConsumir: String,
    val dataConsumida: String?,
    val alimentos: String
)
