package com.application.sallus_app.model

data class ClienteDAta(
    val nome: String,
    val email: String,
    val senha: String,
    val avaliacao: Int,
    val avatar: Int,
    val genero: String,
    val endereco: String,
    val contagemAvaliacao: Int,
    val telefone: String,
    val comorbidade: Boolean,
    val ativo: Boolean,
    val id: Int

)
