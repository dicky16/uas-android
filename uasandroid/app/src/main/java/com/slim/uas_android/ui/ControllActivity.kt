package com.slim.uas_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.slim.uas_android.R
import com.slim.uas_android.adapter.KelasAdapter
import com.slim.uas_android.api.DataRepository
import com.slim.uas_android.model.ClassModel
import com.slim.uas_android.storage.SharePrefManager
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
        val mToken = SharePrefManager.getInstance(this).getToken
        val listClass = ArrayList<ClassModel>()
        var token:String = "Bearer $mToken"
        val postServices = DataRepository.create()
        postServices.getClasses(token).enqueue(object : Callback<List<ClassModel>> {

            override fun onResponse(call: Call<List<ClassModel>>, response: Response<List<ClassModel>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.map {
                        val list = ClassModel(
                            it.id,
                            it.room_name
                        )
                        listClass.add(list)
                        showRecyclerClass(listClass)

                    }
                    Log.e("tag", "data = ${listClass.get(0).room_name}")
                }
            }

            override fun onFailure(call: Call<List<ClassModel>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }

    private fun showRecyclerClass(list: ArrayList<ClassModel>) {
        rv_class.layoutManager = GridLayoutManager(this, 2)
        val classAdapter = KelasAdapter(list)
        classAdapter.notifyDataSetChanged()
        rv_class.adapter = classAdapter
//        kelasAdapter = KelasAdapter(arrayListOf(), object : KelasAdapter.OnAdapterListener {
//            override fun onClick(result: ClassModel) {
//                startActivity(
//                    Intent(this@ControllActivity, DetailClassActivity::class.java)
//                        .putExtra("intent_title", result.room_name)
////                        .putExtra("intent_image", result.image)
//                )
//            }
//        })
//        rv_class.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = kelasAdapter
//        }
    }
}