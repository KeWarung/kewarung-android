package com.bangkit.kewarung.home.kasir

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.bangkit.kewarung.authentication.data.DataXXX
import com.bangkit.kewarung.databinding.ActivityKasirBinding

class KasirActivity : AppCompatActivity() {

    private lateinit var kasir: DataXXX
    private lateinit var binding: ActivityKasirBinding
    private var jumlahAwal = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKasirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        kasir = intent.getParcelableExtra<DataXXX>(EXTRA_DETAIL) as DataXXX
        binding.apply {
            namaBarang.text = kasir.nama_produk
            stokBarang.text = kasir.stok.toString()
            harga.text = kasir.harga.toString()

            add.setOnClickListener {
                jumlahAwal += 1
                jumlah.text = jumlahAwal.toString()
                updateTotalBelanja()
            }

            minus.setOnClickListener {
                if (jumlahAwal > 1) {
                    jumlahAwal -= 1
                    jumlah.text = jumlahAwal.toString()
                    updateTotalBelanja()
                }
            }

            bayar.addTextChangedListener {
                val bayarText = it?.toString()
                val bayar = bayarText?.toIntOrNull() ?: 0
                totalBayar.text = bayarText.toString()
                updateKembalian(bayar)
            }
        }

        updateTotalBelanja()


        binding.btnTambah.setOnClickListener {
            val i = Intent(this, AddActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun updateTotalBelanja() {
        val belanja = kasir.harga * jumlahAwal
        binding.totalBelanja.text = belanja.toString()
    }

    private fun updateKembalian(bayar: Int) {
        val belanja = kasir.harga * jumlahAwal
        val kembalian = bayar- belanja
        binding.totalKembalian.text = kembalian.toString()
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}