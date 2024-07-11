package net.streamroutes.sreamroutesapp.data.model.orsModel

data class Properties(
    val segments: List<Segment>,
    val summary: Summary,
    val way_points: List<Int>
)