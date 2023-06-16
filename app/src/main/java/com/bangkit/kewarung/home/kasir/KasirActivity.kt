package com.bangkit.kewarung.home.kasir

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import com.bangkit.kewarung.R
import com.bangkit.kewarung.authentication.data.DataXXX
import com.bangkit.kewarung.databinding.ActivityKasirBinding
import com.bangkit.kewarung.home.MainActivity
import com.google.android.material.button.MaterialButton

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
        val kembalian = bayar - belanja
        binding.totalKembalian.text = kembalian.toString()
        binding.btnBayar.setOnClickListener {
            if (kembalian>0){
                val view = View.inflate(this@KasirActivity, R.layout.dialog_succes, null)
                val builder = AlertDialog.Builder(this@KasirActivity)
                builder.setView(view)
                val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                val btnBack = view.findViewById<MaterialButton>(R.id.btn_back)

                btnBack?.setOnClickListener {
                    dialog.dismiss()
                    val i = Intent(this@KasirActivity,MainActivity::class.java)
                    startActivity(i)
                }
            }else{
                Toast.makeText(this,"Uang kurang",Toast.LENGTH_LONG).show()
            }
        }
    }


    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}