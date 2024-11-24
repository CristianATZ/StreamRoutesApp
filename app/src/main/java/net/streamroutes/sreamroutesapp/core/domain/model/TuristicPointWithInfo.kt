package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Modelo usado para vincular un punto turístico con un lugar
 */
data class TuristicPointWithInfo(
    val turisticPoint: TuristicPoint,
    val place: Place
)