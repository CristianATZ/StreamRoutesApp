package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Modelo para los puntos turísticos
 */
data class TuristicPoint(
    val idPlace: String = "",
    val totalRoutes: Int = 0,
    val fee: Int = 0,
    val nextStop: Int = 0,
    val days: List<Day> = emptyList(),
    val description: String = ""
)
