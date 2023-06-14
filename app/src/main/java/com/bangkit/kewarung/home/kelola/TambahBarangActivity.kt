package com.bangkit.kewarung.home.kelola

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.bangkit.kewarung.api.ApiConfig
import com.bangkit.kewarung.authentication.UserSession
import com.bangkit.kewarung.authentication.data.AddBarangResponse
import com.bangkit.kewarung.authentication.data.reduceFileImage
import com.bangkit.kewarung.authentication.data.uriToFile
import com.bangkit.kewarung.databinding.ActivityTambahBarangBinding
import com.bangkit.kewarung.home.MainViewModel
import com.bangkit.kewarung.home.ViewModelFactory
import com.bangkit.kewarung.home.dataStore
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class TambahBarangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahBarangBinding
    private var getFile: File? = null
    private lateinit var tambahBarangViewModel: TambahBarangViewModel
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
        binding.btnSimpan.setOnClickListener{
            Simpan()
            intentMain()
        }

        val pref = UserSession.getInstance(dataStore)

        tambahBarangViewModel = ViewModelProvider(this, ViewModelFactory(pref))[TambahBarangViewModel::class.java]
    }

    private fun UpluodImage(){
        val intent =  Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun Simpan(){
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val nama_produk = binding.namaBarang.text.toString()
            val harga = binding.harga.text.toString()
            val hargaProduk = Integer.parseInt(harga)
            val stok = 0
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestImageFile
            )
            tambahBarangViewModel.getToken().observe(this) { token: String ->
                tambahBarangViewModel.getUserId().observe(this){userId: String ->
                    val service = ApiConfig.getApiService()
                        .addBarang("jwt=$token",userId,nama_produk,hargaProduk,stok)
                    service.enqueue(object : Callback<AddBarangResponse> {
                        override fun onResponse(
                            call: Call<AddBarangResponse>,
                            response: Response<AddBarangResponse>
                        ) {
                            if (response.isSuccessful) {
                                val responseBody = response.body()
                                if (responseBody != null && response.errorBody().toString() == "404") {
                                    Toast.makeText(
                                        this@TambahBarangActivity,
                                        "Berhasil Uploud",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    this@TambahBarangActivity,
                                    "Gagal Uploud",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<AddBarangResponse>, t: Throwable) {
                            Toast.makeText(
                                this@TambahBarangActivity,
                                "Gagal instance Retrofit",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }
            }
        } else {
            Toast.makeText(this@TambahBarangActivity, "Silakan masukkan berkas gambar terlebih dahulu.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun intentMain() {
        val i = Intent(this, KelolaBarangActivity::class.java)
        startActivity(i)
        showLoading(false)
        finish()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this@TambahBarangActivity)
                getFile = myFile
                binding.image.setImageURI(uri)
            }
        }
    }
}