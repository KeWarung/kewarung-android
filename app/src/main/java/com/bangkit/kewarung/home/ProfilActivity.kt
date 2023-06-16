package com.bangkit.kewarung.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bangkit.kewarung.authentication.LoginActivity
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.databinding.ActivityProfilBinding
import com.bangkit.kewarung.home.kelola.TambahBarangActivity

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
                        profileViewModel.setDataUser(token,userId)
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

        binding.btnLogout.setOnClickListener {
            profileViewModel.saveToken("")
            val i = Intent(this@ProfilActivity, LoginActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
    }
}