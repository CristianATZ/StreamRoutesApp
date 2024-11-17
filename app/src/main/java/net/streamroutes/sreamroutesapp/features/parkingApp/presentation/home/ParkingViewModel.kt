package net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.ParkingRepository
import net.streamroutes.sreamroutesapp.core.data.repository.ParkingWithPlace
import javax.inject.Inject

@HiltViewModel
class ParkingViewModel @Inject constructor(
    private val parkingRepository: ParkingRepository
): ViewModel() {
    // Variable usada para guardar todos los estacionamientos de la base de datos
    private val _parkings = MutableStateFlow<List<ParkingWithPlace>?>(null)
    val parkings: StateFlow<List<ParkingWithPlace>?> = _parkings

    // Variable usada para guardar el estacionamiento seleccionado en la vista principal
    private val _selectedParking = MutableStateFlow<ParkingWithPlace?>(null)
    val selectedParking: StateFlow<ParkingWithPlace?> = _selectedParking

    // Variable usada para guardar los servicios del estacionamiento seleccionado
    private val _services = MutableStateFlow<List<String>?>(null)
    val services: StateFlow<List<String>?> = _services


    init {
        getAllParkings()
    }

    /**
     * Método usado para traer todos los estacionamientos de la base de datos
     */
    fun getAllParkings(){
        viewModelScope.launch {
            _parkings.value = parkingRepository.getAllParkings()
        }
    }

    /**
     * Método para obtener todos los servicios de un estacionamiento
     */
    fun getServicesByParking(){
        viewModelScope.launch {
            // Enviar ID del selectedParking ("parking-01")
            val idParking = _selectedParking.value?.parking?.idPlace
            _services.value = idParking?.let { parkingRepository.getServicesByParking(it) }
        }
    }


    /**
     * Método usado para actualizar el estacionamiento seleccionado
     */
    fun selectParking(parking: ParkingWithPlace){
        _selectedParking.value = parking
    }
}