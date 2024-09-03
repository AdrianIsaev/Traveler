package com.example.traveler.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toolbar
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.traveler.R
import com.example.traveler.databinding.FragmentDefaultBinding
import com.example.traveler.presentation.viewmodel.DefaultViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Default : Fragment() {
    private lateinit var toolbar: Toolbar
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var binding: FragmentDefaultBinding
    private val viewModel : DefaultViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDefaultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*val windowManager = requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels
        val halfScreenHeight = screenHeight / 5
        val imageView = view.findViewById<AppCompatImageView>(R.id.imageFilterView)
        val imageView2 = view.findViewById<AppCompatImageView>(R.id.imageFilterView2)
        imageView.maxHeight = halfScreenHeight
        imageView2.maxHeight = halfScreenHeight*/

        lifecycleScope.launch {
            val publications = viewModel.getDataFromDatabase()
            withContext(Dispatchers.Main) {
                for (publication in publications) {
                    if (publication.id == "37f78eec-3901-482a-b41f-fe72fbb7f838"){
                        Glide.with(requireContext())
                            .load(publication.uri.toUri())
                            .into(binding.imageFilterView3)
                    }
                    if (publication.id == "11dface7-c62a-470a-a694-61b755f62625"){
                        Glide.with(requireContext())
                            .load(publication.uri.toUri())
                            .into(binding.imageFilterView)
                    }
                    if (publication.id == "2c9cbb58-3249-4aa2-9c7b-429a16dd3c3e"){
                        Glide.with(requireContext())
                            .load(publication.uri.toUri())
                            .into(binding.imageFilterView2)
                    }
                }
            }
        }
    }
}