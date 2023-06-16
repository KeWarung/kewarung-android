package com.bangkit.kewarung.home.kelola

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.kewarung.api.ApiConfig
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.authentication.data.DataXXX
import com.bangkit.kewarung.authentication.data.KelolaBarangResponse
import com.bangkit.kewarung.authentication.data.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KelolaBarangViewModel(private val pref: UserSession) : ViewModel() {

    val dataUser = MutableLiveData<ArrayList<DataXXX>>()

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun getUserId(): LiveData<String> {
        return pref.getUserId().asLiveData()
    }

    fun setAllProduct(token:String,userId: String){
        ApiConfig.getApiService().getDataKelola("jwt=$token",userId).enqueue(
            object: Callback<KelolaBarangResponse> {
                override fun onResponse(
                    call: Call<KelolaBarangResponse>,
                    response: Response<KelolaBarangResponse>
                ) {
                    if(response.isSuccessful){
                        dataUser.postValue(response.body()?.data)
                    }
                }

                override fun onFailure(call: Call<KelolaBarangResponse>, t: Throwable) {
                    Log.e( "onFailure: ",t.message.toString())
                }

            }
        )
    }

    fun getAllProduct():LiveData<ArrayList<DataXXX>>{
        return dataUser
    }

}