package com.belousovas.kspapp.data

import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("./login.php")
    fun getLoginPage(): Call<String>

    @FormUrlEncoded
    @POST("./login.php")
    fun login(
        @Header("Cookie") cookie : String,
        @Field("login") userName : String,
        @Field("password") userPassword : String,
        @Field("ok") ok : String = "Войти",
        @Field("checker") checker : String = "ok"
    ): Call<String>

    @GET("./")
    fun getMainPage(@Header("Cookie") cookie : String): Call<String>

    @GET("./")
    fun getCookie(): Call<String>

    @GET("./bet.php")
    fun getTourById(@Header("Cookie") cookie : String, @Query("tour_id") tourId : String): Call<String>

    @GET("./table.php")
    fun getTableById(): Call<String>
}