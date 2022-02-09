package com.example.android.myapplication.data

import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("./login.php")
    fun getLoginPage(): String

    @POST("./login.php")
    fun login(): String

    @GET("./")
    fun getMainPage(): String

    @GET("./bet.php")
    fun getTourById(): String

    @GET("./table.php")
    fun getTableById(): String
}