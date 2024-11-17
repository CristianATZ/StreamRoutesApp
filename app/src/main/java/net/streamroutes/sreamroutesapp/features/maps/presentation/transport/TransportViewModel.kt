package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
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
    // Variable usada para guardar todas las rutas de tranporte público de la base de datos
    private val _routes = MutableStateFlow<List<RouteWithPlaces>?>(null)
    val routes: StateFlow<List<RouteWithPlaces>?> = _routes

    // Variable usada para guardar la ruta seleccionada en rutas de transporte
    private val _selectedRoute = MutableStateFlow<RouteWithPlaces?>(null)
    val selectedRoute: StateFlow<RouteWithPlaces?> = _selectedRoute

    // Variable usada para capturar los puntos (LatLng) de la ruta de transporte seleccionada
    private val _orsRoute = MutableStateFlow<List<LatLng>>(emptyList())
    val orsRoute: StateFlow<List<LatLng>> = _orsRoute

    // Variable usada para almacenar los datos de la ruta (distancia, tiempo, etc.)
    private val _orsRouteData = MutableStateFlow<Map<String, Any?>?>(null)
    val orsRouteData: StateFlow<Map<String, Any?>?> = _orsRouteData

    // Variable usada para almacenar la dirección del marcador de planifica tu viaje
    private val _markerAddress = MutableStateFlow<String?>(null)
    val markerAdress: StateFlow<String?> = _markerAddress


    /**
     * Inicializador del viewModel para obtener todas las rutas de transporte
     */
    init {
        getAllRoutes()
    }


    /**
     * Método usado para traer todas las registradas en la base de datos
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
        //Log.d("TransportViewModel", selectedRoute.value.toString())
    }


    /**
     * Método usado para trazar la ruta para transporte público
     */
    fun getOrsRoute(profile: String, start: LatLng, end: LatLng) {
        viewModelScope.launch {
            try {
                // Enviar request al repository y guardar su response
                val response = orsRepository.fetchRoute(
                    profile,
                    "${start.longitude},${start.latitude}",
                    "${end.longitude},${end.latitude}"
                )

                if (response.isSuccessful) {
                    // Guardar datos de la ruta
                    val _distance = response.body()?.features?.get(0)?.properties?.segments?.get(0)?.distance?.div(1000)
                    val distance = _distance?.toBigDecimal()?.setScale(2, java.math.RoundingMode.HALF_EVEN)

                    val _duration = response.body()?.features?.get(0)?.properties?.segments?.get(0)?.duration?.div(60)
                    val duration = _duration?.toBigDecimal()?.setScale(2, java.math.RoundingMode.HALF_EVEN)

                    val nextStreet = response.body()?.features?.get(0)?.properties?.segments?.get(0)?.steps?.find { it.name != "-" }?.name

                    /*
                    Log.d("RESPONSE", "${start.longitude},${start.latitude}")
                    Log.d("RESPONSE", "${end.longitude},${end.latitude}")
                    Log.d("RESPONSE", "Distancia:  ${distance}")
                    Log.d("RESPONSE", "Duracion:  ${duration}")
                    Log.d("RESPONSE", "NextStreet:  ${nextStreet}")
                     */

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
                } else {
                    Log.e("ORS", "No se encontró una ruta válida en la respuesta de la API.")
                    _orsRoute.value = emptyList()
                }

            } catch (e: Exception) {
                Log.e("ORS", "Error al obtener la ruta de ORS: ${e.message}", e)
            }
        }
    }


    /**
     * Método usado para obtener la dirección de un LatLng
     */
    suspend fun getAddress(location: LatLng): String? {
        return try {
            val response = orsRepository.getAddress(
                "${location.longitude}", "${location.latitude}"
            )

            if (response.isSuccessful) {
                val address = response.body()?.features?.get(0)?.properties?.label.toString()
                _markerAddress.value = address
                //Log.d("RESPONSE", address ?: "NADA ALV")
                address
            } else {
                //Log.e("RESPONSE", "No se encontró una dirección válida en la respuesta de la API.")
                null
            }
        } catch (e: Exception) {
            //Log.e("RESPONSE", "Error al obtener la dirección: ${e.message}", e)
            null
        }
    }


    /**
     * Método usado para planificar una ruta que pasa por varios destinos
     */
    fun planRoute(coordinates: List<LatLng>) {
        viewModelScope.launch {
            try {
                // Convertir `LatLng` a la estructura esperada para la API
                val routeCoordinates = coordinates.map { listOf(it.longitude, it.latitude) }

                // Llamar al repositorio para obtener la ruta
                val response = orsRepository.planRoute(routeCoordinates)

                if (response.isSuccessful) {
                    val polyline = response.body()?.routes?.get(0)?.geometry
                    val coordinates: List<LatLng> = PolyUtil.decode(polyline)
                    Log.d("RESPONSE", coordinates.toString())
                    _orsRoute.value = coordinates
                } else {
                    Log.e("RESPONSE", "No se encontró una ruta válida en la respuesta de la API.")
                    _orsRoute.value = emptyList()
                }
            } catch (e: Exception) {
                Log.e("RESPONSE", "Error al planificar la ruta: ${e.message}", e)
            }
        }
    }

}
