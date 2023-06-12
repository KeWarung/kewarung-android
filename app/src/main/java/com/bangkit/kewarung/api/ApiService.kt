package com.bangkit.kewarung.api

import com.bangkit.kewarung.authentication.data.LoginResponse
import com.bangkit.kewarung.authentication.data.LogoutResponse
import com.bangkit.kewarung.authentication.data.ProfileResponse
import com.bangkit.kewarung.authentication.data.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

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

    @GET("users/{userId}")
    fun getProfile(
        @Header("Cookie") token: String,
        @Path("userId") userId: String
    ): Call<ProfileResponse>

    @GET("logout")
    fun logout():Call<LogoutResponse>
}
