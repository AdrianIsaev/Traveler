package com.example.traveler.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveler.R
import com.example.traveler.data.room.storage.entity.User
import com.example.traveler.databinding.FragmentSettingsBinding
import com.example.traveler.presentation.adapters.ChatAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class Settings : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    lateinit var adapter: ChatAdapter
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
        
        var prefs = requireContext().getSharedPreferences("NAME", android.content.Context.MODE_PRIVATE)
        val userName = prefs.getString("nameGlobal", "noName")?: "noName"
        binding.sendToChatButton.setOnClickListener {

            myRef.child(myRef.push().key?:"emptyMessage").setValue(User(userName, binding.writeToChatEditText.text.toString()))
        }
        onChangeListener(myRef)
        initRcView()
    }
    private fun initRcView() = with(binding){
        adapter = ChatAdapter()
        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        chatRecyclerView.adapter = adapter
    }

    private fun onChangeListener(dRef: DatabaseReference){
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<User>()
                for (s in snapshot.children){
                    val user = s.getValue(User::class.java)
                    if (user!=null) list.add(user)
                }
                adapter.submitList(list)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}