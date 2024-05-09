package com.example.traveler.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

open class MapViewModel : ViewModel() {
    private val startPosition = Point(59.9402, 30.315)
    private val zoomValue: Float = 16.5f
    val message: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    fun mapInitializing(context: Context){
        MapKitFactory.initialize(context)
    }
    fun moveToStartLocation(mapview: MapView){
        mapview.map.move(
            CameraPosition(startPosition, zoomValue, 0.0f, 0.0f)
        )
    }
    fun addPlacemarkToMap(address: String?){
        //address?.let {
           // it ->
            //val location = getLoca
            {

        //}
        }
    }

//lateinit var private
}