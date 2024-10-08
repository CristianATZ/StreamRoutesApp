package net.streamroutes.sreamroutesapp.core.domain.model

data class RouteInformation(
    val name: String,
    val countTurism: Int,
    val hourAprox: Int,
    val minutesAprox: Int,
    val officialStops: Int,
    val start: String,
    val end: String
)
