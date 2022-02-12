package com.belousovas.kspapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belousovas.kspapp.data.Repository
import com.belousovas.kspapp.domain.model.Tour
import java.util.ArrayList

class MainFragmentViewModel : ViewModel() {

    private val repository = Repository()

    private val tourList = MutableLiveData<ArrayList<Tour>>()

    init {
        //tourList.value = repository.getTourList()
    }



}