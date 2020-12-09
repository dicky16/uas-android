package com.slim.uas_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.slim.uas_android.R
import org.json.JSONObject

class ControllActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controll)
        getKelas()
    }

    private fun getKelas() {
//        val client = AsyncHttpClient()
//        client.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjE6ODAwMFwvYXBpXC9sb2dpbiIsImlhdCI6MTYwNzQ3Njg3OCwiZXhwIjoxNjA3NDgwNDc4LCJuYmYiOjE2MDc0NzY4NzgsImp0aSI6Imt1cVVnOEhhdnJrd2MzUnciLCJzdWIiOjEsInBydiI6Ijg3ZTBhZjFlZjlmZDE1ODEyZmRlYzk3MTUzYTE0ZTBiMDQ3NTQ2YWEifQ.XdfKZO_0OM-Ch1X0ufjOZrddCYEWAA0I_yHuXs195r4")
//        val url = "http://192.168.43.214/web-uas-android/api/v1/kelas"
////        val listFood = ArrayList<FoodModel>()
//        client.get(url, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
////                progressBar.visibility = View.INVISIBLE
//                val result = String(responseBody)
//                try {
//                    val jsonObject = JSONObject(result)
//                    val jsonArray = jsonObject.getJSONArray("data")
//                    val jsonObj = jsonArray.getJSONObject(1)
//
//                    val length = jsonArray.length() - 1
//                    Log.d("data tes", jsonObj.getString("room_name"))
//
//                } catch (e: Exception) {
//                    Toast.makeText(this@ControllActivity, e.message, Toast.LENGTH_SHORT).show()
//                    Log.d("error", e.message)
//                    e.printStackTrace()
//                }
//            }
//
//            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
//
//            }
//        })
    }
}