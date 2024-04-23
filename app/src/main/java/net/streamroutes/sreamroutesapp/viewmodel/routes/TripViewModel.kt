package net.streamroutes.sreamroutesapp.viewmodel.routes

import androidx.lifecycle.ViewModel
import com.google.maps.model.LatLng

class TripViewModel() : ViewModel() {

}

data class TripState(
    val currentLocation : LatLng,
    val selectedLocations : List<LatLng>
)