package com.slim.uas_android.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.slim.uas_android.R
import com.slim.uas_android.storage.SharePrefManager
import com.slim.uas_android.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val data = SharePrefManager.getInstance(this).getUserData
        tv_nama.text = data[1]
        tv_email.text = data[2]

        btn_logout.setOnClickListener {
            SharePrefManager.getInstance(this).clear()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}