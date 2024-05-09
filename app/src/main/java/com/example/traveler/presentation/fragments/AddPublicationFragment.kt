package com.example.traveler.presentation.fragments

import android.health.connect.datatypes.units.Length
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import com.example.traveler.R
import com.example.traveler.data.repository.LocalRosterRepo
import com.example.traveler.data.room.storage.entity.LocalRosterModel
import com.example.traveler.data.room.storage.entity.Publication
import com.example.traveler.databinding.FragmentAddPublication2Binding
import com.example.traveler.presentation.viewmodel.AddPublicationViewModel
import com.example.traveler.presentation.viewmodel.AddPublicationViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.database.core.Context
import com.google.firebase.firestore.FirebaseFirestore

class AddPublicationFragment : Fragment() {
    private lateinit var binding: FragmentAddPublication2Binding
    private lateinit var viewModel: AddPublicationViewModel
    private lateinit var author: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPublication2Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(AddPublicationViewModel::class.java)
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navA).visibility = View.GONE
        var prefs = requireContext().getSharedPreferences("NAME", android.content.Context.MODE_PRIVATE)
        author = prefs.getString("nameGlobal", "noName")?: "noName"

        binding.buttonSaveAdd.setOnClickListener {
            val imageUrl = "empty"
            val title = binding.editTextAdd.text.toString()
            val description = binding.editTextAdd2.text.toString()
            val address = binding.editTextAdd3.text.toString()
            val numberOfLikes = 0
            val numberOfComments = 0
            val publication = Publication(title, author, description, numberOfLikes, numberOfComments, address, imageUrl)
            val db = FirebaseFirestore.getInstance()
            db.collection("list_items")
                .add(publication)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "lolipop", LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "((((((())))", LENGTH_SHORT).show()
                }
        }
    }
}