package com.slim.uas_android.api

import com.slim.uas_android.model.ClassModel
import com.slim.uas_android.model.LoginResponse
import com.slim.uas_android.model.PinjamResponse
import retrofit2.Call
import retrofit2.http.*

interface PostServices {

    @GET("v1/kelas")
    fun getClasses(
        @Header("Authorization") token:String
    ): Call<List<ClassModel>>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<List<LoginResponse>>

    @FormUrlEncoded
    @POST("v1/pinjam")
    fun pinjam(
        @Field("mulai") mulai:String,
        @Field("selesai") selesai:String,
        @Field("keterangan") keterangan:String,
        @Field("status") status:String,
        @Field("user_id") user_id:Int,
        @Field("kelas_id") kelas_id:Int,
        @Header("Authorization") token:String
    ): Call<List<PinjamResponse>>

}