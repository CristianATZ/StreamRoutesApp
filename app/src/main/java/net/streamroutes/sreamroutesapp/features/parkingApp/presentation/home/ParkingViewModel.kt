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

    init {
        getAllParkings()
    }

    fun getAllParkings(){
        viewModelScope.launch {
            _parkings.value = parkingRepository.getAllParkings()
        }
    }
}