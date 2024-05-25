package com.example.traveler.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveler.data.room.storage.entity.Publication
import com.example.traveler.databinding.FragmentRosterBinding
import com.example.traveler.presentation.adapters.RosterAdapter
import com.example.traveler.presentation.viewmodel.RosterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Roster : Fragment() {
    private lateinit var binding: FragmentRosterBinding

    private val viewModel: RosterViewModel by viewModels()
    private val adapter: RosterAdapter = RosterAdapter(this)
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
    fun getNavControllerFromRosterFragment() : NavController{
        return findNavController()
    }
}