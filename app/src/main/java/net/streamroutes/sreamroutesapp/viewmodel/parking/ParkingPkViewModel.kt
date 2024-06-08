package net.streamroutes.sreamroutesapp.viewmodel.parking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.streamroutes.sreamroutesapp.data.model.Location
import net.streamroutes.sreamroutesapp.data.model.ParkingResultItem
import net.streamroutes.sreamroutesapp.data.repository.RemoteRepository


data class HistorialItem(
    val vehiculo: Vehiculo,
    val estacionamiento: ParkingResultItem,
    val tiempo: Int,
    val total: Int
)

class ParkingPkViewModel(
    private val remoteRepository: RemoteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ParkingPkUiState())
    val uiState: StateFlow<ParkingPkUiState> = _uiState.asStateFlow()

    init {
        ParkingPkUiState()
    }

    fun updateVehiculo(vehiculo: Vehiculo) {
        _uiState.value = _uiState.value.copy(vehiculo = vehiculo)
    }

    fun updateEstacionamiento(estacionamiento: ParkingResultItem) {
        _uiState.value = _uiState.value.copy(estacionamiento = estacionamiento)
    }

    fun updateEstacionado(estacionado: Boolean) {
        _uiState.value = _uiState.value.copy(estacionado = estacionado)
    }

    fun updateTotal(total: Int) {
        _uiState.value = _uiState.value.copy(total = total)
    }

    fun updateTiempo(tiempo: Int) {
        _uiState.value = _uiState.value.copy(tiempo = tiempo)
    }

    fun updatePagado(pagado: Boolean) {
        _uiState.value = _uiState.value.copy(pagado = pagado)
    }

    fun updateHistorial(new: HistorialItem) {
        _uiState.value = _uiState.value.copy(historial = _uiState.value.historial + new)
    }

    fun updateVerHistorial(ver: Boolean) {
        _uiState.value = _uiState.value.copy(verHistorial = ver)
    }
}

data class ParkingPkUiState(
    val estacionado: Boolean = false,
    val estacionamiento: ParkingResultItem = ParkingResultItem(0.0,0,"","", Location(0.0,0.0),0,"", emptyList(),"",0,""),
    val vehiculo: Vehiculo = Vehiculo("",TipoVehiculo.NINGUNO, "","","",ColorVehiculo.BLANCO),
    val total: Int = 0,
    val tiempo: Int = 0,
    val pagado: Boolean = false,
    val historial: List<HistorialItem> = emptyList(),
    val verHistorial: Boolean = false
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