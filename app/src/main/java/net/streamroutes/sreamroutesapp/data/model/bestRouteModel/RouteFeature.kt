package net.streamroutes.sreamroutesapp.data.model.bestRouteModel

data class RouteFeature(
    val bbox: List<Double>,
    val type: String,
    val properties: Properties,
    val geometry: Geometry
)