package com.bangkit.kewarung.api

import com.bangkit.kewarung.authentication.account.Account
import com.bangkit.kewarung.authentication.data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}