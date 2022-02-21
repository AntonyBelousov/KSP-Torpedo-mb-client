package com.belousovas.kspapp.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.belousovas.kspapp.domain.model.Tour
import com.example.android.belousovas.R

class TourFragment : Fragment(R.layout.tour_fragment) {

    private lateinit var tourViewModel: TourViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tourId = arguments?.get("tourId").toString()
        val tourName = arguments?.get("tourName").toString()
        tourViewModel = ViewModelProvider(requireActivity())[TourViewModel::class.java]
        tourViewModel.setTourDetails(tourId, tourName)

        tourViewModel.tour.observe(this, {
            if (it != null) {
                createTourView(it)
            }
        })
    }


    private fun createTourView(tour: Tour) {
        val parent = view!!.findViewById<LinearLayout>(R.id.ll_tour)
        val parentParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        parentParams.setMargins(8, 8, 8, 8)

        val textTourDeadLine = TextView(context)
        textTourDeadLine.text = tour.tourDeadline
        parent.addView(textTourDeadLine, parentParams)

        for (game in tour.gameList) {
            val txv = TextView(context)
            txv.gravity = 3
            txv.setTextColor(Color.parseColor("#000000"))
            txv.setBackgroundColor(Color.parseColor("#B388FF"))
            txv.text = game.match
            parent.addView(txv, parentParams)
        }
    }
}