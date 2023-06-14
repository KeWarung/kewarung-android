package com.bangkit.kewarung.home.kelola

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.kewarung.authentication.UserSession

class TambahBarangViewModel (private val pref: UserSession) : ViewModel() {
    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun getUserId(): LiveData<String> {
        return pref.getUserId().asLiveData()
    }
}