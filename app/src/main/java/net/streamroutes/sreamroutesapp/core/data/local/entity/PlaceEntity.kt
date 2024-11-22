package net.streamroutes.sreamroutesapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class PlaceEntity (
    @PrimaryKey val idPlace: String = "",
    val name: String = "",          // Nombre
    val latitude: String = "",      // Latitud
    val longitude: String = "",     // Longitud
    val state: String = "",         // Estado
    val street: String = "",        // Calle
    val suburb: String = "",        // Colonia
    val type: Int = 0 ,             // *Tipo de lugar
    val imageUrl: String = ""       // URL de la imagen
)