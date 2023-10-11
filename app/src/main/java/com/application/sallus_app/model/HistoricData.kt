package com.application.sallus_app.model

import java.time.format.DateTimeFormatter
import java.util.Date

data class HistoricData(
    val descricao: String,
    val qtdCalorias: Int,
    val periodo: String,
    val consumido: Boolean,
    val dataConsumir: Date,
    val dataConsumida: DateTimeFormatter,
    val alimentos: String
)
