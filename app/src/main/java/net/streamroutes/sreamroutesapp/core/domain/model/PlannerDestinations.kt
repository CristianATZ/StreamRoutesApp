package net.streamroutes.sreamroutesapp.core.domain.model

import com.google.android.gms.maps.model.LatLng

/**
 * Modelo usado para guardar los lugares seleccionados en planifica tu viaje.
 * Cada destino guarda su dirección y coordenadas.
 */
data class Destinations(
    val address: String,
    val coords: LatLng
)