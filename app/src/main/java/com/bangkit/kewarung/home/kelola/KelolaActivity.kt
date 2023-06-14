package com.bangkit.kewarung.home.kelola

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.kewarung.authentication.data.DataXXX
import com.bangkit.kewarung.databinding.ActivityKelolaBinding

class KelolaActivity : AppCompatActivity() {

    private lateinit var kelolaBarang: DataXXX
    private lateinit var  binding: ActivityKelolaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelolaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        kelolaBarang = intent.getParcelableExtra<DataXXX>(EXTRA_DETAIL) as DataXXX
        binding.apply {
            namaBarang.text = kelolaBarang.nama_produk
            stokBarang.text = kelolaBarang.stok.toString()
            hargaBarang.text = kelolaBarang.harga.toString()
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}