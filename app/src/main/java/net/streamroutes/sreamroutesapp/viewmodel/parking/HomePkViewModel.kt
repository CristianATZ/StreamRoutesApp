package net.streamroutes.sreamroutesapp.viewmodel.parking

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class TipoVehiculo {
    NINGUNO, CARRO, MOTO, TRACTOR
}


data class Estacionamiento (
    val nombre: String,
    val calle: String,
    val colonia: String,
    val codigoPostal: String,
    val opiniones: String,
    val tiempoAprox: String,
    val calificacion: String,
    val precio: Int
)

@SuppressLint("MutableCollectionMutableState")
class HomePkViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomePkUiState())
    val uiState: StateFlow<HomePkUiState> = _uiState.asStateFlow()

    var verTodo by mutableStateOf(false)
        private set

    var iniciarRecorrido by mutableStateOf(false)
        private set

    var estacionamientoSeleccionado by mutableStateOf(Estacionamiento("","", "", "", "", "", "", -1))
        private set

    var vehiculoSeleccionado by mutableStateOf(
        Vehiculo("", TipoVehiculo.NINGUNO, "", "", "", ColorVehiculo.NINGUNO)
    )
        private set

    var verEstacionamiento by mutableStateOf(false)
        private set

    var rutaEstacionamiento by mutableStateOf(mutableListOf<LatLng>())
        private set

    var currentLocation by mutableStateOf(
        LatLng(20.13961981092977, -101.15076362059153)
    )
        private set

    var leerQR by mutableStateOf(false)
        private set

    init {
        _uiState.value = HomePkUiState(
            vehiculoSeleccionado,
            verTodo,
            iniciarRecorrido,
            estacionamientoSeleccionado,
            verEstacionamiento,
            rutaEstacionamiento,
            currentLocation,
            leerQR
        )
    }

    fun updateVehiculoSeleccionado(_selection: Vehiculo){
        vehiculoSeleccionado = _selection
    }

    fun updateVerTodo(_verTodo: Boolean){
        verTodo = _verTodo
    }

    fun updateIniciarRecorrido(_iniciarRecorrido: Boolean){
        viewModelScope.launch {
            delay(250)
            iniciarRecorrido = _iniciarRecorrido
        }
    }

    fun updateEstacionamientoSeleccionado(_estacionamiento: Estacionamiento){
        viewModelScope.launch {
            delay(250)
            estacionamientoSeleccionado = _estacionamiento
        }
    }

    fun updateVerEstacionamiento(_ver: Boolean){
        viewModelScope.launch {
            delay(250)
            verEstacionamiento = _ver
        }
    }

    fun updateRutaEstacionamiento(_ruta: MutableList<LatLng>){
        rutaEstacionamiento = _ruta
    }

    fun updateCurrentLocation(_current: LatLng){
        currentLocation = _current
    }

    fun updateLeerQR(_leer: Boolean){
        viewModelScope.launch {
            delay(250)
            leerQR = _leer
        }
    }

    fun resetViewModel(){
        verTodo = false
        iniciarRecorrido = false
        estacionamientoSeleccionado = Estacionamiento("","", "", "", "", "", "", -1)
        vehiculoSeleccionado = Vehiculo("", TipoVehiculo.NINGUNO, "", "", "", ColorVehiculo.NINGUNO)
        verEstacionamiento = false
        rutaEstacionamiento.clear()
        currentLocation = LatLng(20.139609738093373, -101.1507421629189)
        leerQR = false

        _uiState.value = HomePkUiState()
    }

}

data class HomePkUiState(
    val vehiculoSeleccionado: Vehiculo? = null,
    val verTodo: Boolean = false,
    val iniciarRecorrido: Boolean = false,
    val estacionamientoSeleccionado: Estacionamiento? = null,
    val verEstacionamiento: Boolean = false,
    val rutaEstacionamiento: MutableList<LatLng> = mutableListOf(),
    val currentLocation: LatLng? = null,
    val leerQR: Boolean = false
)