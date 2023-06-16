package com.bangkit.kewarung.home.kelola

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.authentication.data.DataXXX
import com.bangkit.kewarung.databinding.ActivityKelolaBinding
import com.bangkit.kewarung.home.ViewModelFactory
import com.bangkit.kewarung.home.dataStore

class KelolaActivity : AppCompatActivity() {

    private lateinit var kelolaBarang: DataXXX
    private lateinit var  binding: ActivityKelolaBinding
    private lateinit var kelolaViewModel: KelolaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelolaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserSession.getInstance(dataStore)
        kelolaViewModel = ViewModelProvider(this,ViewModelFactory(pref))[KelolaViewModel::class.java]
        kelolaBarang = intent.getParcelableExtra<DataXXX>(EXTRA_DETAIL) as DataXXX
        binding.apply {
            namaBarang.text = kelolaBarang.nama_produk
            stokBarang.text = kelolaBarang.stok.toString()
            hargaBarang.text = kelolaBarang.harga.toString()
            btnSimpan.setOnClickListener {
                kelolaViewModel.getToken().observe(this@KelolaActivity){token: String->
                    if (token.isEmpty()){
                        Log.e("onFailure","token tidak ada")
                    }else{
                        binding.apply {
                            val productId = kelolaBarang.id_produk
                            val harga = harga.text.toString().trim()
                            val hargaProduk = Integer.parseInt(harga)
                            val stok = stok.text.toString()
                            val stokAwal = kelolaBarang.stok.toString()
                            val stokProduk = Integer.parseInt(stok.trim())+ Integer.parseInt(stokAwal)
                            kelolaViewModel.SetDataBarang(token,productId,hargaProduk,stokProduk)
                            kelolaViewModel.getDataBarang().observe(this@KelolaActivity){
                                Toast.makeText(this@KelolaActivity,it.message,Toast.LENGTH_LONG).show()
                                intentMain()
                            }
                        }
                    }
                }
            }
        }

    }

    private fun intentMain() {
        val i = Intent(this, KelolaBarangActivity::class.java)
        startActivity(i)
        finish()
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}


