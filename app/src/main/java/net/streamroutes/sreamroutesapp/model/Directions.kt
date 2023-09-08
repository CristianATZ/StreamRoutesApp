package net.streamroutes.sreamroutesapp.model
data class Geometry(
    val coordinates: List<List<Double>>,
    val type: String
)
data class Features(
    val geometry: Geometry
)
data class Directions(
    val features : List< Features>
)