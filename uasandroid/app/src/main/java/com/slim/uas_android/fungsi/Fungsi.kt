package com.slim.uas_android.fungsi

import android.content.Context
import android.graphics.Color
import cn.pedant.SweetAlert.SweetAlertDialog
import com.slim.uas_android.storage.SharePrefManager

class Fungsi private constructor(private val mCtx: Context) {
    fun showLoading() {
        val pDialog =
            SweetAlertDialog(mCtx, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(true)
        pDialog.show()
    }
    fun hideLoading() {
        val pDialog =
            SweetAlertDialog(mCtx, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.hide()
    }

    companion object {
        private var mInstance: Fungsi? = null
        @Synchronized
        fun getInstance(mCtx: Context): Fungsi {
            if (mInstance == null) {
                mInstance = Fungsi(mCtx)
            }
            return mInstance as Fungsi
        }
    }
}