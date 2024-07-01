package net.streamroutes.sreamroutesapp.viewmodel.parking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.data.model.parkinModel.ParkingResultItem
import net.streamroutes.sreamroutesapp.data.repository.RemoteRepository


data class HistorialItem(
    val vehiculo: Vehiculo?,
    val estacionamiento: ParkingResultItem?,
    val tiempo: Int,
    val total: Int
)

class ParkingPkViewModel(
    private val remoteRepository: RemoteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ParkingPkUiState())
    val uiState: StateFlow<ParkingPkUiState> = _uiState.asStateFlow()

    fun agregarEstacionamiento(e: ParkingResultItem, t: Int, v: Vehiculo){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                estacionamiento = e,
                total = t,
                vehiculo = v
            )
        }
    }

    fun apartarLugar(a: Boolean, v: Vehiculo, e: ParkingResultItem, hi: Int, mi: Int, hf: Int, t: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                apartado = a,
                estacionamiento = e,
                vehiculo = v,
                horaInicio = hi,
                minutoInicio = mi,
                horaFinal = hf,
                total = t
            )
        }
    }
}

data class ParkingPkUiState(
    val estacionamiento: ParkingResultItem? = null,
    val vehiculo: Vehiculo? = null,
    val total: Int = 0,
    val tiempo: Int = 0,
    val pagado: Boolean = false,

    // apartar
    val apartado: Boolean = false,
    val tiempoApartado: Int = 0,
    val horaInicio: Int = 0,
    val minutoInicio: Int = 0,
    val horaFinal: Int = 0,
)

@Suppress("UNCHECKED_CAST")
class ParkingPkViewModelFactory(
    private val remoteRepository: RemoteRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParkingPkViewModel::class.java)) {
            return ParkingPkViewModel(remoteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}