package com.example.traveler.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveler.data.room.storage.entity.Publication
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class RosterViewModel: ViewModel() {
    suspend fun getDataFromDatabase() : MutableMap<String, Publication>{
        val db = Firebase.firestore
        val arrayOfPublications = mutableMapOf<String, Publication>()
        val querySnapshot = db.collection("list_items").get().await()
        for (document in querySnapshot){
            val publication = document.toObject<Publication>()
            val pathToDocument = document.reference.path
            arrayOfPublications.put(pathToDocument, publication)
        }
        return arrayOfPublications
    }
    //var likeButtonFlag: MutableLiveData<Int> = MutableLiveData()
}