package com.mayurappstudios.shoppytheshoppinglist.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.mayurappstudios.shoppytheshoppinglist.model.LocationData

@Composable
fun LocationSelectionScreen(
    modifier: Modifier = Modifier,
    locationData: LocationData,
    onLocationSelected: (LocationData) -> Unit
) {
    var userLocation by remember {
        mutableStateOf(
            LatLng(
                locationData.latitude,
                locationData.longitude
            )
        )
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 10f)
    }
    Column(modifier = modifier.fillMaxSize()) {
        GoogleMap(
            onMapClick = {
                userLocation = it

            },
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(state = MarkerState(position = userLocation))
        }
        var newLocation: LocationData
        Button(onClick = {
            newLocation = LocationData(userLocation.latitude, userLocation.longitude)
            onLocationSelected(newLocation)
        }) {
            Text("Select Location")
        }

    }
}