package com.slim.uas_android.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.slim.uas_android.R
import com.slim.uas_android.adapter.InventoryAdapter
import com.slim.uas_android.adapter.KelasAdapter
import com.slim.uas_android.api.DataRepository
import com.slim.uas_android.model.ClassModel
import com.slim.uas_android.model.InventoryModel
import com.slim.uas_android.storage.SharePrefManager
import kotlinx.android.synthetic.main.activity_controll.*
import kotlinx.android.synthetic.main.activity_inventory.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)
        var nama:String? = intent.getStringExtra("kelas")
        tv_kelas_inventory.text = nama
        getInventory()
    }

    fun getInventory() {
        val mToken = SharePrefManager.getInstance(this).getToken
        val listInventoryModel = ArrayList<InventoryModel>()
        var token:String = "Bearer $mToken"
        val postServices = DataRepository.create()
        postServices.getInventory(token).enqueue(object : Callback<List<InventoryModel>> {

            override fun onResponse(call: Call<List<InventoryModel>>, response: Response<List<InventoryModel>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.map {
                        val list = InventoryModel(
                            it.nama_barang,
                            it.label,
                            it.tahun,
                            it.jumlah,
                            it.kondisi,
                            it.sumber_dana,
                            it.gambar,
                            it.keterangan
                        )
                        listInventoryModel.add(list)
                        tv_total_inventory.text = listInventoryModel.size.toString()
                        showRecyclerInventory(listInventoryModel)
                    }
                }
            }

            override fun onFailure(call: Call<List<InventoryModel>>, error: Throwable) {
                Log.e("tag", "errornya ${error.message}")
            }
        })
    }

    private fun showRecyclerInventory(list: ArrayList<InventoryModel>) {
        rv_inventory.layoutManager = LinearLayoutManager(this)
        val inventoryAdapter = InventoryAdapter(list)
        inventoryAdapter.notifyDataSetChanged()
        rv_inventory.adapter = inventoryAdapter
        inventoryAdapter.setOnItemClickCallBack(object : InventoryAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: InventoryModel) {
                detailInventory(data)
            }
        })
    }

    fun detailInventory(inventoryModel: InventoryModel) {
        val intent = Intent(applicationContext, DetailInventoryActivity::class.java)
        intent.putExtra("label", inventoryModel.label)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}