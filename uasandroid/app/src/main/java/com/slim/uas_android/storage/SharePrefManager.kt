package com.slim.uas_android.storage

import android.content.Context
import com.slim.uas_android.model.LoginResponse
import java.util.*

class SharePrefManager private constructor(private val mCtx: Context) {
    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_LOGIN, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }
    val getToken: String?
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_LOGIN, Context.MODE_PRIVATE)
            return sharedPreferences.getString("token", "null")
        }
    val getUserData: Array<String?>
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_LOGIN, Context.MODE_PRIVATE)
            val data = arrayOf(
                "${sharedPreferences.getInt("id", 0)}",
                sharedPreferences.getString("name", "null"),
                sharedPreferences.getString("email", "null"),
                sharedPreferences.getString("level", "null"),
                sharedPreferences.getString("token", "null")
            )
            return data
        }
//
//    val user: LoginResponse
//        get() {
//            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//            return LoginResponse(
//                sharedPreferences.getInt("id", -1),
//                sharedPreferences.getString("email", null),
//                sharedPreferences.getString("name", null),
//                sharedPreferences.getString("school", null)
//            )
//        }


    fun saveUser(user: List<LoginResponse>) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_LOGIN, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("id", user[0].data.id)
        editor.putString("email", user[0].data.email)
        editor.putString("name", user[0].data.name)
        editor.putString("level", user[0].data.level)
        editor.putString("token", user[0].data.token)

        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_LOGIN, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_LOGIN = "login"
        private var mInstance: SharePrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharePrefManager {
            if (mInstance == null) {
                mInstance = SharePrefManager(mCtx)
            }
            return mInstance as SharePrefManager
        }
    }
}