package com.example.traveler.data.core

import android.os.Bundle
import com.example.traveler.data.room.storage.entity.Publication

interface RosterAdapterNavigationInterface {
    fun onShareClick(roster: Publication, bundle: Bundle)
}