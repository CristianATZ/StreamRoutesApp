package net.streamroutes.sreamroutesapp.viewmodel

import android.media.Image
import androidx.lifecycle.ViewModel
import com.google.maps.model.LatLng

class RoutesViewModel() : ViewModel() {

}

data class Route(
    val name : String,
    val time : String,
    val stops : List<Pair<String,LatLng>>,
    val realTime : Boolean,
    val imgs : List<Image>
)

data class RouteState(
    val routes : List<Route>,
    val filter : String,
    val currentLocation : LatLng,
    // val selectedLocation : LatLng
)