package com.belousovas.kspapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.belousovas.kspapp.domain.model.Game
import com.belousovas.kspapp.domain.model.Tour
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Repository {

    init {
        getSessionCookie()
    }

    companion object {
        private var sessionId: String = "null"
        fun getSessionCookie(){
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
                tourList.value = getTourListFromResponse(response.body().toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("TTT", "Request error - getTourList() $javaClass")
            }
        })
    }

    fun getTourById(tourId: String, tourName: String, tour: MutableLiveData<Tour?>) {
        RetrofitInstance.api.getTourById(sessionId, tourId).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                tour.value = getTourFromResponse(tourId, tourName, response.body().toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("TTT", "Request error - getTourById() $javaClass")
            }
        })
    }

    private fun getTourFromResponse(tourId : String, tourName : String, response: String): Tour {
        val tour = Tour(tourId, tourName)
        val gameList = ArrayList<Game>()
        val doc: Document = Jsoup.parse(response)
        tour.tourDeadline = Regex("Ставки принимаются до: \\d[\\d: .]+\\d").find(doc.getElementsByClass("workarea")[0].text())?.value
            ?: ""
        val gamesTable = doc.getElementsByTag("table")[0]
        val games = gamesTable.getElementsByTag("tr")
        for (game in games) {
            if(game.getElementsByClass("bet_input").isEmpty()) continue
            val match = game.text()
            val betIndex = Regex("\\d+").find(game.getElementsByClass("bet_input")[0].attr("name"))!!.value
            gameList.add(Game(match,betIndex))
        }
        tour.gameList = gameList
        return tour
    }

    private fun getTourListFromResponse(response: String): ArrayList<Tour> {
        val tourList = ArrayList<Tour>()
        val doc: Document = Jsoup.parse(response)
        val toursBlock = doc.getElementById("table_closest")
        val tours = toursBlock?.getElementsByTag("a")
        if (tours != null) {
            for (tour in tours) {
                val tourId = tour.attr("href").split("=")[1]
                val tourName = tour.text()
                tourList.add(Tour(tourName, tourId))
            }
        }
        return tourList
    }


}