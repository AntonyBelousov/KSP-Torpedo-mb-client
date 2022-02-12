package com.example.android.belousovas.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.belousovas.data.Repository
import com.example.android.belousovas.domain.model.Tour
import java.util.ArrayList

class MainFragmentViewModel : ViewModel() {

    private val repository = Repository()

    private val tourList = MutableLiveData<ArrayList<Tour>>()

    init {
        //tourList.value = repository.getTourList()
        Log.e("TTT", "ViewModel created");
    }



}