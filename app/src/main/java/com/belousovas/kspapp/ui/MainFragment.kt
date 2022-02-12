package com.belousovas.kspapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.belousovas.R
import com.belousovas.kspapp.data.Repository

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var mainFragmentViewModel : MainFragmentViewModel
    private val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainFragmentViewModel = ViewModelProvider(requireActivity())[MainFragmentViewModel::class.java]


    }

    fun getTourById() {

    }

    fun getTableById() {

    }

}