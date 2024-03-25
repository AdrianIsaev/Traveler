package com.example.traveler.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.traveler.data.repository.LocalRosterRepo
import com.example.traveler.data.room.storage.entity.LocalRosterModel
import com.example.traveler.data.room.storage.root.AppDatabase

class AddPublicationViewModel(application: Application) : AndroidViewModel(application) {
    private val appRepo: LocalRosterRepo = LocalRosterRepo(application)

    fun insertRosterLine(localRosterLine: LocalRosterModel){
        appRepo.insertRosterData(localRosterLine)
    }


}