package com.bangkit.kewarung.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bangkit.kewarung.authentication.LoginActivity
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.databinding.ActivityMainBinding
import com.bangkit.kewarung.home.kasir.KasirActivity
import com.bangkit.kewarung.home.kelola.KelolaActivity
import com.bangkit.kewarung.home.laporan.LaporanActivity

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserSession.getInstance(dataStore)

        mainViewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        mainViewModel.getToken().observe(
            this
        ) { token: String ->
            if (token.isEmpty()) {
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                finish()
            } else {
                Log.e("onFailure",token)
            }
        }


        binding.apply {
            btnProfile.setOnClickListener{
                val i = Intent(this@MainActivity, ProfilActivity::class.java)
                startActivity(i)
            }
            kasir.setOnClickListener {
                val intent = Intent(this@MainActivity, KasirActivity::class.java)
                startActivity(intent)
            }
            kelola.setOnClickListener {
                val intent = Intent(this@MainActivity, KelolaActivity::class.java)
                startActivity(intent)
            }
            laporan.setOnClickListener {
                val intent = Intent(this@MainActivity, LaporanActivity::class.java)
                startActivity(intent)
            }
            btnLogout.setOnClickListener {
                mainViewModel.saveToken("")

                val i = Intent(this@MainActivity, LoginActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
            }
        }
    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}