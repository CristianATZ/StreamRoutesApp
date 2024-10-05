package net.streamroutes.sreamroutesapp.core.domain.model

import java.time.LocalDateTime
import java.time.LocalTime

data class History(
    val idReference: String,          // Identificador único del historial
    val totalTime: Double,           // Tiempo total en horas
    val totalPrice: Double,           // Precio total a pagar
    val parkingDate: LocalDateTime,  // Fecha y hora del aparcamiento
    val timeIn: LocalTime,        // Fecha y hora de entrada
    val timeOut: LocalTime,         // Fecha y hora de salida
    val isReserved: Boolean,
    val idParking: String,     // ID de referencia del estacionamiento
    val parkingName: String,
    val parkingPrice: Double,
    val parkingAddress: String
)