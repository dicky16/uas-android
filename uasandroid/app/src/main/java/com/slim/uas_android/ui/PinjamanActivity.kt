package com.slim.uas_android.ui

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.slim.uas_android.R
import com.slim.uas_android.adapter.KelasAdapter
import com.slim.uas_android.adapter.KelasAdapterPinjam
import com.slim.uas_android.api.DataRepository
import com.slim.uas_android.model.ClassModel
import com.slim.uas_android.storage.SharePrefManager
import kotlinx.android.synthetic.main.activity_pinjaman.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PinjamanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pinjaman)
        getKelas()
        val data = SharePrefManager.getInstance(this).getUserData
        if(data[3].toString().equals("admin")) {
            btn_request_pinjaman.visibility = View.VISIBLE
        }
        btn_pinjaman_saya.setOnClickListener {
            val intent = Intent(applicationContext, StatusPinjamanActivity::class.java)
            intent.putExtra("from", "user")
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        btn_request_pinjaman.setOnClickListener {
            val intent = Intent(applicationContext, StatusPinjamanActivity::class.java)
            intent.putExtra("from", "admin")
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
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
                }
            }

            override fun onFailure(call: Call<List<ClassModel>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }

    private fun showRecyclerClass(list: ArrayList<ClassModel>) {
        rv_kelas_pinjam.layoutManager = GridLayoutManager(this, 2)
        val classAdapter = KelasAdapterPinjam(list)
        classAdapter.notifyDataSetChanged()
        rv_kelas_pinjam.adapter = classAdapter
        classAdapter.setOnItemClickCallBack(object : KelasAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: ClassModel) {
                detailPinjam(data)
            }
        })
    }
    fun detailPinjam(classModel: ClassModel) {
        val intent = Intent(applicationContext, DetailPinjamanActivity::class.java)
        intent.putExtra("id", classModel.id)
        intent.putExtra("kelas", classModel.room_name)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}