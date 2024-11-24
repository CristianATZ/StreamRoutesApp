package net.streamroutes.sreamroutesapp.core.domain.model

// Modelo usado para unir ruta con lugares
data class RouteWithPlaces(
    val route: Route,
    val startPlace: Place,
    val endPlace: Place,
    val turisticPoint: List<Place>
)
