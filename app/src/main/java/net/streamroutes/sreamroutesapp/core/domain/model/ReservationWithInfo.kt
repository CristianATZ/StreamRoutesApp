package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Modelo usado para vincular una reservación con su respectivo estacionamiento,
 * lugar y usuario
 */
data class ReservationWithInfo(
    val reservation: ReservationParking,
    val parking: Parking,
    val place: Place,
    val user: User
)