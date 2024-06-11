package net.streamroutes.sreamroutesapp.data.model

data class ParkingResultItem(
    val calification: Double,
    val currentVehicles: Int,
    val address: String,
    val id: String,
    val location: Location,
    val maxVehicles: Int,
    val name: String,
    val opinions: List<Opinion>,
    val zipcode: String,
    val price: Int,
    val approxTime: String,
    val neighborhood: String
)