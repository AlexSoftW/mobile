package com.application.sallus_app.model

// nessa Classe é onde vai ficar nossas dataClass.

// as Data Class são frequentemente usadas para representar dados que são obtidos de APIs,
// passados entre componentes, armazenados em bancos de dados e assim por diante.
// elas ajudam a manter o código limpo, legível e eficiente.

data class FoodData(
    val nome: String,
    val tipo: String,
    val indicadoDiabete: Boolean,
    val indicadoColesterol: Boolean,
    val indicadoHipertensao: Boolean,
    val proteina: Double,
    val carboidrato: Double,
    val gorduraTotal: Double,
    val calorias: Double?
)
