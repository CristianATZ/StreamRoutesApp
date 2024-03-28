package net.streamroutes.sreamroutesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.streamroutes.sreamroutesapp.ui.screens.RoutesNavigationOptions

class MainViewModel: ViewModel() {
    // Main UI State
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    var routeScreen by mutableStateOf(RoutesNavigationOptions.HOME_SCREEN)
        private set

    init {
        _uiState.value = MainUiState(
            routeScreen = routeScreen
        )
    }

    fun updateRouteScreen(_routeScreen: RoutesNavigationOptions){
        routeScreen = _routeScreen
    }
}

data class MainUiState(
    val routeScreen: RoutesNavigationOptions = RoutesNavigationOptions.HOME_SCREEN
)