package com.bangkit.kewarung.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.kewarung.authentication.LoginViewModel
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.home.kelola.TambahBarangViewModel

class ViewModelFactory(private val pref: UserSession) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java)->{
                ProfileViewModel(pref) as T
            }
            modelClass.isAssignableFrom(TambahBarangViewModel::class.java)->{
                TambahBarangViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown Viewmodel Class: " + modelClass.name)
        }
    }

}