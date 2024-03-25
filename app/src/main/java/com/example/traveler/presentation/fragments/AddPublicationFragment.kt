package com.example.traveler.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.traveler.R
import com.example.traveler.data.repository.LocalRosterRepo
import com.example.traveler.data.room.storage.entity.LocalRosterModel
import com.example.traveler.presentation.viewmodel.AddPublicationViewModel
import com.example.traveler.presentation.viewmodel.AddPublicationViewModelFactory

class AddPublicationFragment : Fragment() {

    companion object {
        fun newInstance() = AddPublicationFragment()
    }

    private lateinit var localRosterLine: LocalRosterModel
    private lateinit var viewModel: AddPublicationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_publication2, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(AddPublicationViewModel::class.java)

            //viewModel = ViewModelProvider(this, AddPublicationViewModelFactory(requireActivity().application)).get(AddPublicationViewModel::class.java)

    }
}