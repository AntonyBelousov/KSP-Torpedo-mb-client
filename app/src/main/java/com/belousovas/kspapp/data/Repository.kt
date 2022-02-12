package com.example.android.belousovas.data

import android.util.Log
import com.example.android.belousovas.domain.model.Tour
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Repository {


    fun login() : Boolean {
        RetrofitInstance.api.login()
    }

    fun getTourList() : ArrayList<Tour> {
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

    private fun convertTourList(response: String) : ArrayList<Tour> {
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