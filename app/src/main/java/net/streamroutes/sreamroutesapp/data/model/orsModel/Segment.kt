package net.streamroutes.sreamroutesapp.data.model.orsModel

data class Segment(
    val distance: Double,
    val duration: Double,
    val steps: List<Step>
)