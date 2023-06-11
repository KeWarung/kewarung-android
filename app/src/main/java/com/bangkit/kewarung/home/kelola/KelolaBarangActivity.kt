package com.bangkit.kewarung.home.kelola

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.kewarung.databinding.ActivityKelolaBarangBinding

class KelolaBarangActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityKelolaBarangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelolaBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}