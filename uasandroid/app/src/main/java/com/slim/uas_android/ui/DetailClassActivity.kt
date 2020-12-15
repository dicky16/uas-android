package com.slim.uas_android.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.slim.uas_android.R
import com.slim.uas_android.adapter.KelasAdapter
import com.slim.uas_android.adapter.SensorAdapter
import com.slim.uas_android.model.ClassModel
import com.slim.uas_android.model.SensorModel
import kotlinx.android.synthetic.main.activity_controll.*
import kotlinx.android.synthetic.main.activity_detail_class.*

class DetailClassActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var list : ArrayList<SensorModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_class)
        val nama:String? = intent.getStringExtra("kelas")
        val id:Int? = intent.getIntExtra("id",0)
        btn_inventory.setOnClickListener {
            val intent = Intent(applicationContext, InventoryActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("kelas", nama)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        tv_detail_class.text = nama
        //firebase
        ref = FirebaseDatabase.getInstance().getReference("kelas").child(""+id)
        list = arrayListOf()

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    list.clear()
                    for (h in p0.children){
                        val key = h.key.toString()
                        val value = h.value.toString()
                        val sensor = SensorModel(
                            key,
                            value
                        )
//                        if(key != "id") {
                            list.add(sensor)
//                        }
                        showRecyclerSensor(list)
                    }
                }
            }
        })
    }

    private fun showRecyclerSensor(list: ArrayList<SensorModel>) {
        rv_sensor.layoutManager = LinearLayoutManager(this)
        val sensorAdapter = SensorAdapter(list)
        sensorAdapter.notifyDataSetChanged()
        rv_sensor.adapter = sensorAdapter
        sensorAdapter.setOnItemClickCallBack(object : SensorAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: SensorModel) {
                setSensor(data)
            }
        })
    }

    fun setSensor (sensor: SensorModel) {
        val id:Int? = intent.getIntExtra("id",0)
        if(sensor.value == "on") {
                        ref = FirebaseDatabase.getInstance().getReference("kelas").child("$id")
                        ref.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {

                            }
                            override fun onDataChange(p0: DataSnapshot) {
                                ref.child(sensor.key).setValue("off")
                            }
                        })
                    } else {
                        ref = FirebaseDatabase.getInstance().getReference("kelas").child("$id")
                        ref.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {

                            }
                            override fun onDataChange(p0: DataSnapshot) {
                                ref.child(sensor.key).setValue("on")
                            }
                        })
                    }
    }

}
