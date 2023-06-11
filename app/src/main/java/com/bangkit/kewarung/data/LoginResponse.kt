package com.bangkit.kewarung.data

data class LoginResponse(
    val message: String,
    val token: String,
    val user_id: String
)