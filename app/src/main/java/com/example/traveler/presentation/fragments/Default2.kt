package com.example.traveler.presentation.fragments
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.traveler.R
import com.example.traveler.databinding.FragmentDefault2Binding
import com.example.traveler.databinding.FragmentDefaultBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class Default2 : Fragment() {
    private lateinit var binding: FragmentDefault2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDefault2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title2.gravity = Gravity.START
        //val prefs2 = requireContext().getSharedPreferences("NAME", android.content.Context.MODE_PRIVATE)
        //val name = prefs2.getString("nameGlobal", null)
        //Toast.makeText(requireContext(), name.toString(), Toast.LENGTH_LONG).show()
    }
}