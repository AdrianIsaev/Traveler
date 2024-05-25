package com.example.traveler.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RosterAdapterViewModel: ViewModel() {
    val likeButtonFlag: MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }
}