package com.example.traveler.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traveler.R
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView
import kotlin.collections.Map

class Map : Fragment() {
    private lateinit var mapView : MapView
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_map, container, false)
        // Inflate the layout for this fragment
        mapView = rootView.findViewById(R.id.mapview)
        return rootView
    }
}