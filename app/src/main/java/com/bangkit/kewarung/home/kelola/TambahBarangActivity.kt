package com.bangkit.kewarung.home.kelola

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.kewarung.databinding.ActivityTambahBarangBinding

class TambahBarangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahBarangBinding

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUploudFoto.setOnClickListener{
            UpluodImage()
        }
    }

    private fun UpluodImage(){
        val intent =  Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            binding.image.setImageURI(data?.data)
        }
    }
}