package net.streamroutes.sreamroutesapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de Room del usada para las rutas de transporte
 * Similar al modelo Route.kt
 */
@Entity(tableName = "routes")
data class RouteEntity(
    @PrimaryKey val idRoute: String = "",   // ID de la ruta
    val idStartPlace: String = "",          // ID punto A
    val idEndPlace: String = "",            // ID punto B
    val name: String = "",                  // Nombre de ruta
    val arriveTime: Int = 0,                // Tiempo esperado de llegada
    val noStops: Int = 0,                   // Número de paradas
    val time: Int = 0,                      // Duración de la ruta
)