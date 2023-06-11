package com.bangkit.kewarung.authentication.data

data class LoginResponse(
    val message: String,
    val token: String,
    val user_id: String
)