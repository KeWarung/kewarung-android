package com.bangkit.kewarung.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.kewarung.databinding.ActivityMainBinding
import com.bangkit.kewarung.home.kasir.KasirActivity
import com.bangkit.kewarung.home.kelola.KelolaActivity
import com.bangkit.kewarung.home.laporan.LaporanActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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