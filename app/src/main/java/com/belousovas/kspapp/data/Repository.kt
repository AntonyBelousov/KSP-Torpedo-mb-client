package com.belousovas.kspapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.belousovas.kspapp.domain.model.Tour
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Repository {

    init {
        setSessionCookie()
    }

    companion object {
        private var sessionId: String = "null"
        fun setSessionCookie(){
            // ToDo: add saving session cookie to local storage
            if (sessionId == "null") {
                RetrofitInstance.api.getCookie().enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        sessionId = response.headers().get("Set-Cookie").toString().split(";")[0]
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.e("TTT", "Request error - getSessionId() $javaClass")
                    }

                })
            }
        }
    }


    fun login(user: String, password: String, loginStatus: MutableLiveData<Boolean>) {
        RetrofitInstance.api.login(cookie = sessionId, userName = user, userPassword = password)
            .enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    loginStatus.value = response.code().toString() == "200"
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    loginStatus.value = false
                    Log.e("TTT", "Request error - login()")
                }
            })
    }


    fun getTourList(tourList: MutableLiveData<List<Tour>>) {
        RetrofitInstance.api.getMainPage(sessionId).enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                tourList.value = convertTourList(response.body().toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("TTT", "Request error - getTourList() $javaClass")
            }
        })

    }

    private fun convertTourList(response: String): ArrayList<Tour> {
        val tourList = ArrayList<Tour>()
        val doc: Document = Jsoup.parse(response)
        val tourBlock = doc.getElementById("table_closest")
        val tours = tourBlock?.getElementsByTag("a")
        if (tours != null) {
            for (tour in tours) {
                val tourLink = tour.attr("href")
                val tourName = tour.text()
                tourList.add(Tour(tourName, tourLink))
            }
        }
        return tourList
    }


}