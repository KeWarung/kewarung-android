package com.bangkit.kewarung.api

import com.bangkit.kewarung.authentication.account.Account
import com.bangkit.kewarung.authentication.data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun userLogin(
        @Body user: Account
    ): Call<LoginResponse>
}