package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.OrsRepository
import net.streamroutes.sreamroutesapp.core.data.repository.RouteRepository
import net.streamroutes.sreamroutesapp.core.data.repository.RouteWithPlaces
import net.streamroutes.sreamroutesapp.core.domain.network.OpenRouteServiceClient
import javax.inject.Inject

@HiltViewModel
class TransportViewModel @Inject constructor(
    private val routeRepository: RouteRepository,
    private val orsRepository: OrsRepository
) : ViewModel() {
    private val _routes = MutableStateFlow<List<RouteWithPlaces>?>(null)
    val routes: StateFlow<List<RouteWithPlaces>?> = _routes

    private val _selectedRoute = MutableStateFlow<RouteWithPlaces?>(null)
    val selectedRoute: StateFlow<RouteWithPlaces?> = _selectedRoute

    private val _orsRoute = MutableStateFlow<List<LatLng>>(emptyList())
    val orsRoute: StateFlow<List<LatLng>> = _orsRoute

    private val _orsRouteData = MutableStateFlow<Map<String, Any?>?>(null)
    val orsRouteData: StateFlow<Map<String, Any?>?> = _orsRouteData


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
            //_selectedRoute.value = routes?.value?.get(2)
        }
    }


    /**
     * Método para actualizar la ruta seleccionada
     */
    fun selectRoute(route: RouteWithPlaces) {
        _selectedRoute.value = route
        Log.d("TransportViewModel", selectedRoute.value.toString())
    }


    /**
     * Método usado para trazar la ruta para transporte público
     */
    fun getOrsRoute(start: LatLng, end: LatLng) {
        viewModelScope.launch {
            try {
                // Enviar request al repository y guardar su response
                val response = orsRepository.fetchRoute(
                    "${start.longitude},${start.latitude}",
                    "${end.longitude},${end.latitude}"
                )

                // Guardar datos de la ruta
                val _distance = response.body()?.features?.get(0)?.properties?.segments?.get(0)?.distance?.div(1000)
                val distance = _distance?.toBigDecimal()?.setScale(2, java.math.RoundingMode.HALF_EVEN)

                val _duration = response.body()?.features?.get(0)?.properties?.segments?.get(0)?.duration?.div(60)
                val duration = _duration?.toBigDecimal()?.setScale(2, java.math.RoundingMode.HALF_EVEN)

                val nextStreet = response.body()?.features?.get(0)?.properties?.segments?.get(0)?.steps?.find { it.name != "-" }?.name

                Log.d("RESPONSE", "${start.longitude},${start.latitude}")
                Log.d("RESPONSE", "${end.longitude},${end.latitude}")
                Log.d("RESPONSE", "Distancia:  ${distance}")
                Log.d("RESPONSE", "Duracion:  ${duration}")
                Log.d("RESPONSE", "NextStreet:  ${nextStreet}")

                val mapOrsRouteData = mapOf(
                    "distance" to distance,
                    "duration" to duration,
                    "nextStreet" to nextStreet
                )
                _orsRouteData.value = mapOrsRouteData

                // Calcular coordenadas y puntos de la polilínea
                val coordinates = response.body()?.features?.get(0)?.geometry?.coordinates
                val latLngList = coordinates?.map { LatLng(it[1], it[0]) }
                if (latLngList != null) {
                    _orsRoute.value = latLngList
                }
                //Log.d("RESPONSE", latLngList.toString())

                /*
                if (response.routes.isNotEmpty()) {
                    val coordinates = response.routes[0].features[0].geometry.coordinates
                    val latLngList = coordinates.map { LatLng(it[1], it[0]) }
                    _orsRoute.value = latLngList
                } else {
                    // Manejar caso de respuesta vacía o datos nulos
                    Log.e("ORS", "No se encontró una ruta válida en la respuesta de la API.")
                    _orsRoute.value = emptyList() // Si deseas limpiar la ruta anterior
                }
                 */
            } catch (e: Exception) {
                Log.e("ORS", "Error al obtener la ruta de ORS: ${e.message}", e)
            }
        }
    }
}

/*
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
*/