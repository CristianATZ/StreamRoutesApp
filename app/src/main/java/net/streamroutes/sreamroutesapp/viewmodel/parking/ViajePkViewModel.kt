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

    fun cancelarRecorrido(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
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