package net.streamroutes.sreamroutesapp.data.model.parkings

data class ReservationParking(
    val idUser: String = "",                // ID de usuario
    val idParking: String = "",             // ID del estacionamiento
    val incomingAlertInMinutes: Int = 0,    // Tiempo programado para la alerta, en minutos
    val typeVehicle: Int = 0,               // *Tipo de Vehículo
    val amount: Double = 0.0,               // Monto total
    val date: String = "",                  // Fecha
    val hour: String = "",                  // Hora
    val reservationHours: Int = 0           // Horas reservadas
)

// --- NOTAS ----
// *Tipo de vehículo es un enum con las siguientes opciones
enum class VehicleType(val id: Int) {
    Carro(1),
    Moto(2),
    Bus(3),
    Bicicleta(4),
    Furgoneta(5)
}
