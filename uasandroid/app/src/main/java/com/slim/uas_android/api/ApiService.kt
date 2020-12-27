package com.slim.uas_android.api

import com.slim.uas_android.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

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
    ): Call<List<ResponseObject>>

    @GET("v1/inventory")
    fun getInventory(
        @Header("Authorization") token:String,
        @Query("id") id:Int
    ): Call<List<InventoryModel>>

    @GET("v1/pinjam")
    fun getPinjam(
        @Header("Authorization") token:String
    ): Call<List<StatusPinjamModel>>

    @GET("v1/pinjam/id")
    fun getPinjamById(
        @Header("Authorization") token:String,
        @Query("user_id") id:Int
    ): Call<List<StatusPinjamModel>>

    @FormUrlEncoded
    @POST("v1/pinjam/terima")
    fun terimaPinjam(
        @Header("Authorization") token:String,
        @Field("id") id:Int
    ): Call<List<ResponseObject>>

    @FormUrlEncoded
    @POST("v1/pinjam/tolak")
    fun tolakPinjam(
        @Header("Authorization") token:String,
        @Field("id") id:Int
    ): Call<List<ResponseObject>>

}