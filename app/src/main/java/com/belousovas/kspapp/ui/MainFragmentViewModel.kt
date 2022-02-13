package com.belousovas.kspapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belousovas.kspapp.data.Repository
import com.belousovas.kspapp.domain.model.Tour
import java.util.ArrayList

class MainFragmentViewModel : ViewModel() {

    private val repository = Repository()
    var tourList = MutableLiveData<List<Tour>>()

    init {
        getTourList()
    }

    private fun getTourList() {
        repository.getTourList(tourList)
    }

}