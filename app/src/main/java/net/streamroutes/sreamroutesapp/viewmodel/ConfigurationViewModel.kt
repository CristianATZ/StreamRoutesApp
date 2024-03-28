package net.streamroutes.sreamroutesapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConfigurationViewModel(): ViewModel() {
    //private val _uiState = MutableStateFlow(ConfigurationUiState())
    //val uiState: StateFlow<ConfigurationUiState>
}

enum class MapType {
    ROADMAP, SATELLITE, HYBRID, TERRAIN
}

enum class MapStyle {
    DARK, LIGHT, NEON
}

enum class Notification {
    SIEMPRE, NUNCA, A_VECES
}

data class ConfigurationUiState (
    // conf
    val city: String = "",
    val language: Int,
    val theme: Boolean,

    // map
    val stops: Boolean,
    val stations: Boolean,
    val currentLocation: Boolean,
    val food: Boolean,
    val health: Boolean,
    val mapType: MapType,
    val mapStyle: MapStyle,

    // notification
    val notificationType: Notification,
    val alerts: Boolean,
    val suscription: Boolean,
    val map: Boolean,

    // privacy
    val current: Boolean,
    val ads: Boolean,
    val routes: Boolean,
    val payment: Boolean
)