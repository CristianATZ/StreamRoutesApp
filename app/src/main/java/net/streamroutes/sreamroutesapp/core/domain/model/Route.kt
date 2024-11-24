package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Modelo de rutas
 */
data class Route(
    val idRoute: String = "",       // ID de la ruta
    val idStartPlace: String = "",  // ID punto A
    val idEndPlace: String = "",    // ID punto B
    val name: String = "",          // Nombre de ruta
    val arriveTime: Int = 0,        // Tiempo esperado de llegada
    val noStops: Int = 0,           // Número de paradas
    val time: Int = 0,              // Duración de la ruta
    val turisticPoints: List<String> = emptyList(),
    val imageUrl: String = ""
)