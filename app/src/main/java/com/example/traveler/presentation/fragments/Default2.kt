package com.example.traveler.presentation.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.traveler.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class Default2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_default2, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs =
            requireContext().getSharedPreferences("NAME", android.content.Context.MODE_PRIVATE)
                .edit()
        prefs.putString("userName", "Success").commit()
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
        val navController = navHostFragment.navController
        val navigationBar = view.findViewById<BottomNavigationView>(R.id.bottom_navA)
        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        val toolbar = view.findViewById<Toolbar>(R.id.toolbarJI)
        toolbar.inflateMenu(R.menu.toolbar_navigation)

        NavigationUI.setupWithNavController(
            toolbar,
            navController,
            appBarConfiguration
        )
        NavigationUI.setupWithNavController(
            navigationBar,
            navController
        )
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.tool_logout -> {
                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_default2_to_auth)
                }
                R.id.tool_share ->{

                }
            }
            false
        }
        //val prefs2 = requireContext().getSharedPreferences("NAME", android.content.Context.MODE_PRIVATE)
        //val name = prefs2.getString("nameGlobal", null)
        //Toast.makeText(requireContext(), name.toString(), Toast.LENGTH_LONG).show()
    }
}