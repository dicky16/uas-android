package com.slim.uas_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.slim.uas_android.R
import com.slim.uas_android.model.InventoryModel
import com.slim.uas_android.model.StatusPinjamModel
import com.slim.uas_android.storage.SharePrefManager
import kotlinx.android.synthetic.main.activity_pinjaman.view.*
import kotlinx.android.synthetic.main.inventory_item.view.*
import kotlinx.android.synthetic.main.status_item.view.*

class StatusPinjamAdapter(private val listClass: ArrayList<StatusPinjamModel>) : RecyclerView.Adapter<StatusPinjamAdapter.ListViewHolder>(){

    private var onItemClickCallBack: OnItemClickCallBack? = null
    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.status_item, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listClass.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listClass[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(statusPinjamModel: StatusPinjamModel) {
            with(itemView){
                val data = SharePrefManager.getInstance(context).getUserData
                tv_nama_pinjam.text = statusPinjamModel.room_name
                tv_mulai.text = statusPinjamModel.mulai_pinjam
                tv_selesai.text = statusPinjamModel.selesai_pinjam
                tv_status.text = statusPinjamModel.status
                if(data[3].toString().equals("admin")) {
                    tv_status.visibility = View.GONE
                    if(statusPinjamModel.status.equals("pending")) {
                        tv_pinjam_terima.visibility = View.VISIBLE
                        tv_pinjam_tolak.visibility = View.VISIBLE
                    } else if(statusPinjamModel.status.equals("diterima")) {
                        tv_pinjam_tolak.visibility = View.VISIBLE
                    } else if(statusPinjamModel.status.equals("ditolak")){
                        tv_pinjam_terima.visibility = View.VISIBLE
                    }
                    tv_pinjam_terima.setOnClickListener {
//                        Toast.makeText(context, "ok berima", Toast.LENGTH_SHORT).show()
                        onItemClickCallBack?.onItemClickedTerima(statusPinjamModel)
                    }

                    tv_pinjam_tolak.setOnClickListener {
//                        Toast.makeText(context, "ok tolak", Toast.LENGTH_SHORT).show()
                        onItemClickCallBack?.onItemClickedTolak(statusPinjamModel)
                    }
                }
//                itemView.setOnClickListener {onItemClickCallBack?.onItemClicked(inventoryModel)}
            }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClickedTerima(data: StatusPinjamModel)
        fun onItemClickedTolak(data: StatusPinjamModel)
    }
}