package net.streamroutes.sreamroutesapp.viewmodel.parking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class HistorialItem(
    val vehiculo: Vehiculo,
    val estacionamiento: Estacionamiento,
    val tiempo: Int,
    val total: Int
)

class ParkingPkViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(ParkingPkUiState())
    val uiState: StateFlow<ParkingPkUiState> = _uiState.asStateFlow()

    var estacionado by mutableStateOf(false)
        private set
    var estacionamiento by mutableStateOf(Estacionamiento("","", "", "", "", -1))
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

    fun updateEstacionamiento(_estacionamiento: Estacionamiento){
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
    val estacionamiento: Estacionamiento? = null,
    val vehiculo: Vehiculo? = null,
    val total: Int = 0,
    val tiempo: Int = 0,
    val pagado: Boolean = false,
    val historial: List<HistorialItem> = emptyList(),
    val verHistorial: Boolean = false
)