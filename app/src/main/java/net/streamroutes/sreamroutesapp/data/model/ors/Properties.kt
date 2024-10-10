package net.streamroutes.sreamroutesapp.data.model.ors

data class Properties(
    val segments: List<Segment>,
    val summary: Summary,
    val way_points: List<Int>
)