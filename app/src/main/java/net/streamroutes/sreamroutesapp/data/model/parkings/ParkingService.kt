package net.streamroutes.sreamroutesapp.data.model.parkings

data class Service(
    val description: String = ""    // Descripción del servicio
)

data class ParkingService(
    val idParking: String = "",     // ID del Estacionamiento
    val idService: String = ""      // ID del Servicio
)