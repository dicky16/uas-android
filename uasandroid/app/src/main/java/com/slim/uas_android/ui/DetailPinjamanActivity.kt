package com.slim.uas_android.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.slim.uas_android.R
import com.slim.uas_android.api.DataRepository
import com.slim.uas_android.model.ResponseObject
import com.slim.uas_android.storage.SharePrefManager
import kotlinx.android.synthetic.main.activity_detail_pinjaman.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class DetailPinjamanActivity : AppCompatActivity() {
    var mulai: String = ""
    var selesai: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pinjaman)
        val data = SharePrefManager.getInstance(this).getUserData
        //set nama
        edt_nama.setText("" + data[1])
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        setTanggal(year, month, day)
        //get text data
        val id = data[0]?.toInt()
        val kelasId = intent.getIntExtra("id", 0)
        val keterangan = edt_keterangan.text
        btn_submit_pinjam.setOnClickListener {
//            Toast.makeText(this, " mulai $keterangan", Toast.LENGTH_SHORT).show()
            if (id != null) {
                submitPinjam(mulai, selesai, keterangan.toString(),"pending", id, kelasId)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    fun setTanggal(year:Int, month:Int, day:Int) {
        edt_mulai.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                var tgl = "$dayOfMonth-$month-$year"
                edt_mulai.text = tgl.toEditable()
                mulai = tgl
            }, year, month, day)
            dpd.show()
        }
        edt_selesai.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                var tgl = "$dayOfMonth-$month-$year"
                edt_selesai.text = tgl.toEditable()
                selesai = tgl
            }, year, month, day)
            dpd.show()
        }
    }

    fun submitPinjam(mulai:String, selesai:String, keterangan:String, status:String, user_id:Int, kelas_id:Int) {
        //show loading
        val pDialog =
            SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(true)
        pDialog.show()

        val mToken = SharePrefManager.getInstance(this).getToken
        var token:String = "Bearer $mToken"
        val postServices = DataRepository.create()
        if (mToken != null) {
            postServices.pinjam(mulai, selesai, keterangan, status, user_id, kelas_id, token).enqueue(object : Callback<List<ResponseObject>> {

                override fun onResponse(call: Call<List<ResponseObject>>, responseObject: Response<List<ResponseObject>>) {
                    if (responseObject.isSuccessful) {
                        val data = responseObject.body()
                        data?.map {
                            val status = it.success
                            if(status) {
                                pDialog.hide()
                                SweetAlertDialog(this@DetailPinjamanActivity, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Berhasil!")
                                .setContentText("Berhasil mengajukan pinjaman kelas!")
                                .show()
                                edt_nama.setText("")
                                edt_mulai.setText("")
                                edt_selesai.setText("")
                                edt_keterangan.setText("")
                            } else {
                                if(it.message.equals("Token is Expired") || it.message.equals("Token is Invalid")) {
                                    pDialog.hide()
                                    SharePrefManager.getInstance(applicationContext).clear()
                                    val intent = Intent(applicationContext, LoginActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                                    startActivity(intent)
                                } else {
                                    pDialog.hide()
                                    SweetAlertDialog(this@DetailPinjamanActivity, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Gagal!")
                                        .setContentText("Gagal mengajukan pinjaman kelas!")
                                        .show()
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<com.slim.uas_android.model.ResponseObject>>, error: Throwable) {
                    Log.e("tag", "errornya ${error.message}")
                }
            })
        }
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)


}