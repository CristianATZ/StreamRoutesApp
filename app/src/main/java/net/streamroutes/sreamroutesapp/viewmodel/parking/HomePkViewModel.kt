package net.streamroutes.sreamroutesapp.viewmodel.parking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.streamroutes.sreamroutesapp.data.model.Location
import net.streamroutes.sreamroutesapp.data.model.ParkingResult
import net.streamroutes.sreamroutesapp.data.model.ParkingResultItem
import net.streamroutes.sreamroutesapp.data.repository.RemoteRepository

enum class TipoVehiculo {
    NINGUNO, CARRO, MOTO, TRACTOR
}

enum class ParkingState {
    NONE, LOADING, SUCCESSFUL, FAILURE
}

class HomePkViewModel(
    private val remoteRepository: RemoteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomePkUiState())
    val uiState: StateFlow<HomePkUiState> = _uiState.asStateFlow()

    init {
        resetViewModel()
    }

    // traer la informacion de los estacionamientos
    fun fetchParkings() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(state = ParkingState.LOADING, message = "Cargando todos...")
            try {
                val parkings = withContext(Dispatchers.IO) {
                    remoteRepository.getParkings()
                }
                _uiState.value = _uiState.value.copy(parkingList = parkings, message = "Información cargada con exito.", state = ParkingState.SUCCESSFUL)

                Log.d("PARKINGS", uiState.value.state.toString())
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is java.net.UnknownHostException -> "No hay conexión a Internet."
                    is java.net.SocketTimeoutException -> "La solicitud ha tardado demasiado en responder."
                    else -> "Error al cargar la información."
                }

                Log.d("STATE", e.message.toString())
                _uiState.value = _uiState.value.copy(state = ParkingState.FAILURE, message = errorMessage, error = e.message.toString())
            }
        }
    }

    fun updateVehiculoSeleccionado(selection: Vehiculo) {
        _uiState.value = _uiState.value.copy(vehiculoSeleccionado = selection)
    }

    fun updateVerTodo(verTodo: Boolean) {
        _uiState.value = _uiState.value.copy(verTodo = verTodo)
    }

    fun updateIniciarRecorrido(iniciarRecorrido: Boolean) {
        viewModelScope.launch {
            delay(250)
            _uiState.value = _uiState.value.copy(iniciarRecorrido = iniciarRecorrido)
        }
    }

    fun updateEstacionamientoSeleccionado(estacionamiento: ParkingResultItem) {
        viewModelScope.launch {
            delay(250)
            _uiState.value = _uiState.value.copy(estacionamientoSeleccionado = estacionamiento)
        }
    }

    fun updateVerEstacionamiento(ver: Boolean) {
        viewModelScope.launch {
            delay(250)
            _uiState.value = _uiState.value.copy(verEstacionamiento = ver)
        }
    }

    fun updateRutaEstacionamiento(ruta: MutableList<LatLng>) {
        _uiState.value = _uiState.value.copy(rutaEstacionamiento = ruta)
    }

    fun updateCurrentLocation(current: LatLng) {
        _uiState.value = _uiState.value.copy(currentLocation = current)
    }

    fun updateLeerQR(leer: Boolean) {
        viewModelScope.launch {
            delay(250)
            _uiState.value = _uiState.value.copy(leerQR = leer)
        }
    }

    fun resetViewModel() {
        _uiState.value = HomePkUiState()
    }
}

data class HomePkUiState(
    val parkingList: ParkingResult = ParkingResult(),
    val state: ParkingState = ParkingState.NONE,
    val message: String = "",
    val error: String = "",
    val vehiculoSeleccionado: Vehiculo = Vehiculo("",TipoVehiculo.NINGUNO, "","","",ColorVehiculo.BLANCO),
    val verTodo: Boolean = false,
    val iniciarRecorrido: Boolean = false,
    val estacionamientoSeleccionado: ParkingResultItem = ParkingResultItem(0.0,0,"","", Location(0.0,0.0),0,"", emptyList(),"",0,""),
    val verEstacionamiento: Boolean = false,
    val rutaEstacionamiento: MutableList<LatLng> = mutableListOf(),
    val currentLocation: LatLng = LatLng(0.0,0.0),
    val leerQR: Boolean = false
)

@Suppress("UNCHECKED_CAST")
class HomePkViewModelFactory(
    private val remoteRepository: RemoteRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomePkViewModel::class.java)) {
            return HomePkViewModel(remoteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}