package com.slim.uas_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slim.uas_android.R
import com.slim.uas_android.model.ClassModel
import kotlinx.android.synthetic.main.class_item.view.*

class ClassAdapter(private  val listClass: ArrayList<ClassModel>) : RecyclerView.Adapter<ClassAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.class_item, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listClass.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listClass[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(classModel: ClassModel) {
            with(itemView){
                tv_class.text = classModel.room_name
            }
        }
    }
}