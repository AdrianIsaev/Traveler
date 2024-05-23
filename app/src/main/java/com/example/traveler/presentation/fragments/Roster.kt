package com.example.traveler.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveler.data.room.storage.entity.Publication
import com.example.traveler.databinding.FragmentRosterBinding
import com.example.traveler.presentation.adapters.ProjectAdapter
import com.example.traveler.presentation.viewmodel.RosterViewModel
import com.google.common.io.LineReader
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Roster : Fragment() {
    private lateinit var binding: FragmentRosterBinding
    private val adapter = ProjectAdapter()
    private val viewModel: RosterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRosterBinding.inflate(inflater, container, false)
        binding.listView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        lifecycleScope.launch {
            val publications = viewModel.getDataFromDatabase()
            withContext(Dispatchers.Main) {
                for(publication in publications) {
                    addPublicationFromServer(publication.value, publication.key)
                }
            }
        }
    }
    private fun addPublicationFromServer(publication: Publication, key: String){
        val publicationCopy = publication.copy(id = key)
        adapter.addRosterLine(publicationCopy)
    }
    private fun init(){
        binding.apply {
            listView.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}