package net.streamroutes.sreamroutesapp.data.model.general

data class Place(
    val name: String = "",          // Nombre
    val latitude: String = "",      // Latitud
    val longitude: String = "",     // Longitud
    val address: String = "",       // Dirección
    val state: String = "",         // Estado
    val type: Int = 0               // *Tipo de lugar
)

// --- NOTAS ----
// * type: 1 para lugares generales, 2 para estacionamientos
