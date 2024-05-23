package com.example.traveler.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.data.room.storage.entity.User
import com.example.traveler.presentation.adapters.ChatAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class ChatViewModel : ViewModel() {

    fun addMessageToChat(dRef: DatabaseReference, adapter: ListAdapter<User, ChatAdapter.ItemHolder>, chatRecyclerView: RecyclerView) {
        dRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<User>()
                for (s in snapshot.children) {
                    val user = s.getValue(User::class.java)
                    if (user != null) list.add(user)
                }
                adapter.submitList(list)
                chatRecyclerView.smoothScrollToPosition(adapter.itemCount)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


}