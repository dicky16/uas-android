package com.slim.uas_android.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.slim.uas_android.R
import com.slim.uas_android.storage.SharePrefManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getPref()
        btn_pinjam_ruangan.setOnClickListener {
            val intent = Intent(applicationContext, PinjamanActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        btn_class_monitoring.setOnClickListener {
            val intent = Intent(applicationContext, ControllActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }
    private fun getPref() {
        val tes = SharePrefManager.getInstance(this).isLoggedIn
        Log.d("tag", "bool = $tes")
    }

    override fun onStart() {
        super.onStart()

        if(!SharePrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
}