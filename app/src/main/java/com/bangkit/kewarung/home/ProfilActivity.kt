package com.bangkit.kewarung.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilBinding
    private lateinit var profileViewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserSession.getInstance(dataStore)
        profileViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[ProfileViewModel::class.java]
        profileViewModel.getToken().observe(this){ token: String->
            if(token.isEmpty()){
                Log.e("onFailure","token tidak ditemukan")
            }else{
                profileViewModel.getUserId().observe(this) { userId: String ->
                    if (userId.isEmpty()) {
                        Log.e("onFailure","userId tidak ada")
                    } else {
                        Log.e("onFailure",userId)
                        profileViewModel.setDataUser(userId,token)
                        profileViewModel.getDetailUser().observe(this) {
                            binding.apply {
                                tvEmail.text = it.data.email
                                tvName.text = it.data.nama_toko
                            }
                        }
                    }
                }
            }
            }
    }
}