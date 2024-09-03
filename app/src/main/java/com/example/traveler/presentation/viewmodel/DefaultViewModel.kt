package com.example.traveler.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.traveler.data.room.storage.entity.Publication
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class DefaultViewModel: ViewModel() {
    suspend fun getDataFromDatabase() : ArrayList<Publication>{
        val db = Firebase.firestore
        val arrayOfPublications = arrayListOf<Publication>()
        val querySnapshot = db.collection("list_items").get().await()
        for (document in querySnapshot){
            val publication = document.toObject<Publication>()
            arrayOfPublications.add(publication)
        }
        return arrayOfPublications
    }
}