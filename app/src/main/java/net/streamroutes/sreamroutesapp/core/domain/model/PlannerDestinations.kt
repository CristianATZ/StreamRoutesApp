package net.streamroutes.sreamroutesapp.core.domain.model

import com.google.android.gms.maps.model.LatLng

data class Destinations(
    val address: String,
    val coords: LatLng
)