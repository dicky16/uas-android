package com.slim.uas_android.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.slim.uas_android.R
import com.slim.uas_android.model.ClassModel
import kotlinx.android.synthetic.main.class_item.view.*

class KelasAdapterPinjam(private val listFood: ArrayList<ClassModel>) : RecyclerView.Adapter<KelasAdapterPinjam.ListViewHolder>(){

    private var onItemClickCallBack: KelasAdapter.OnItemClickCallBack? = null
    fun setOnItemClickCallBack(onItemClickCallBack: KelasAdapter.OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.class_item, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listFood.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listFood[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(classModel: ClassModel) {
            with(itemView){
                tv_class.text = classModel.room_name

                itemView.setOnClickListener {onItemClickCallBack?.onItemClicked(classModel)}
            }
        }
    }
    interface OnItemClickCallBack {
        fun onItemClicked(data: ClassModel)
    }

}