package com.example.traveler.presentation.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveler.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.TextStyle
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.tasks.await
import java.io.IOException
import java.util.Locale

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
    suspend fun getAddressesFromDatabase(): MutableMap<String, String> {
        val databaseF = Firebase.firestore
        //val arrayOfAddresses = ArrayList<String>()

        val arrayOfAddressesAndTitles = mutableMapOf<String, String>()
        val querySnapshot = databaseF.collection("list_items").get().await()
        for (document in querySnapshot) {
            //arrayOfAddresses.add(document.getString("address").toString())
            //arrayOfTitles.add(document.getString("title").toString())
            arrayOfAddressesAndTitles.put(document.getString("address").toString(), document.getString("title").toString())
        }
        return arrayOfAddressesAndTitles
    }
    fun addPlacemarkOnMap(arrayOfAddressesAndTitles: MutableMap<String, String>, context: Context, map: Map){
        val geocoder = Geocoder(context, Locale.getDefault())
        val addressesDecoding = mutableMapOf<Address, String>()
        val arrayOfAddresses= arrayOfAddressesAndTitles
        val arrayOfTitles = arrayOfAddressesAndTitles.values
        if (arrayOfAddresses.isNotEmpty()) {
            for (address in arrayOfAddresses.keys) {
                try {
                    val results = geocoder.getFromLocationName(address, 1)
                    if (results!!.isNotEmpty()) {
                        addressesDecoding.put(results?.get(0)!!, arrayOfAddresses.get(address).toString()
                        )
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        if (addressesDecoding.keys.size > 0) {
            for (address in addressesDecoding) {
                val point = Point(address.key.latitude, address.key.longitude)
                val testPoint = Point(address.key.latitude - 0.0001, address.key.longitude - 0.0001)

                val placemark = map.mapObjects.addPlacemark(point)

                placemark.opacity = 0.5f
                val vectorDrawable = ContextCompat.getDrawable(context, R.drawable.map_source)
                vectorDrawable?.let { drawable ->
                    drawable.setTint(Color.RED)
                    val vectorBitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                    val canvas = Canvas(vectorBitmap)
                    drawable.setBounds(0, 0, canvas.width, canvas.height)
                    drawable.draw(canvas)
                    placemark.setIcon(ImageProvider.fromBitmap(vectorBitmap))
                }
                placemark.setText(address.value)
                placemark.useAnimation()
                placemark.isDraggable = true
                map.move(CameraPosition(point, 15.0f, 0.0f, 0.0f))
            }
        }
    }
}