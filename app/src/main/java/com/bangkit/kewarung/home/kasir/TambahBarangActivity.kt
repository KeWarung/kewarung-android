package com.bangkit.kewarung.home.kasir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.kewarung.databinding.ActivityTambahBarangBinding

class TambahBarangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahBarangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}