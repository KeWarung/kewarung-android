package com.bangkit.kewarung.home.kasir

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.kewarung.authentication.data.DataXXX
import com.bangkit.kewarung.databinding.ListSearchBinding
import com.bangkit.kewarung.home.kelola.KelolaActivity
import com.bumptech.glide.Glide

class KasirAdapter: RecyclerView.Adapter<KasirAdapter.ViewHolder>() {
    private val listStoryData = ArrayList<DataXXX>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listStoryData[position])
    }

    override fun getItemCount(): Int {
        return listStoryData.size
    }

    inner class ViewHolder(private val binding: ListSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: DataXXX) {
            binding.apply {
                namaBarang.text = product.nama_produk
                harga.text = product.harga.toString()
                stokBarang.text = product.stok.toString()

                tambah.setOnClickListener {
                    val intent = Intent(tambah.context, KasirActivity::class.java).apply {
                        putExtra(KasirActivity.EXTRA_DETAIL,product)
                    }
                    it.context.startActivity(intent)
                }
            }
        }
    }

    fun setProductData(product: ArrayList<DataXXX>) {
        this.listStoryData.clear()
        this.listStoryData.addAll(product)
        notifyDataSetChanged()
    }
}