package com.slim.uas_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.slim.uas_android.R
import kotlinx.android.synthetic.main.activity_inventory.*

class InventoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)
        var nama:String? = intent.getStringExtra("kelas")
        tv_kelas_inventory.text = nama
    }
}