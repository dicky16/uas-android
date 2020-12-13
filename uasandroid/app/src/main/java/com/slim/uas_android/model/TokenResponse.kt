package com.slim.uas_android.model

data class TokenResponse(
    val id:Int,
    val name:String,
    val email:String,
    val level:String,
    val token:String
)