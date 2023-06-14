package com.bangkit.kewarung.home.kelola

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.kewarung.authentication.data.DataXXX
import com.bangkit.kewarung.databinding.ListKelolaBinding
import com.bumptech.glide.Glide

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private val listStoryData = ArrayList<DataXXX>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListKelolaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listStoryData[position])
    }

    override fun getItemCount(): Int {
        return listStoryData.size
    }

    inner class ViewHolder(private val binding: ListKelolaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: DataXXX) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(product.foto)
                    .into(image)
                namaBarang.text = product.nama_produk
                btnDetail.setOnClickListener {
                    val intent = Intent(btnDetail.context, KelolaActivity::class.java).apply {
                        putExtra(KelolaActivity.EXTRA_DETAIL,product)
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
