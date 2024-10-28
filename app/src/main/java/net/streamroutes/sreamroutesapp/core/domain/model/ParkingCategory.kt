package net.streamroutes.sreamroutesapp.core.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

enum class CategoryVehicle {
    CAR, MOTO, BUS, BIKE
}

data class ParkingCategory(
    val label: String,
    val icon: ImageVector,
    val categoryVehicle: CategoryVehicle,
    val onSelectCategory: () -> Unit
)