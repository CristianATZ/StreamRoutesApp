package net.streamroutes.sreamroutesapp.viewmodel.parking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.streamroutes.sreamroutesapp.data.model.orsModel.RouteResult
import net.streamroutes.sreamroutesapp.data.model.parkinModel.ParkingResultItem
import net.streamroutes.sreamroutesapp.data.repository.RemoteRepository

class ViajePkViewModel(
    private val remoteRepository : RemoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ViajePkUiState())
    val uiState : StateFlow<ViajePkUiState> = _uiState.asStateFlow()

    fun fetchBestRoute(start: String, end: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(state = ServiceState.LOADING, message = "Cargando mejor ruta...")
            try {
                Log.d("ROUTES", "$start, $end")
                val response = withContext(Dispatchers.IO) {
                    remoteRepository.getBestRoute(start, end)
                }

                if (response.isSuccessful) {
                    val ruta = response.body()
                    ruta?.let {
                        _uiState.value = _uiState.value.copy(rutaEstacionamiento = it, message = "Información cargada con éxito.", state = ServiceState.SUCCESSFUL)
                        //_uiState.value = _uiState.value.copy(coordenadas = it.features.last().geometry.coordinates.map { x -> LatLng(x.last(), x.first()) })
                    }
                    Log.d("ROUTES", "SI JALO/ ${uiState.value.rutaEstacionamiento!!.features.last().properties.segments.last().steps}")
                    //Log.d("ROUTES", uiState.value.rutaEstacionamiento.features.last().geometry.coordinates.map { doubles -> LatLng(doubles.first(), doubles.last()) }.toString())
                } else {
                    _uiState.value = _uiState.value.copy(state = ServiceState.FAILURE, message = "Error en la solicitud: ${response.message()}")
                    Log.d("ROUTES", "NO JALO ${response.message()},")
                }
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is java.net.UnknownHostException -> "No hay conexión a Internet."
                    is java.net.SocketTimeoutException -> "La solicitud ha tardado demasiado en responder."
                    else -> "Error al cargar la información."
                }

                Log.d("ROUTES", e.message.toString())
                _uiState.value = _uiState.value.copy(state = ServiceState.FAILURE, message = errorMessage, error = e.message.toString())
            }
        }
    }

    fun cancelarRecorrido(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                rutaEstacionamiento = null,
                state = ServiceState.NONE,
                message = "",
                error = "",
                vehiculoSeleccionado = null,
                estacionamientoSeleccionado = null,
                leerQR = false
            )
        }
    }

    fun updateVehiculo(x : Vehiculo?){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                vehiculoSeleccionado = x
            )
        }
    }

    fun updateEstacionamiento(x : ParkingResultItem){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                estacionamientoSeleccionado = x
            )
        }
    }

    fun updateLeerQR(leer: Boolean) {
        viewModelScope.launch {
            delay(250)
            _uiState.value = _uiState.value.copy(leerQR = leer)
        }
    }

}

data class ViajePkUiState(
    // peticion
    val rutaEstacionamiento: RouteResult? = null,
    val state: ServiceState = ServiceState.NONE,
    val message: String = "",
    val error: String = "",

    // local
    val vehiculoSeleccionado: Vehiculo? = null,
    val estacionamientoSeleccionado: ParkingResultItem? = null,
    var leerQR: Boolean = false
)

    @Suppress("UNCHECKED_CAST")
    class ViajePkViewModelFactory(
        private val remoteRepository: RemoteRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViajePkViewModel::class.java)) {
                return ViajePkViewModel(remoteRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }