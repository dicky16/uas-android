package com.slim.uas_android.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.slim.uas_android.R
import kotlinx.android.synthetic.main.activity_detail_class.*

class DetailClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_class)
        var nama:String? = intent.getStringExtra("kelas")
        var id:String? = intent.getStringExtra("id")
        btn_inventory.setOnClickListener {
            val intent = Intent(applicationContext, InventoryActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("kelas", nama)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        tv_detail_class.text = nama
    }
}