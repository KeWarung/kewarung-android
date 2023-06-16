package com.bangkit.kewarung.home.kelola

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.kewarung.api.ApiConfig
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.authentication.data.KelolaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KelolaViewModel(private val pref: UserSession): ViewModel() {

    val dataBarang = MutableLiveData<KelolaResponse>()

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun SetDataBarang(token:String,productId:String,harga:Int,stok:Int){
        ApiConfig.getApiService().updateDataBarang("jwt=$token",productId,harga,stok).enqueue(
            object: Callback<KelolaResponse>{
                override fun onResponse(
                    call: Call<KelolaResponse>,
                    response: Response<KelolaResponse>
                ) {
                    if (response.isSuccessful){
                        dataBarang.postValue(response.body())
                    }else{
                        dataBarang.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<KelolaResponse>, t: Throwable) {
                    Log.e("onFailure",t.message.toString())
                }
            }
        )
    }

    fun getDataBarang():LiveData<KelolaResponse>{
        return dataBarang
    }
}