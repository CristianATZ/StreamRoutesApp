package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Enumerado de filtros de espacio
 */
enum class FilterSpace {
    ALL, FREE, BUSY
}

/**
 * Enumerado de filtros de costos
 */
enum class FilterCost {
    ALL, FREE, COST
}

/**
 * Data class de filtros de estaiconamiento
 */
data class FilterParking(
    val space: FilterSpace = FilterSpace.ALL,
    val cost: FilterCost = FilterCost.ALL
)