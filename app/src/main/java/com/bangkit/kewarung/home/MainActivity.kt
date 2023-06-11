package com.bangkit.kewarung.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class MainActivity : AppCompatActivity() {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

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

            }
        }


        binding.apply {
            btnProfile.setOnClickListener{
                val intent = Intent(this@MainActivity, ProfilActivity::class.java)
                startActivity(intent)
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
        }
    }
}