package net.streamroutes.sreamroutesapp.core.domain.model

import java.time.LocalDateTime

data class SavedRoute(
    val name: String,
    val start: String,
    val end: String,
    val officialSpotsCount: Int,
    val saveDate: LocalDateTime
)