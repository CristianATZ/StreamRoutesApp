package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Modelo usado para los lugares
 */
data class Place(
    val idPlace: String = "",
    val name: String = "",          // Nombre
    val latitude: String = "",      // Latitud
    val longitude: String = "",     // Longitud
    val state: String = "",         // Estado
    val street: String = "",        // Calle
    val suburb: String = "",        // Colonia
    val type: Int = 0,              // *Tipo de lugar
    val imageUrl: String = ""        // Url de la imagen
)

// --- NOTAS ----
// * type: 1 para lugares generales, 2 para estacionamientos
