package net.streamroutes.sreamroutesapp.core.domain.model

enum class FilterSpace {
    ALL, FREE, BUSY
}

enum class FilterCost {
    ALL, FREE, COST
}

data class FilterParking(
    val space: FilterSpace = FilterSpace.ALL,
    val cost: FilterCost = FilterCost.ALL
)