package net.streamroutes.sreamroutesapp.features.turism.presentation.turismList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.RouteRepository
import net.streamroutes.sreamroutesapp.core.data.repository.TuristicPointWithInfo
import javax.inject.Inject

@HiltViewModel
class TourismViewModel @Inject constructor(
    private val routeRepository: RouteRepository
): ViewModel() {
    // Variable usada para guardar todos los puntos turisticos de la base de datos
    private val _turisticPoints = MutableStateFlow<List<TuristicPointWithInfo>?>(null)
    val turisticPoints: MutableStateFlow<List<TuristicPointWithInfo>?> = _turisticPoints

    // Variable usada para guardar la ruta seleccionada en turismo
    private val _selectedTPRoute = MutableStateFlow<TuristicPointWithInfo?>(null)
    val selectedTPRoute: StateFlow<TuristicPointWithInfo?> = _selectedTPRoute

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


    /**
     * Método para actualizar la ruta seleccionada de turismo
     */
    fun selectTPRoute(route: TuristicPointWithInfo) {
        _selectedTPRoute.value = route
        //Log.d("TransportViewModel", selectedRoute.value.toString())
    }
}