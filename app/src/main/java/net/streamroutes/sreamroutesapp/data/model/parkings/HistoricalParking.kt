package net.streamroutes.sreamroutesapp.data.model.parkings

data class HistoricalParking(
    val idUser: String = "",            // ID del usuario
    val idPlace: String = "",            // ID del lugar
    val typeVehicle: Int = 0,           // *Tipo de vehículo
    val entranceHour: String = "",      // Hora de entrada
    val entranceDate: String = "",      // Fecha de entrada
    val departureHour: String = "",     // Hora de salida
    val departureDate: String = "",     // Fecha de salida
    val amount: Double = 0.0,           // Monto total
    val feePerHour: Double = 0.0,       // Tarifa por hora
    val totalHours: Int = 0             // Total de horas
)

// --- NOTAS ---
// * Tipo de vehículo es un ID int. El enum se encuentra en
// ReservationParking.
// * Todos los datos registrados en esta tabla NO se deben
// modificar para mantener la integridad de los datos.