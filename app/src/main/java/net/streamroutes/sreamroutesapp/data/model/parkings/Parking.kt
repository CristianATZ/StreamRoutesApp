package net.streamroutes.sreamroutesapp.data.model.parkings

data class Parking(
    val idPlace: String = "",       // ID de lugar
    val feePerHour: Double = 0.0,   // Tarifa por hora
    val rating: Double = 0.0,       // Calificación del 0.0 - 5.0
    val maxCapacity: Int = 0,       // Máxima capacidad del estacionamiento
    val waitingMinutes: Int = 0,    // Tiempo aproximado de espera en minutos
    val openHour: String = "",      // Hora de apertura
    val closeHour: String = "",     // Hora de cierre
    val description: String = "",   // Descripción del estacionamiento
    val currentEntrances: Int = 0   // Cantidad actual de espacios ocupados
)