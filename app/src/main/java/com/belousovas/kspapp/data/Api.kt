package com.example.android.belousovas.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("./login.php")
    fun getLoginPage(): String

    @POST("./login.php")
    fun login(): String

    @GET("./")
    fun getMainPage(): Call<String>

    @GET("./bet.php")
    fun getTourById(): String

    @GET("./table.php")
    fun getTableById(): String
}