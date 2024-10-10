package net.streamroutes.sreamroutesapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.streamroutes.sreamroutesapp.data.model.ors.Geometry
import net.streamroutes.sreamroutesapp.data.model.ors.Properties
import net.streamroutes.sreamroutesapp.data.repository.RemoteRepository

enum class OrsState {
    NONE, LOADING, SUCCESSFUL, FAILURE
}

class OrsViewModel(
    private val remoteRepository: RemoteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(OrsUiState())
    val uiState: StateFlow<OrsUiState> = _uiState.asStateFlow()

    // función para traer los datos de la API Open Routes Service
    // formato: Longitud,Latitud
    fun fetchRouteInfo(start: String, end: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(state = OrsState.LOADING, message = "Cargando mejor ruta...")
            try {
                Log.d("ROUTES", "INICIOS: $start, $end")
                val response = withContext(Dispatchers.IO) {
                    remoteRepository.getBestRoute(start, end)
                }

                if (response.isSuccessful) {
                    val ruta = response.body()
                    ruta?.let {
                        val properties = it.features.last().properties
                        _uiState.value = _uiState.value.copy(
                            geometry = it.features.last().geometry,
                            properties = properties,
                            message = "Información cargada con éxito.",
                            state = OrsState.SUCCESSFUL,
                            time = properties.segments.firstOrNull()?.duration?.let { duration ->
                                // Convertir segundos a formato hh:mm:ss
                                String.format("%02d:%02d:%02d", duration.toInt() / 3600, (duration.toInt() % 3600) / 60, duration.toInt() % 60)
                            },
                            distance = properties.segments.firstOrNull()?.distance?.let { distance ->
                                // Convertir metros a kilómetros con dos decimales
                                String.format("%.2f km", distance / 1000)
                            }
                        )
                    }
                    Log.d("ROUTES", "SI JALO/ ${uiState.value.geometry}")
                } else {
                    _uiState.value = _uiState.value.copy(state = OrsState.FAILURE, message = "Error en la solicitud: ${response.message()}")
                    Log.d("ROUTES", "NO JALO ${response.message()}")
                }
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is java.net.UnknownHostException -> "No hay conexión a Internet."
                    is java.net.SocketTimeoutException -> "La solicitud ha tardado demasiado en responder."
                    else -> "Error al cargar la información."
                }

                Log.d("ROUTES", "MENSAJE ERROR " + e.message.toString())
                _uiState.value = _uiState.value.copy(state = OrsState.FAILURE, message = errorMessage, error = e.message.toString())
            }
        }
    }

    // función para limpiar los datos
    fun clear() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                geometry = null,
                properties = null,
                state = OrsState.NONE,
                message = "",
                error = "",
                time = null,
                distance = null
            )
        }
    }
}

data class OrsUiState(
    val geometry: Geometry? = null,
    val properties: Properties? = null,
    val state: OrsState = OrsState.NONE,
    val message: String = "",
    val error: String = "",
    val time: String? = null,
    val distance: String? = null,
)


@Suppress("UNCHECKED_CAST")
class OrsViewModelFactory(
    private val remoteRepository: RemoteRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if( modelClass.isAssignableFrom(OrsViewModel::class.java)){
            return OrsViewModel(remoteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}