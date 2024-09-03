package com.example.traveler.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveler.data.room.storage.entity.User
import com.example.traveler.databinding.FragmentSettingsBinding
import com.example.traveler.presentation.adapters.ChatAdapter
import com.example.traveler.presentation.viewmodel.ChatViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.database
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Settings : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var adapter: ChatAdapter
    private val viewModel: ChatViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val database = Firebase.database("https://traveler-bbef7-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("message")
        val prefs = viewModel.getMainSharedPreferences(requireContext())
        val userName = prefs.getString("nameGlobal", "noName")
        binding.sendToChatButton.setOnClickListener {
            myRef.child(myRef.push().key ?: "emptyMessage").setValue(User(userName, binding.writeToChatEditText.text.toString(), viewModel.getCurrentTime()))
            binding.writeToChatEditText.text.clear()
        }
        initRcView()
        viewModel.addMessageToChat(myRef, adapter, binding.chatRecyclerView)
        autoFillTextReceivedFromAdapter()
    }
    private fun initRcView() = with(binding) {
        adapter = ChatAdapter(requireContext())
        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        chatRecyclerView.adapter = adapter
    }
    private fun autoFillTextReceivedFromAdapter(){
        val message = arguments?.getString("1")
        if (message!=null) {
            binding.writeToChatEditText.setText(message)
            binding.writeToChatEditText.requestFocus()
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.writeToChatEditText, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}