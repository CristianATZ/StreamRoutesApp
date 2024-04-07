package net.streamroutes.sreamroutesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FastViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(FastUiState())
    val uiState: StateFlow<FastUiState> = _uiState.asStateFlow()

    var currentLocation by mutableStateOf(LatLng())
        private set

    var selectedLocation by mutableStateOf(LatLng())
        private set

    init {
        _uiState.value = FastUiState(
            currentLocation = currentLocation,
            selectedLocation = selectedLocation
        )
    }

    fun updateCurrentLocation(_current: LatLng){
        currentLocation = _current
    }

    fun updateSelectedLocation(_selected: LatLng){
        selectedLocation = _selected
    }
}

data class FastUiState(
    val currentLocation: LatLng = LatLng(),
    val selectedLocation: LatLng = LatLng(),
    // val timeExpected: String,
    //
)