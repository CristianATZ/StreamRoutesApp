package net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.HistoricalParkingWithInfo
import net.streamroutes.sreamroutesapp.core.data.repository.ParkingRepository
import net.streamroutes.sreamroutesapp.core.data.repository.ParkingWithPlace
import net.streamroutes.sreamroutesapp.core.data.repository.ReservationWithInfo
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import net.streamroutes.sreamroutesapp.core.domain.model.HistoricalParking
import net.streamroutes.sreamroutesapp.core.domain.model.ReservationParking
import javax.inject.Inject

@HiltViewModel
class ParkingViewModel @Inject constructor(
    private val parkingRepository: ParkingRepository,
    private val userRepository: UserRepository
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

    // Variable usada para obtener el historial de aparcamiento de un usuario
    private val _historicalParking = MutableStateFlow<List<HistoricalParkingWithInfo>?>(null)
    val historicalParking: StateFlow<List<HistoricalParkingWithInfo>?> = _historicalParking

    // Variable usada para obtener las reservaciones de un usuario
    private val _reservations = MutableStateFlow<List<ReservationWithInfo>?>(null)
    val reservations: StateFlow<List<ReservationWithInfo>?> = _reservations

    // Variable usada para guardar un historial seleccionado
    private val _selectedHistorical = MutableStateFlow<HistoricalParkingWithInfo?>(null)
    val selectedHistorical: StateFlow<HistoricalParkingWithInfo?> = _selectedHistorical

    // Variable usada para guardar un historial seleccionado
    private val _selectedReservation = MutableStateFlow<ReservationWithInfo?>(null)
    val selectedReservation: StateFlow<ReservationWithInfo?> = _selectedReservation


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

    /**
     * Método usado para actualizar el estacionamiento seleccionado
     */
    fun selectReservations(reservation: ReservationWithInfo){
        _selectedReservation.value = reservation
    }


    /**
     * Método usado para obtener el historial de aparcamientos de un usuario
     */
    fun getHistoricalParkingByUser(){
        viewModelScope.launch {
            val currentUser = userRepository.getCurrentUser()
            if(currentUser != null){
                _historicalParking.value = parkingRepository.getHistoricalParkingByUser(currentUser.uid)
            } else {
                _historicalParking.value = null
            }
        }
    }


    /**
     * Método usado para obtener las reservaciones de un usuario
     */
    fun getReservationsByUser(){
        viewModelScope.launch {
            val currentUser = userRepository.getCurrentUser()
            if(currentUser != null){
                _reservations.value = parkingRepository.getReservationByUser(currentUser.uid)
            } else {
                _reservations.value = null
            }
        }
    }


    /**
     * Método usado para actualizar un historicalParking seleccionado
     */
    fun selectHistoricalParking(historicalParking: HistoricalParkingWithInfo){
        _selectedHistorical.value = historicalParking
    }
}