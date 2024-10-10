package net.streamroutes.sreamroutesapp.viewmodel.parking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.streamroutes.sreamroutesapp.data.model.parkings.ParkingResult
import net.streamroutes.sreamroutesapp.data.repository.RemoteRepository

enum class TipoVehiculo {
    NINGUNO, CARRO, MOTO, TRACTOR
}

enum class ServiceState {
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
            _uiState.value = _uiState.value.copy(state = ServiceState.LOADING, message = "Cargando todos...")
            try {
                val response = withContext(Dispatchers.IO) {
                    remoteRepository.getParkings()
                }


                if(response.isSuccessful){
                    val estacionamiento = response.body()

                    estacionamiento?.let {
                        _uiState.value = _uiState.value.copy(parkingList = it, message = "Información cargada con exito.", state = ServiceState.SUCCESSFUL)
                    }

                    Log.d("PARKINGS", "SI JALO")
                } else {
                    _uiState.value = _uiState.value.copy(state = ServiceState.FAILURE, message = "Error en la solicitud: ${response.message()}")
                    Log.d("PARKINGS", "NO JALO")
                }
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is java.net.UnknownHostException -> "No hay conexión a Internet."
                    is java.net.SocketTimeoutException -> "La solicitud ha tardado demasiado en responder."
                    else -> "Error al cargar la información."
                }

                Log.d("PARKINGS", e.message.toString())
                _uiState.value = _uiState.value.copy(state = ServiceState.FAILURE, message = errorMessage, error = e.message.toString())
            }
        }
    }


    fun updateVehiculoSeleccionado(selection: Vehiculo) {
        _uiState.value = _uiState.value.copy(vehiculoSeleccionado = selection)
    }

    fun updateVerTodo(verTodo: Boolean) {
        _uiState.value = _uiState.value.copy(verTodo = verTodo)
    }

    /*fun updateIniciarRecorrido(iniciarRecorrido: Boolean) {
        viewModelScope.launch {
            delay(250)
            _uiState.value = _uiState.value.copy(iniciarRecorrido = iniciarRecorrido)
        }
    }*/

    /*fun updateEstacionamientoSeleccionado(estacionamiento: ParkingResultItem) {
        viewModelScope.launch {
            delay(250)
            _uiState.value = _uiState.value.copy(estacionamientoSeleccionado = estacionamiento)
        }
    }*/

    /*fun updateVerEstacionamiento(ver: Boolean) {
        viewModelScope.launch {
            delay(250)
            _uiState.value = _uiState.value.copy(verEstacionamiento = ver)
        }
    }*/

    /*fun updateLeerQR(leer: Boolean) {
        viewModelScope.launch {
            delay(250)
            _uiState.value = _uiState.value.copy(leerQR = leer)
        }
    }*/

    fun resetViewModel() {
        _uiState.value = HomePkUiState(parkingList = _uiState.value.parkingList)
    }
}

data class HomePkUiState(
    // peticion remota
    val parkingList: ParkingResult = ParkingResult(),
    val state : ServiceState = ServiceState.NONE,
    val message: String = "",
    val error: String = "",

    // local
    val vehiculoSeleccionado: Vehiculo = Vehiculo("PRA46G",TipoVehiculo.NINGUNO, "","","",ColorVehiculo.BLANCO),
    val verTodo: Boolean = false,
    val currentLocation: LatLng = LatLng(0.0,0.0)
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