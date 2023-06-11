package com.bangkit.kewarung.api

import com.bangkit.kewarung.authentication.account.Account
import com.bangkit.kewarung.authentication.data.LoginResponse
import com.bangkit.kewarung.authentication.data.RegisterResponse
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

    @FormUrlEncoded
    @POST("users")
    fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("nama_toko") nama_toko: String
    ):Call<RegisterResponse>
}
