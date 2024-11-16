package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Modelos de datos de Open Route Service - Mapas (Rutas de transporte público)
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


/**
 * Modelos de datos de Open Route Service - Planifica tu viaje
 */

data class OrsPlannerProperties (
    val name: String,
    val housenumber: String,
    val street: String,
    val country: String,
    val region: String,
    val locality: String,
    val label: String
)

data class OrsPlannerFeature (
    val properties: OrsPlannerProperties
)

data class OrsPlannerResponse (
    val features: List<OrsPlannerFeature> = emptyList()
)

data class OrsPlannerRequestBody(
    val coordinates: List<List<Double>>
)