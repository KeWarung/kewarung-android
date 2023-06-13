package com.bangkit.kewarung.home

import android.util.Log
import androidx.lifecycle.*
import com.bangkit.kewarung.api.ApiConfig
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.authentication.data.LogoutResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (private val pref: UserSession) : ViewModel() {

    val userLogOut = MutableLiveData<LogoutResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun logout(){
        ApiConfig.getApiService().logout().enqueue(
            object : Callback<LogoutResponse>{
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
                    if (response.isSuccessful){
                        userLogOut.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    Log.e( "onFailure: ",t.message.toString())
                }

            }
        )
    }


}