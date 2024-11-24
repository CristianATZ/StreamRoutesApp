package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Modelo usado para vincular un historial de estacionamiento con su respectivo lugar y usuario
 */
data class HistoricalParkingWithInfo (
    val historicalParking: HistoricalParking,
    val place: Place,
    val user: User
)