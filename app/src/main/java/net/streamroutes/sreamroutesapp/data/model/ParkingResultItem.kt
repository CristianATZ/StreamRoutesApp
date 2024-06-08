package net.streamroutes.sreamroutesapp.data.model

data class ParkingResultItem(
    val calification: Double,
    val currentVehicles: Int,
    val direccion: String,
    val id: String,
    val location: Location,
    val maxVehicles: Int,
    val name: String,
    val opinions: List<Opinion>,
    val postalCode: String,
    val price: Int,
    val timeAprox: String
)