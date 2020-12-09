package com.slim.uas_android.ui

import android.icu.text.DateFormat.MEDIUM
import android.os.Bundle
import android.renderscript.RenderScript.Priority
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.slim.uas_android.R
import com.slim.uas_android.data.DataRepository
import com.slim.uas_android.data.PostModel
import com.slim.uas_android.data.PostServices
import com.slim.uas_android.model.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        button.setOnClickListener {
            login("","")
//            jajalLogin()
        }
    }

    private fun login(username : String, password : String)
    {
        val postServices = DataRepository.create()
        postServices.login("admin@gmail.com", "admin123").enqueue(object : Callback<List<LoginResponse>> {

            override fun onResponse(call: Call<List<LoginResponse>>, response: Response<List<LoginResponse>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d("tag", "responsennya ${data?.size}")

                    data?.map {
                        val token = it.data.token
                        Log.d("tag", "token = $token")
                    }
                }
            }

            override fun onFailure(call: Call<List<LoginResponse>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }

    private fun jajalLogin() {
        // get post data
        val postServices = DataRepository.create()
        postServices.getPosts().enqueue(object : Callback<List<PostModel>> {

            override fun onResponse(call: Call<List<PostModel>>, response: Response<List<PostModel>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d("tag", "responsennya ${data?.size}")

                    data?.map {
                        Log.d("tag", "datanya ${it.room_name}")
                    }
                }
            }

            override fun onFailure(call: Call<List<PostModel>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }

}
