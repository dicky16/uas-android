package com.slim.uas_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.slim.uas_android.R
import com.slim.uas_android.adapter.KelasAdapter
import com.slim.uas_android.adapter.StatusPinjamAdapter
import com.slim.uas_android.api.DataRepository
import com.slim.uas_android.model.ClassModel
import com.slim.uas_android.model.ResponseObject
import com.slim.uas_android.model.StatusPinjamModel
import com.slim.uas_android.storage.SharePrefManager
import kotlinx.android.synthetic.main.activity_controll.*
import kotlinx.android.synthetic.main.activity_detail_pinjaman_saya.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatusPinjamanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pinjaman_saya)
        val data = SharePrefManager.getInstance(this).getUserData
        val from = intent.getStringExtra("from")
        val id = data[0]?.toInt()
        if(from.equals("admin")) {
            getPinjam()
        } else {
            if (id != null) {
                getPinjamById(id)
            }
        }

    }

    fun getPinjamById(id:Int)
    {
        val mToken = SharePrefManager.getInstance(this).getToken
        val listClass = ArrayList<StatusPinjamModel>()
        var token:String = "Bearer $mToken"
        val postServices = DataRepository.create()
        postServices.getPinjamById(token, id).enqueue(object : Callback<List<StatusPinjamModel>> {

            override fun onResponse(call: Call<List<StatusPinjamModel>>, response: Response<List<StatusPinjamModel>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.map {
                        val list = StatusPinjamModel(
                            it.id,
                            it.room_name,
                            it.mulai_pinjam,
                            it.selesai_pinjam,
                            it.keterangan,
                            it.status,
                            it.name
                        )
                        listClass.add(list)
                        showRecyclerClass(listClass)

                    }
                }
            }

            override fun onFailure(call: Call<List<StatusPinjamModel>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }

    fun getPinjam()
    {
        val mToken = SharePrefManager.getInstance(this).getToken
        val listClass = ArrayList<StatusPinjamModel>()
        var token:String = "Bearer $mToken"
        val postServices = DataRepository.create()
        postServices.getPinjam(token).enqueue(object : Callback<List<StatusPinjamModel>> {

            override fun onResponse(call: Call<List<StatusPinjamModel>>, response: Response<List<StatusPinjamModel>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.map {
                        val list = StatusPinjamModel(
                            it.id,
                            it.room_name,
                            it.mulai_pinjam,
                            it.selesai_pinjam,
                            it.keterangan,
                            it.status,
                            it.name
                        )
                        listClass.add(list)
                        showRecyclerClass(listClass)

                    }
                }
            }

            override fun onFailure(call: Call<List<StatusPinjamModel>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }

    private fun showRecyclerClass(list: ArrayList<StatusPinjamModel>) {
        rv_pinjaman_saya.layoutManager = LinearLayoutManager(this)
        val classAdapter = StatusPinjamAdapter(list)
        classAdapter.notifyDataSetChanged()
        rv_pinjaman_saya.adapter = classAdapter
        classAdapter.setOnItemClickCallBack(object : StatusPinjamAdapter.OnItemClickCallBack{
            override fun onItemClickedTerima(data: StatusPinjamModel) {
//                Toast.makeText(this@StatusPinjamanActivity, "ok berima ${data.id}", Toast.LENGTH_SHORT).show()
                terimaPinjam(data.id)
            }

            override fun onItemClickedTolak(data: StatusPinjamModel) {
//                detailClassControlling(data)
//                Toast.makeText(this@StatusPinjamanActivity, "ok tolak ${data.id}", Toast.LENGTH_SHORT).show()
                tolakPinjam(data.id)
            }
        })
    }

    fun terimaPinjam(id:Int)
    {
        val mToken = SharePrefManager.getInstance(this).getToken
        val listClass = ArrayList<StatusPinjamModel>()
        var token:String = "Bearer $mToken"
        val postServices = DataRepository.create()
        postServices.terimaPinjam(token, id).enqueue(object : Callback<List<ResponseObject>> {

            override fun onResponse(call: Call<List<ResponseObject>>, response: Response<List<ResponseObject>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.map {
                        if(it.success == true) {
                            getPinjam()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<ResponseObject>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }

    fun tolakPinjam(id:Int)
    {
        val mToken = SharePrefManager.getInstance(this).getToken
        val listClass = ArrayList<StatusPinjamModel>()
        var token:String = "Bearer $mToken"
        val postServices = DataRepository.create()
        postServices.tolakPinjam(token, id).enqueue(object : Callback<List<ResponseObject>> {

            override fun onResponse(call: Call<List<ResponseObject>>, response: Response<List<ResponseObject>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.map {
                        if(it.success == true) {
                            getPinjam()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<ResponseObject>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }

}