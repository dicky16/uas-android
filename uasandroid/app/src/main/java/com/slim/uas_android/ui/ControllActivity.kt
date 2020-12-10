package com.slim.uas_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.slim.uas_android.R
import com.slim.uas_android.adapter.ClassAdapter
import com.slim.uas_android.api.DataRepository
import com.slim.uas_android.model.ClassModel
import kotlinx.android.synthetic.main.activity_controll.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ControllActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controll)
        rv_class.setHasFixedSize(true)
        getKelas()
    }

    private fun getKelas() {
        val listClass = ArrayList<ClassModel>()
        var token:String = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xOTIuMTY4LjQzLjIxNFwvd2ViLXVhcy1hbmRyb2lkXC9hcGlcL2xvZ2luIiwiaWF0IjoxNjA3NTc4NDAzLCJleHAiOjE2MDc1ODIwMDMsIm5iZiI6MTYwNzU3ODQwMywianRpIjoicXd2aVdVWjRDc2NTeVRuViIsInN1YiI6MSwicHJ2IjoiODdlMGFmMWVmOWZkMTU4MTJmZGVjOTcxNTNhMTRlMGIwNDc1NDZhYSJ9.9zUf4CrUqxi6skQan0vzh72D__n4BUW-XuXDg7WX1Ac"
        val postServices = DataRepository.create()
        postServices.getClasses(token).enqueue(object : Callback<List<ClassModel>> {

            override fun onResponse(call: Call<List<ClassModel>>, response: Response<List<ClassModel>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.map {
//                        Log.e("tag", "data = ${it.room_name}")
                        val list = ClassModel(
                            it.id,
                            it.room_name
                        )
                        listClass.add(list)
                        showRecyclerClass(listClass)
                    }
                    Log.e("tag", "data = ${listClass.get(1).room_name}")
                }
            }

            override fun onFailure(call: Call<List<ClassModel>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }

    private fun showRecyclerClass(list: ArrayList<ClassModel>) {
        rv_class.layoutManager = LinearLayoutManager(this)
        val foodAdapter = ClassAdapter(list)
        foodAdapter.notifyDataSetChanged()
        rv_class.adapter = foodAdapter
    }
}