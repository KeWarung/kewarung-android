package com.bangkit.kewarung.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.kewarung.authentication.LoginViewModel
import com.bangkit.kewarung.authentication.UserSession

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
            else -> throw IllegalArgumentException("Unknown Viewmodel Class: " + modelClass.name)
        }
    }

}