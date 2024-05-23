package com.example.traveler.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.traveler.R
import com.example.traveler.databinding.FragmentMapBinding
import com.example.traveler.presentation.viewmodel.MapViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Map : Fragment() {
    private lateinit var binding: FragmentMapBinding
    private lateinit var mapView: MapView
    private val viewModel: MapViewModel by viewModels()
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
        val map = mapView.map
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navA).visibility = View.VISIBLE
        val prefs = requireContext().getSharedPreferences("NAME", Context.MODE_PRIVATE).edit()
        viewModel.message.observe(activity as LifecycleOwner, {

        })
        buttonNavigate.setOnClickListener {
            prefs.putString("userName", "1").commit()
            findNavController().navigate(R.id.action_mapFragment_to_addPublicationFragment2)
        }
        lifecycleScope.launch {
            val addressesAndTitles = viewModel.getAddressesFromDatabase()
            withContext(Dispatchers.Main) {
                viewModel.addPlacemarkOnMap(addressesAndTitles, requireContext(), map)
            }
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