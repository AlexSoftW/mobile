package com.application.sallus_app.model

data class LoginData (
    val userId: Int,
    val email: String,
    val senha: String,
    val token: String
)