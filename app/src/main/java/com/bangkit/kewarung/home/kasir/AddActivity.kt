package com.bangkit.kewarung.home.kasir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.databinding.ActivityAddBinding
import com.bangkit.kewarung.home.ViewModelFactory
import com.bangkit.kewarung.home.dataStore
import com.bangkit.kewarung.home.kelola.KelolaBarangViewModel
import com.bangkit.kewarung.home.kelola.ProductAdapter

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var addViewModel: AddViewModel
    private lateinit var kasirAdapter: KasirAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserSession.getInstance(dataStore)
        addViewModel = ViewModelProvider(this, ViewModelFactory(pref))[AddViewModel::class.java]

        kasirAdapter = KasirAdapter()
        initView()
        addViewModel.getToken().observe(this){ token: String->
            if(token.isEmpty()){
                Log.e("onFailure","token tidak ditemukan")
            }else{
                addViewModel.getUserId().observe(this) { userId: String ->
                    if (userId.isEmpty()) {
                        Log.e("onFailure","userId tidak ada")
                    } else {
                        Log.e("onFailure",token)
                        Log.e("onFailure",userId)
                        addViewModel.setAllProduct(token,userId)
                        addViewModel.getAllProduct().observe(this){
                            kasirAdapter.setProductData(it)
                        }
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.apply {
            barang.apply {
                layoutManager = LinearLayoutManager(this@AddActivity)
                setHasFixedSize(true)
                adapter = kasirAdapter
            }

        }
    }
}
