package net.streamroutes.sreamroutesapp.features.turism.presentation.turismList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.ParkingRepository
import net.streamroutes.sreamroutesapp.core.data.repository.RouteRepository
import net.streamroutes.sreamroutesapp.core.domain.model.Place
import javax.inject.Inject

@HiltViewModel
class TurismListViewModel @Inject constructor(
    private val routeRepository: RouteRepository
): ViewModel() {
    private val _turisticPoints = MutableStateFlow<List<Place>?>(null)
    val turisticPoints: MutableStateFlow<List<Place>?> = _turisticPoints

    init {
        getAllTuristicPoints()
    }

    /**
     * Método usado para traer todos los puntos turísticos de la base de datos
     */
    fun getAllTuristicPoints(){
        viewModelScope.launch {
            _turisticPoints.value = routeRepository.getAllTuristicPoints()
        }
    }
}