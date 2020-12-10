package com.slim.uas_android.api

import com.slim.uas_android.model.ClassModel
import com.slim.uas_android.model.LoginResponse
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

}