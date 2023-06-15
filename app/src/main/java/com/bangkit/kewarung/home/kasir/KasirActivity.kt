package com.bangkit.kewarung.home.kasir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.bangkit.kewarung.authentication.RegisterActivity
import com.bangkit.kewarung.authentication.data.DataXXX
import com.bangkit.kewarung.databinding.ActivityKasirBinding
import com.bangkit.kewarung.home.kelola.KelolaActivity

class KasirActivity : AppCompatActivity() {

    private lateinit var kasir: DataXXX
    private lateinit var binding: ActivityKasirBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKasirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        kasir = intent.getParcelableExtra<DataXXX>(EXTRA_DETAIL) as DataXXX
        binding.apply {
            namaBarang.text = kasir.nama_produk
            stokBarang.text = kasir.stok.toString()
            harga.text = kasir.harga.toString()
            totalBelanja.text = kasir.harga.toString()
            bayar.addTextChangedListener {
                val bayarText = it?.toString()
                val bayar = bayarText?.toIntOrNull() ?: 0
                totalBayar.text = bayarText.toString()
                val kembalian = kasir.harga - bayar
                totalKembalian.text = kembalian.toString()
            }

        }

        binding.btnTambah.setOnClickListener{
            val i = Intent(this, AddActivity::class.java)
            startActivity(i)
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}