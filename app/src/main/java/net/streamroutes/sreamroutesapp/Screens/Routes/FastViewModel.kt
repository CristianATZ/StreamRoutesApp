package net.streamroutes.sreamroutesapp.Screens.Routes

import androidx.lifecycle.ViewModel
import com.google.maps.model.LatLng

class FastViewModel() : ViewModel() {

}

data class FastState(
    val currentLocation: LatLng,
    val selectedLocation: LatLng,
    // val timeExpected: String,
    //
)