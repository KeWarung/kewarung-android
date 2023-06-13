package com.bangkit.kewarung.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.kewarung.api.ApiConfig
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.authentication.data.Data
import com.bangkit.kewarung.authentication.data.DataX
import com.bangkit.kewarung.authentication.data.ProfileResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private  val pref: UserSession): ViewModel() {
    val dataUser = MutableLiveData<ProfileResponse>()

    fun getUserId(): LiveData<String> {
        return pref.getUserId().asLiveData()
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            pref.saveToken(token)
        }
    }

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun setDataUser(token:String,userId: String){
        ApiConfig.getApiService().getProfile("jwt=$token",userId).enqueue(
            object: Callback<ProfileResponse>{
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    if(response.isSuccessful){
                            dataUser.postValue(response.body())
                            Log.e("onFailure",response.body().toString())
                            Log.e("onFailure",response.message().toString())
                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.e( "onFailure: ",t.message.toString())
                }

            }
        )
    }

    fun getDetailUser():LiveData<ProfileResponse>{
        return dataUser
    }
}