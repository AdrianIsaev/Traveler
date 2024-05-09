package com.example.traveler.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.traveler.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.core.Context


class Default2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_default2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var prefs = requireContext().getSharedPreferences("NAME", android.content.Context.MODE_PRIVATE).edit()
        prefs.putString("userName", "Success").commit()
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
        val navController = navHostFragment.navController
        val navigationBar = view.findViewById<BottomNavigationView>(R.id.bottom_navA)
        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        val toolbar = view.findViewById<Toolbar>(R.id.toolbarJI)

        NavigationUI.setupWithNavController(
            toolbar,
            navController,
            appBarConfiguration
        )
        NavigationUI.setupWithNavController(
            navigationBar,
            navController
        )
    }
}