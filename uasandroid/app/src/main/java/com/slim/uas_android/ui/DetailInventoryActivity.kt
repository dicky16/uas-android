package com.slim.uas_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.slim.uas_android.R

class DetailInventoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_inventory)
        var label = intent.getStringExtra("label")
        Toast.makeText(this, "$label", Toast.LENGTH_SHORT).show()
    }
}