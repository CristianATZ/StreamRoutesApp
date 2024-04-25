package net.streamroutes.sreamroutesapp.viewmodel.parking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomePkViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(HomePkUiState())
    val uiState: StateFlow<HomePkUiState> = _uiState.asStateFlow()

    var verTodo by mutableStateOf(false)
        private set

    var iniciarRecorrido by mutableStateOf(false)
        private set

    var estacionamientoSeleccionado by mutableStateOf(Estacionamiento("","", "", "", "", -1))
        private set

    var vehiculoSeleccionado by mutableStateOf(Vehiculo("", TipoVehiculo.NINGUNO))
        private set

    var verEstacionamiento by mutableStateOf(false)
        private set

    init {
        _uiState.value = HomePkUiState(
            vehiculoSeleccionado,
            verTodo,
            iniciarRecorrido,
            estacionamientoSeleccionado,
            verEstacionamiento
        )
    }

    fun updateVehiculoSeleccionado(_selection: Vehiculo){
        vehiculoSeleccionado = _selection
    }

    fun updateVerTodo(_verTodo: Boolean){
        verTodo = _verTodo
    }

    fun updateIniciarRecorrido(_iniciarRecorrido: Boolean){
        iniciarRecorrido = _iniciarRecorrido
    }

    fun updateEstacionamientoSeleccionado(_estacionamiento: Estacionamiento){
        estacionamientoSeleccionado = _estacionamiento
    }

    fun updateVerEstacionamiento(_ver: Boolean){
        verEstacionamiento = _ver
    }
}

data class HomePkUiState(
    val vehiculoSeleccionado: Vehiculo? = null,
    val verTodo: Boolean = false,
    val iniciarRecorrido: Boolean = false,
    val estacionamientoSeleccionado: Estacionamiento? = null,
    val verEstacionamiento: Boolean = false
)

enum class TipoVehiculo {
    NINGUNO, CARRO, MOTO, TRACTOR
}

data class Vehiculo (
    val matricula: String,
    val tipo: TipoVehiculo
)

data class Estacionamiento (
    val nombre: String,
    val calle: String,
    val colonia: String,
    val codigoPostal: String,
    val calificacion: String,
    val precio: Int
)