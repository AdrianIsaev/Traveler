package com.example.traveler.presentation.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.traveler.R
import com.example.traveler.databinding.FragmentMapBinding
import com.example.traveler.presentation.viewmodel.MapViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import kotlin.collections.Map

class Map : Fragment() {
    private lateinit var binding: FragmentMapBinding
    private lateinit var mapView: MapView
    private val viewModel: MapViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.mapInitializing(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        mapView = binding.mapview
        viewModel.moveToStartLocation(mapView)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttonNavigate = binding.createButton
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navA).visibility = View.VISIBLE
        var prefs = requireContext().getSharedPreferences("NAME", Context.MODE_PRIVATE).edit()
        viewModel.message.observe(activity as LifecycleOwner, {

        })
        buttonNavigate.setOnClickListener {
            prefs.putString("userName", "1").commit()
            findNavController().navigate(R.id.action_mapFragment_to_addPublicationFragment2)
        }


        val dataBase = FirebaseFirestore.getInstance()
        dataBase.collection("list_items")
            .get()
            .addOnSuccessListener {
                for (dataKit in it) {
                    dataKit.getString("address")

                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    "Не удалось получить доступ к базе данных ",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }
}