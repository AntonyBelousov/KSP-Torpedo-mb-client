package com.belousovas.kspapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belousovas.kspapp.data.Repository
import com.belousovas.kspapp.domain.model.Tour

class TourViewModel() : ViewModel() {

    private val repository = Repository()
    var tour = MutableLiveData<Tour?>()
    private var tourId = ""
    private var tourName = ""

    fun setTourDetails(id: String, name: String) {
        tour.value = null
        tourId = id
        tourName = name
        repository.getTourById(tourId, tourName, tour)
    }


}