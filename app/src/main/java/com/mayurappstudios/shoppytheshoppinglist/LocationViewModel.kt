package com.mayurappstudios.shoppytheshoppinglist

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location
    private val _address = mutableStateOf(listOf<GeoCodingResult>())
    val address: State<List<GeoCodingResult>> = _address

    // Retrieve the API key from strings.xml
    private val apiKey: String = application.getString(R.string.google_maps_key)

    fun updateLocation(newLocation: LocationData) {
        _location.value = newLocation
    }

    fun fetchAddress(latlng: String) {
        try {
            viewModelScope.launch {
                val result = com.mayurappstudios.shoppytheshoppinglist.RetrofitClient.create()
                    .getAddressFromCoordinates(latlng, apiKey)
                _address.value = result.results
                Log.d("res1---------------------------------------------", "${result.results.size}  + ${apiKey}")
            }
        } catch (e: Exception) {
            Log.d("res1", "${e.cause} ${e.message}")
        }
    }
}