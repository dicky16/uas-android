package com.slim.uas_android.data

import com.slim.uas_android.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface PostServices {

    @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xOTIuMTY4LjQzLjIxNFwvd2ViLXVhcy1hbmRyb2lkXC9hcGlcL2xvZ2luIiwiaWF0IjoxNjA3NDg2NTcyLCJleHAiOjE2MDc0OTAxNzIsIm5iZiI6MTYwNzQ4NjU3MiwianRpIjoiNTA0Y1JwS21MVVRtRHB6TCIsInN1YiI6MSwicHJ2IjoiODdlMGFmMWVmOWZkMTU4MTJmZGVjOTcxNTNhMTRlMGIwNDc1NDZhYSJ9.ygSb255g2_sKPq-tD91XRzsnYLyDShJ4Ztz5UIXDRAQ")
    @GET("v1/kelas")
    fun getPosts(): Call<List<PostModel>>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<List<LoginResponse>>

}