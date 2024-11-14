package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Modelos de datos de Open Route Service
 */

data class OrsGeometry (
    val coordinates: List<List<Double>>
)

data class OrsStep (
    val distance: Double,
    val duration: Double,
    val instruction: String,
    val name: String,
    val type: Int,
    val way_points: List<Int>
)

data class OrsSegment (
    val distance: Double,
    val duration: Double,
    val steps: List<OrsStep>
)

data class OrsSummary (
    val distance: Double,
    val duration: Double
)

data class OrsProperties (
    val segments: List<OrsSegment>,
    val summary: OrsSummary,
    val way_points: List<Int>
)

data class OrsFeature (
    val bbox: List<Double>,
    val geometry: OrsGeometry,
    val properties: OrsProperties,
    val type: String
)

data class OrsRouteResponse (
    val features: List<OrsFeature> = emptyList()
)
