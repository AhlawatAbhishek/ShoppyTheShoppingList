package com.mayurappstudios.shoppytheshoppinglist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationSelectionScreen(modifier: Modifier = Modifier,
    locationData: LocationData,
    onLocationSelected: (LocationData) -> Unit
) {
    val userLocation by remember {
        mutableStateOf(
            LatLng(
                locationData.latitude,
                locationData.longitude
            )
        )
    }
    var cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 10f)
    }
    Column(modifier = modifier.fillMaxSize()) {
         GoogleMap(modifier = Modifier.weight(1f).padding(top=16.dp)){

         }
        Button(onClick = {/**/}){
             Text("Select Location")
        }
    }
}