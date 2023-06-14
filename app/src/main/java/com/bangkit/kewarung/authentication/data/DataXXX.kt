package com.bangkit.kewarung.authentication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataXXX(
    val foto: String,
    val harga: Int,
    val id_produk: String,
    val id_user: String,
    val nama_produk: String,
    val stok: Int
):Parcelable