package net.streamroutes.sreamroutesapp.data.model.routeModel

data class Place(
    val place: String = "",
    val latitude: String = "",
    val longitude: String = ""
)

data class Route(
    val name: String = "",
    val noStops: Int = 0,
    val arriveTime: Int = 0,
    val start: Place = Place(),
    val end: Place = Place(),
    val time: Int = 0
)