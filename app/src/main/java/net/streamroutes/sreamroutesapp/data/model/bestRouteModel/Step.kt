package net.streamroutes.sreamroutesapp.data.model.bestRouteModel

data class Step(
    val distance: Double,
    val duration: Double,
    val instruction: String,
    val name: String,
    val type: Int,
    val way_points: List<Int>
)