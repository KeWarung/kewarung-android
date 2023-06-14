package com.bangkit.kewarung.api

import com.bangkit.kewarung.authentication.data.AddBarangResponse
import com.bangkit.kewarung.authentication.data.LoginResponse
import com.bangkit.kewarung.authentication.data.LogoutResponse
import com.bangkit.kewarung.authentication.data.ProfileResponse
import com.bangkit.kewarung.authentication.data.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

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

    @Multipart
    @POST("products/{userId}")
    fun addBarang(
        @Header("Cookie") token: String,
        @Path("userId") userId: String,
        @Part("nama_produk") nama_produk: String,
        @Part("harga") harga: Int,
        @Part("stok") stok: Int,
        @Part file: MultipartBody.Part
    ):Call<AddBarangResponse>
}
