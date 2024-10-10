package net.streamroutes.sreamroutesapp.data.model.ors

data class Segment(
    val distance: Double,
    val duration: Double,
    val steps: List<Step>
)