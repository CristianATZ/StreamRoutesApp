package net.streamroutes.sreamroutesapp.viewmodel.parking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ParkingPkViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(ParkingPkUiState())
    val uiState: StateFlow<ParkingPkUiState> = _uiState.asStateFlow()

    var estacionamiento by mutableStateOf(Estacionamiento("","", "", "", "", -1))
        private set

    var vehiculo by mutableStateOf(Vehiculo("", TipoVehiculo.NINGUNO, "", "", "", ColorVehiculo.NINGUNO))
        private set

    init {
        _uiState.value = ParkingPkUiState(
            estacionamiento,
            vehiculo
        )
    }
}

data class ParkingPkUiState(
    val estacionamiento: Estacionamiento? = null,
    val vehiculo: Vehiculo? = null
)