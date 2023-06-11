package com.bangkit.kewarung.authentication

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bangkit.kewarung.R
import com.bangkit.kewarung.api.ApiConfig
import com.bangkit.kewarung.authentication.data.LoginResponse
import com.bangkit.kewarung.databinding.ActivityLoginBinding
import com.bangkit.kewarung.home.MainActivity
import com.bangkit.kewarung.home.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserSession.getInstance(dataStore)

        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(pref)
        )[LoginViewModel::class.java]

        binding.tvSignUp.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }

        binding.btnLogin.setOnClickListener {
            login()
        }



    }

    private fun login() {
        showLoading(true)
        val client = ApiConfig.getApiService()
            .login(binding.etEmail.text.toString(), binding.etPass.text.toString())
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    if (response.errorBody().toString() == "404") {
                        showLoading(false)
                        Toast.makeText(this@LoginActivity, responseBody.message, Toast.LENGTH_LONG)
                            .show()
                    } else {
                        saveSession(responseBody)
                        Toast.makeText(
                            this@LoginActivity,
                            getString(R.string.login_sucess),
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                } else {
                    showLoading(false)
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    Toast.makeText(this@LoginActivity, response.message().toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                showLoading(false)
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                Toast.makeText(this@LoginActivity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun saveSession(loginResponse: LoginResponse) {
        showLoading(false)
        loginViewModel.saveToken(loginResponse.token)
        val i = Intent(this, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
        finish()
    }
}
