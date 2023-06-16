package com.bangkit.kewarung.home.kelola

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.databinding.ActivityKelolaBarangBinding
import com.bangkit.kewarung.home.ViewModelFactory
import com.bangkit.kewarung.home.dataStore

class KelolaBarangActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityKelolaBarangBinding
    private lateinit var kelolaBarangViewModel: KelolaBarangViewModel
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelolaBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserSession.getInstance(dataStore)
        kelolaBarangViewModel = ViewModelProvider(this, ViewModelFactory(pref))[KelolaBarangViewModel::class.java]

        productAdapter = ProductAdapter()
        initView()
        kelolaBarangViewModel.getToken().observe(this){ token: String->
            if(token.isEmpty()){
                Log.e("onFailure","token tidak ditemukan")
            }else{
                kelolaBarangViewModel.getUserId().observe(this) { userId: String ->
                    if (userId.isEmpty()) {
                        Log.e("onFailure","userId tidak ada")
                    } else {
                        kelolaBarangViewModel.setAllProduct(token,userId)
                        kelolaBarangViewModel.getAllProduct().observe(this){
                            productAdapter.setProductData(it)
                        }
                    }
                }
            }
        }

        binding.btnTambah.setOnClickListener {
            val intent = Intent(this@KelolaBarangActivity, TambahBarangActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun initView() {
        binding.apply {
            barang.apply {
                layoutManager = LinearLayoutManager(this@KelolaBarangActivity)
                setHasFixedSize(true)
                adapter = productAdapter
            }

        }
    }
}