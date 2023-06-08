package com.bangkit.kewarung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.kewarung.databinding.ActivityKelolaBinding

class KelolaActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityKelolaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelolaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}