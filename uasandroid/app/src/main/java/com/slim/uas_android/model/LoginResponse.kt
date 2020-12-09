package com.slim.uas_android.model

data class LoginResponse(val success:Boolean,
                     val message:String,
                     val data:TokenResponse
)