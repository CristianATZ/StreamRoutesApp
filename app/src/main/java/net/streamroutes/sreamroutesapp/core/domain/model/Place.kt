package net.streamroutes.sreamroutesapp.core.domain.model

data class Place(
    val name: String = "",          // Nombre
    val latitude: String = "",      // Latitud
    val longitude: String = "",     // Longitud
    val state: String = "",         // Estado
    val street: String = "",        // Calle
    val suburb: String = "",        // Colonia
    val type: Int = 0               // *Tipo de lugar
)

// --- NOTAS ----
// * type: 1 para lugares generales, 2 para estacionamientos
