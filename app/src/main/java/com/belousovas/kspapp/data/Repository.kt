package com.belousovas.kspapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belousovas.kspapp.domain.model.Tour
import com.belousovas.kspapp.ui.LoginFragmentViewModel
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Repository {

    private var sessionId: String =
        getSessionId() // ToDo: add saving session cookie to local storage


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


    private fun getSessionId(): String {
        RetrofitInstance.api.getMainPage().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                sessionId = response.headers().get("Set-Cookie").toString().split(";")[0]
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("TTT", "Request error - getSessionId() $javaClass")
            }

        })
        return sessionId
    }

    fun getTourList(): ArrayList<Tour> {
        var htmlString = ""
        RetrofitInstance.api.getMainPage().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                htmlString = response.body().toString()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("TTT", "Request error - getTourList() $javaClass")
            }

        })

        return convertTourList(htmlString)
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