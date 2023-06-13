package com.bangkit.kewarung.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: UserSession) : ViewModel() {
    fun saveToken(token: String) {
        viewModelScope.launch {
            pref.saveToken(token)
        }
    }
    fun saveUserId(userId: String){
        viewModelScope.launch {
            pref.saveUserId(userId)
        }
    }
}