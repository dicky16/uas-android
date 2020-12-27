package com.slim.uas_android.model

data class StatusPinjamModel(
    val id:Int,
    val room_name:String,
    val mulai_pinjam:String,
    val selesai_pinjam:String,
    val keterangan:String,
    val status:String,
    val name:String
)