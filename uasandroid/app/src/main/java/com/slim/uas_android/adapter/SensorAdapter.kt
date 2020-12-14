package com.slim.uas_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.slim.uas_android.R
import com.slim.uas_android.model.SensorModel
import kotlinx.android.synthetic.main.sensor_item.view.*

class SensorAdapter(private val listFood: ArrayList<SensorModel>) : RecyclerView.Adapter<SensorAdapter.ListViewHolder>(){
    var perangkatId:Int = 0
    //firebase
    lateinit var ref : DatabaseReference

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.sensor_item, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listFood.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listFood[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(sensor: SensorModel) {
            with(itemView){

                if(sensor.key == "id") {
                    perangkatId = sensor.value.toInt()
                    btn_switch_perangkat.isClickable = false
                }
                tv_perangkat.text = sensor.key
                //firebase
                if(sensor.value == "on") {
                    btn_switch_perangkat.isChecked = true
                }
                btn_switch_perangkat.setOnClickListener {

                    ref = FirebaseDatabase.getInstance().getReference("kelas").child("$perangkatId")
                    ref.addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {

                        }
                        override fun onDataChange(p0: DataSnapshot) {
                            if(sensor.value == "on") {
                                ref.child(sensor.key).setValue("off")
                            } else {
                                ref.child(sensor.key).setValue("on")
                            }
                            Toast.makeText(context, "message $perangkatId",
                                Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    }

}