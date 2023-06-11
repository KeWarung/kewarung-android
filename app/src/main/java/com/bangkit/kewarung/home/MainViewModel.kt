package com.bangkit.kewarung.home

import androidx.lifecycle.*
import com.bangkit.kewarung.authentication.UserSession
import kotlinx.coroutines.launch

class MainViewModel (private val pref: UserSession) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }
    fun saveToken(token: String) {
        viewModelScope.launch {
            pref.saveToken(token)
        }
    }

}