package com.bangkit.kewarung.authentication

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserSession private constructor(private val dataStore: DataStore<Preferences>) {
    private val KEY = stringPreferencesKey("user_token")
    private val USER_ID = stringPreferencesKey("user_id")

    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
                preferences[KEY] ?: ""
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[KEY] = token
        }
    }

    fun getUserId():Flow<String>{
        return dataStore.data.map{preferences ->
            preferences[USER_ID]?: ""
        }
    }

    suspend fun saveUserId(userId: String){
        dataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserSession? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserSession {
            return INSTANCE ?: synchronized(this) {
                val instance = UserSession(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}