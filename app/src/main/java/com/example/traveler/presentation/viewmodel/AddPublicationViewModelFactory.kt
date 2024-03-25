package com.example.traveler.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddPublicationViewModelFactory(private val application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPublicationViewModel::class.java)) {
            return AddPublicationViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}