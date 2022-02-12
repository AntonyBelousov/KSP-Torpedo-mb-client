package com.example.android.belousovas.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.belousovas.R
import com.example.android.belousovas.data.Repository

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var mainFragmentViewModel : MainFragmentViewModel
    private val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("TTT", "MainFragment created");

        mainFragmentViewModel = ViewModelProvider(requireActivity())[MainFragmentViewModel::class.java]

        var value = repository.getTourList()
        Log.e("TTT", value.toString());

    }

    fun getTourById() {

    }

    fun getTableById() {

    }

}