import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FastViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(FastUiState())
    val uiState: StateFlow<FastUiState> = _uiState.asStateFlow()

    fun updateCurrentLocation(_current: LatLng) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(currentLocation = _current)
        }
    }

    fun updateSelectedLocation(_selected: LatLng) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(selectedLocation = _selected)
        }
    }

    fun fetchLocations() {
        viewModelScope.launch {
            // Simula una llamada a una fuente de datos remota
            val newCurrentLocation = fetchCurrentLocationFromRemote()
            val newSelectedLocation = fetchSelectedLocationFromRemote()

            updateCurrentLocation(newCurrentLocation)
            updateSelectedLocation(newSelectedLocation)
        }
    }

    private suspend fun fetchCurrentLocationFromRemote(): LatLng {
        return withContext(Dispatchers.IO) {
            // Simula un retardo y devuelve una ubicación
            LatLng(40.7128, -74.0060) // Ejemplo: Nueva York
        }
    }

    private suspend fun fetchSelectedLocationFromRemote(): LatLng {
        return withContext(Dispatchers.IO) {
            // Simula un retardo y devuelve una ubicación
            LatLng(34.0522, -118.2437) // Ejemplo: Los Ángeles
        }
    }
}

data class FastUiState(
    val currentLocation: LatLng = LatLng(20.139539228288044, -101.15073143400946),
    val selectedLocation: LatLng = LatLng(0.0, 0.0)
)

class FastViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FastViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FastViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
