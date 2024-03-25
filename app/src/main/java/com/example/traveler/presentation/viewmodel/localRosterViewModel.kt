package com.example.traveler.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.traveler.data.repository.LocalRosterRepo
import com.example.traveler.data.room.storage.entity.LocalRosterModel

class localRosterViewModel(application: Application): AndroidViewModel(application){
    private val localRosterRepo: LocalRosterRepo = LocalRosterRepo(application)

    fun insertRosterLine(localRosterModel: LocalRosterModel){
        localRosterRepo.insertRosterData(localRosterModel)
    }

    fun updateRosterLine(localRosterModel: LocalRosterModel){
        localRosterRepo.updateRosterData(localRosterModel)
    }

    fun deleteRosterLine(localRosterModel: LocalRosterModel){
        localRosterRepo.deleteRosterData(localRosterModel)
    }

    fun getAllRosterDataLive(): LiveData<List<LocalRosterModel>>{
        return localRosterRepo.getAllRosterDataLive()
    }

}