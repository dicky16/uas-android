package com.slim.uas_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.slim.uas_android.R
import com.slim.uas_android.model.InventoryModel
import kotlinx.android.synthetic.main.activity_inventory.view.*
import kotlinx.android.synthetic.main.class_item.view.*
import kotlinx.android.synthetic.main.inventory_item.view.*

class InventoryAdapter(private val listClass: ArrayList<InventoryModel>) : RecyclerView.Adapter<InventoryAdapter.ListViewHolder>(){

    private var onItemClickCallBack: OnItemClickCallBack? = null
    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.inventory_item, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listClass.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listClass[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(inventoryModel: InventoryModel) {
            with(itemView){
                tv_nama_barang.text = inventoryModel.nama_barang
                tv_jumlah_inventory.text = inventoryModel.jumlah
                itemView.setOnClickListener {onItemClickCallBack?.onItemClicked(inventoryModel)}
            }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: InventoryModel)
    }
}