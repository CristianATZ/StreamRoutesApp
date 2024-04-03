package net.streamroutes.sreamroutesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConfigurationViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(ConfigurationUiState())
    val uiState: StateFlow<ConfigurationUiState> = _uiState.asStateFlow()

    // Notification
    var notificationType by mutableStateOf(Notification.SIEMPRE)
        private set

    var alerts by mutableStateOf(true)
        private set

    var suscription by mutableStateOf(true)
        private set

    var map by mutableStateOf(true)
        private set


    // Map
    var stops by mutableStateOf(true)
        private set

    var stations by mutableStateOf(true)
        private set

    var currentLocation by mutableStateOf(true)
        private set

    var food by mutableStateOf(true)
        private set

    var health by mutableStateOf(true)
        private set

    var mapType by mutableStateOf(MapType.ROADMAP)
        private set

    var mapStyle by mutableStateOf(MapStyle.LIGHT)
        private set


    // Privacy
    var current by mutableStateOf(true)
        private set

    var ads by mutableStateOf(true)
        private set

    var routes by mutableStateOf(true)
        private set

    var paymet by mutableStateOf(true)
        private set

    init {
        _uiState.value = ConfigurationUiState(
            notificationType,
            alerts,
            suscription,
            map,
            stops,
            stations,
            currentLocation,
            food,
            health,
            mapType,
            mapStyle,
            current,
            ads,
            routes,
            paymet
        )
    }


    // Actualizar campos
    fun updateNotificationType(_notificationType: Notification){
        notificationType = _notificationType
    }

    fun updateAlerts(_alerts: Boolean){
        alerts = _alerts
    }

    fun updateSuscription(_suscription: Boolean){
        suscription = _suscription
    }

    fun updateMap(_map: Boolean){
        map = _map
    }

    fun updateStops(_stops: Boolean){
        stops = _stops
    }

    fun updateStations(_stations: Boolean){
        stations = _stations
    }

    fun updateCurrentLocation(_currentLocation: Boolean){
        currentLocation = _currentLocation
    }

    fun updateFood(_food: Boolean){
        food = _food
    }

    fun updateHealth(_health: Boolean){
        health = _health
    }

    fun updateMapType(_mapType: MapType){
        mapType = _mapType
    }

    fun updateMapStyle(_mapStyle: MapStyle){
        mapStyle = _mapStyle
    }

    fun updateCurrent(_current: Boolean){
        current = _current
    }

    fun updateAds(_ads: Boolean){
        ads = _ads
    }

    fun updateRoutes(_routes: Boolean){
        routes = _routes
    }

    fun updatePayment(_payment: Boolean){
        paymet = _payment
    }

}

enum class MapType {
    ROADMAP, SATELLITE, HYBRID, TERRAIN
}

enum class MapStyle {
    DARK, LIGHT, NEON
}

enum class Notification {
    SIEMPRE, NUNCA
}

data class ConfigurationUiState (
    // notification
    val notificationType: Notification = Notification.SIEMPRE,
    val alerts: Boolean = true,
    val suscription: Boolean = true,
    val map: Boolean = true,

    // map
    val stops: Boolean = true,
    val stations: Boolean = true,
    val currentLocation: Boolean = true,
    val food: Boolean = true,
    val health: Boolean = true,
    val mapType: MapType = MapType.ROADMAP,
    val mapStyle: MapStyle = MapStyle.LIGHT,

    // privacy
    val current: Boolean = true,
    val ads: Boolean = true,
    val routes: Boolean = true,
    val payment: Boolean = true
)