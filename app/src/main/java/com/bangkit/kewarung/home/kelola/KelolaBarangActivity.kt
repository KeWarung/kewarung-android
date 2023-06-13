package com.bangkit.kewarung.home.kelola

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.kewarung.databinding.ActivityKelolaBarangBinding
import com.bangkit.kewarung.home.ProfilActivity

class KelolaBarangActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityKelolaBarangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelolaBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTambah.setOnClickListener {
            val intent = Intent(this@KelolaBarangActivity, TambahBarangActivity::class.java)
            startActivity(intent)
        }
    }
}