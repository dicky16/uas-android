package com.slim.uas_android.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataRepository {

    fun create(): PostServices {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.43.214/web-uas-android/api/")
            .build()
        return retrofit.create(PostServices::class.java)
    }
}