package com.belousovas.kspapp.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.belousovas.R
import com.belousovas.kspapp.domain.model.Tour
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController


class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var mainViewModel: MainViewModel

    override fun onStart() {
        super.onStart()

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        mainViewModel.tourList.observe(this, {
            if (it.isNotEmpty()) createTourListView(it)
        })
    }

    private fun createTourListView(openTours: List<Tour>) {
        val parent = view!!.findViewById<LinearLayout>(R.id.ll_main_tour_list)

        parent.removeAllViews()

        val parentParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        parentParams.setMargins(16, 16, 16, 16)

        for (tour in openTours) {
            val btn = Button(context)
            btn.gravity = 1
            btn.setTextColor(Color.parseColor("#000000"))
            btn.setBackgroundColor(Color.parseColor("#B388FF"))
            btn.text = tour.tourName
            parent.addView(btn, parentParams)
            btn.setOnClickListener {
                val bundle = bundleOf("tourId" to tour.tourId, "tourName" to tour.tourName)
                findNavController().navigate(R.id.action_mainFragment_to_tourFragment, bundle)
            }
        }
    }


}