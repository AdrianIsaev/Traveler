package com.example.traveler.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveler.databinding.FragmentRosterBinding
import com.example.traveler.presentation.adapters.ProjectAdapter
import com.google.common.io.LineReader
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore

class Roster : Fragment() {
    private lateinit var binding: FragmentRosterBinding
    private val adapter = ProjectAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRosterBinding.inflate(inflater, container, false)
        binding.listView.adapter = adapter

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.filterButton.setOnClickListener {
            init()
            Toast.makeText(requireContext(), "111", Toast.LENGTH_SHORT).show()
        }


    }
    private fun init(){
        binding.apply {
            listView.layoutManager = LinearLayoutManager(requireContext())
            //adapter.addRosterLine((Roster("ew", "fsdf", "gsdgsg", 43, 23)))
        }
    }
}