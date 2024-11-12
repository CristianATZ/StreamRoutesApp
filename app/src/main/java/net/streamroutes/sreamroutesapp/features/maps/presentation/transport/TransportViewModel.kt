package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.RouteRepository
import net.streamroutes.sreamroutesapp.core.data.repository.RouteWithPlaces
import javax.inject.Inject

    @HiltViewModel
    class TransportViewModel @Inject constructor(
        private val routeRepository: RouteRepository
    ) : ViewModel() {
        private val _routes = MutableStateFlow<List<RouteWithPlaces>?>(null)
        val routes: StateFlow<List<RouteWithPlaces>?> = _routes

        private val _selectedRoute = MutableStateFlow<RouteWithPlaces?>(null)
        val selectedRoute: StateFlow<RouteWithPlaces?> = _selectedRoute

        /**
         * Inicializador del viewModel para obtener todas las rutas de transporte
         */
        init {
            getAllRoutes()
        }

        /**
         * Método usaro para traer todas las registradas en la base de datos
         */
        fun getAllRoutes(){
            viewModelScope.launch {
                _routes.value = routeRepository.getAllRoutes()
                _selectedRoute.value = routes?.value?.get(0)
            }
        }


        /**
         * Método para actualizar la ruta seleccionada
         */
        fun selectRoute(route: RouteWithPlaces) {
            _selectedRoute.value = route
        }
    }

class TransportViewModelFactory(
    private val routeRepository: RouteRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TransportViewModel::class.java)){
            return TransportViewModel(routeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
