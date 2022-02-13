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
import android.widget.TextView




class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var mainFragmentViewModel : MainFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainFragmentViewModel = ViewModelProvider(requireActivity())[MainFragmentViewModel::class.java]

        mainFragmentViewModel.tourList.observe(viewLifecycleOwner, {
            if(it.isNotEmpty()) createTourListView(it)
        })
    }


    private fun createTourListView(openTours: List<Tour>) {
        val parent = view!!.findViewById<LinearLayout>(R.id.ll_main_tour_list)

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
            btn.text = tour.tableName
            parent.addView(btn, parentParams)
//            btn.setOnClickListener {
//                val bundle = Bundle()
//                bundle.putString("tableLink", link)
//                findNavController().navigate(R.id.action_mainFragment_to_tableFragment, bundle)
//            }
        }
    }


}