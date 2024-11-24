package net.streamroutes.sreamroutesapp.core.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Enumerado para las categorías de vehículos
 */
enum class CategoryVehicle {
    CAR, MOTO, BUS, BIKE
}

/**
 * Data class para las categorías de un estacionamiento
 */
data class ParkingCategory(
    val label: String,
    val icon: ImageVector,
    val categoryVehicle: CategoryVehicle,
    val onSelectCategory: () -> Unit
)