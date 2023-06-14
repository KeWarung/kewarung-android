package com.bangkit.kewarung.home.kasir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.kewarung.authentication.RegisterActivity
import com.bangkit.kewarung.databinding.ActivityKasirBinding

class KasirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKasirBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKasirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTambah.setOnClickListener{
            val i = Intent(this, AddActivity::class.java)
            startActivity(i)
        }
    }
}