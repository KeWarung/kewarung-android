package com.bangkit.kewarung.home.kasir

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.kewarung.api.ApiConfig
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.authentication.data.DataXXX
import com.bangkit.kewarung.authentication.data.KelolaBarangResponse
import com.bangkit.kewarung.authentication.data.SearchBarangResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddViewModel(private val pref: UserSession) : ViewModel() {

    val dataBarang = MutableLiveData<DataXXX>()

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun getUserId(): LiveData<String> {
        return pref.getUserId().asLiveData()
    }

    fun setAllProduct(token:String,userId: String,name: String){
        ApiConfig.getApiService().getDataSearch("jwt=$token","$name&$userId").enqueue(
            object :Callback<SearchBarangResponse>{
                override fun onResponse(
                    call: Call<SearchBarangResponse>,
                    response: Response<SearchBarangResponse>
                ) {
                    if (response.isSuccessful){
                        dataBarang.postValue(response.body()?.data)
                    }
                }

                override fun onFailure(call: Call<SearchBarangResponse>, t: Throwable) {
                    Log.e("onFailure",t.message.toString())
                }

            }
        )
    }
    fun getAllProduct(): LiveData<DataXXX> {
        return dataBarang
    }

}