package net.streamroutes.sreamroutesapp.viewmodel.parking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
): ViewModel(){
    private val _uiState = MutableStateFlow(ParkingPkUiState())
    val uiState: StateFlow<ParkingPkUiState> = _uiState.asStateFlow()

    var estacionado by mutableStateOf(false)
        private set
    var estacionamiento by mutableStateOf(ParkingResultItem(0.0,0,"","", Location(0.0,0.0),0,"", emptyList(),"",0,""))
        private set

    var vehiculo by mutableStateOf(Vehiculo("", TipoVehiculo.NINGUNO, "", "", "", ColorVehiculo.NINGUNO))
        private set

    var total by mutableIntStateOf(0)
        private set

    var tiempo by mutableIntStateOf(0)
        private set

    var pagado by mutableStateOf(false)
        private set

    var historial by mutableStateOf(mutableListOf<HistorialItem>())
        private set

    var verHistorial by mutableStateOf(false)
        private set

    init {
        _uiState.value = ParkingPkUiState(
            estacionado,
            estacionamiento,
            vehiculo,
            total,
            tiempo,
            pagado,
            historial,
            verHistorial
        )
    }

    fun updateVehiculo(_vehiculo: Vehiculo){
        vehiculo = _vehiculo
    }

    fun updateEstacionamiento(_estacionamiento: ParkingResultItem){
        estacionamiento = _estacionamiento
    }

    fun updateEstacionado(_estacionado: Boolean){
        estacionado = _estacionado
    }

    fun updateTotal(_total: Int){
        total = _total
    }

    fun updateTiempo(_tiempo: Int){
        tiempo = _tiempo
    }

    fun updatePagado(_pagado: Boolean){
        pagado = _pagado
    }

    fun updateHistorial(_new: HistorialItem){
        historial.add(_new)
    }

    fun updateVerHistorial(_ver: Boolean){
        verHistorial = _ver
    }
}

data class ParkingPkUiState(
    val estacionado: Boolean = false,
    val estacionamiento: ParkingResultItem = ParkingResultItem(0.0,0,"","", Location(0.0,0.0),0,"", emptyList(),"",0,""),
    val vehiculo: Vehiculo = Vehiculo("",TipoVehiculo.NINGUNO, "", "", "", ColorVehiculo.BLANCO),
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