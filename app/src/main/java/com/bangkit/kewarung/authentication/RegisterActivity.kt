package com.bangkit.kewarung.authentication

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bangkit.kewarung.api.ApiConfig
import com.bangkit.kewarung.authentication.data.RegisterResponse
import com.bangkit.kewarung.databinding.ActivityRegisterBinding
import com.bangkit.kewarung.home.laporan.LaporanActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSudah.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.register.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val passwordInput = binding.password.text.toString().trim()
        val confirmPass = binding.confirmpass.text.toString().trim()
        if(passwordInput == confirmPass) {
            showLoading(true)
            val client = ApiConfig.getApiService().register(
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.namatoko.text.toString()
            )
            client.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {
                        if (response.errorBody().toString() == "404") {
                            showLoading(false)
                            Toast.makeText(
                                this@RegisterActivity,
                                responseBody.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            goLogin()
                            Toast.makeText(
                                this@RegisterActivity,
                                responseBody.message,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    } else {
                        showLoading(false)
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                        Toast.makeText(this@RegisterActivity, response.message(), Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    showLoading(false)
                    Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                    Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_LONG).show()
                }

            })
        }else{
            Toast.makeText(this, "Password Tidak Sama", Toast.LENGTH_SHORT).show()
        }
    }
    private fun goLogin() {
        val i = Intent(this, LoginActivity::class.java)
        i.putExtra("email", binding.email.text.toString())
        i.putExtra("password", binding.password.text.toString())
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(i)
        showLoading(false)
        finish()
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
}