package com.example.traveler.presentation.fragments

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveler.R
import com.example.traveler.data.room.storage.entity.User
import com.example.traveler.databinding.FragmentSettingsBinding
import com.example.traveler.presentation.adapters.ChatAdapter
import com.example.traveler.presentation.viewmodel.ChatViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Settings : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    lateinit var adapter: ChatAdapter

    private val viewModel: ChatViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val database = Firebase.database("https://traveler-bbef7-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("message")
        var prefs =
            requireContext().getSharedPreferences("NAME", android.content.Context.MODE_PRIVATE)
        val userName = prefs.getString("nameGlobal", "noName") ?: "noName"
        binding.sendToChatButton.setOnClickListener {
            val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            myRef.child(myRef.push().key ?: "emptyMessage")
                .setValue(User(userName, binding.writeToChatEditText.text.toString(), currentTime))
        }
        initRcView()
        viewModel.addMessageToChat(myRef, adapter, binding.chatRecyclerView)
    }

    private fun initRcView() = with(binding) {
        adapter = ChatAdapter()
        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        chatRecyclerView.adapter = adapter
    }
}