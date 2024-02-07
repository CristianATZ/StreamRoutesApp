package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConfigurationViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(ConfigurationState())
    val uiState : StateFlow<ConfigurationState> = _uiState.asStateFlow()

    init {
        _uiState.value = ConfigurationState(
            city = "Moroleon",
            language = 0,
            theme = false,

            current = true,
            ads = true,
            payment = true,
            routes = true,

            mapType = MapType.ROADMAP,
            currentLocation = true,
            food = true,
            health = true,
            stations = true,
            stops = true,
            mapStyle = MapStyle.LIGHT,

            notificationType = Notification.SIEMPRE,
            alerts = true,
            map = true,
            suscription = true
        )
    }
}