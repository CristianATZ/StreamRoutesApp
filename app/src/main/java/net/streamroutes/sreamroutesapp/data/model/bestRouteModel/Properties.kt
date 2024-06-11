package net.streamroutes.sreamroutesapp.data.model.bestRouteModel

data class Properties(
    val segments: List<Segment>,
    val summary: Summary,
    val way_points: List<Int>
)