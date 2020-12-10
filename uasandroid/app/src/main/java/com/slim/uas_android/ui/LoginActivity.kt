package com.slim.uas_android.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.slim.uas_android.R
import com.slim.uas_android.api.DataRepository
import com.slim.uas_android.model.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        button.setOnClickListener {
            val email = edt_email.text.toString()
            val password = edt_password.text.toString()
            login(email,password)
        }
    }

    private fun login(email : String, password : String)
    {
        val postServices = DataRepository.create()
        postServices.login(email, password).enqueue(object : Callback<List<LoginResponse>> {

            override fun onResponse(call: Call<List<LoginResponse>>, response: Response<List<LoginResponse>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.map {
                        val status = it.success
                        if(status) {
                            val intent = Intent(applicationContext, HomeActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@LoginActivity, "Email atau Password Anda salah!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<LoginResponse>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }

}
