package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    // --------------------------------------------------------------------------
    /**
     * Sección para planifica tu viaje
     */

    // Variable usada para capturar la ruta de punto A a punto B en automovil
    private val _orsRouteCar = MutableStateFlow<List<LatLng>>(emptyList())
    val orsRouteCar: StateFlow<List<LatLng>> = _orsRouteCar

    // Variable usada para almacenar los datos de la ruta de punto A a punto B en automovil
    private val _orsRouteDataCar = MutableStateFlow<Map<String, Any?>?>(null)
    val orsRouteDataCar: StateFlow<Map<String, Any?>?> = _orsRouteDataCar

    // Variable usada para capturar la ruta de punto A a punto B a pie
    private val _orsRouteWalk = MutableStateFlow<List<LatLng>>(emptyList())
    val orsRouteWalk: StateFlow<List<LatLng>> = _orsRouteWalk

    // Variable usada para almacenar los datos de la ruta de punto A a punto B en automovil
    private val _orsRouteDataWalk = MutableStateFlow<Map<String, Any?>?>(null)
    val orsRouteDataWalk: StateFlow<Map<String, Any?>?> = _orsRouteDataWalk

    // Variable usada para capturar la ruta de punto A a punto B en bicicleta
    private val _orsRouteBike = MutableStateFlow<List<LatLng>>(emptyList())
    val orsRouteBike: StateFlow<List<LatLng>> = _orsRouteBike

    // Variable usada para almacenar los datos de la ruta de punto A a punto B en automovil
    private val _orsRouteDataBike = MutableStateFlow<Map<String, Any?>?>(null)
    val orsRouteDataBike: StateFlow<Map<String, Any?>?> = _orsRouteDataBike

    // Variable usada para capturar la ruta de punto A a punto B en silla de ruedas
    private val _orsRouteWheelchair = MutableStateFlow<List<LatLng>>(emptyList())
    val orsRouteWheelchair: StateFlow<List<LatLng>> = _orsRouteWheelchair

    // Variable usada para almacenar los datos de la ruta de punto A a punto B en automovil
    private val _orsRouteDataWheelchair = MutableStateFlow<Map<String, Any?>?>(null)
    val orsRouteDataWheelchair: StateFlow<Map<String, Any?>?> = _orsRouteDataWheelchair


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

                    val duration = response.body()?.features?.get(0)?.properties?.segments?.get(0)?.duration?.div(60)?.toInt()

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

    fun restartOrsRoute() {
        viewModelScope.launch {
            _orsRoute.value = emptyList()
            _orsRouteData.value = null
        }
    }


    fun changeOrsData(currentRoute: Int){
        viewModelScope.launch {
            if(currentRoute == 1) _orsRouteData.value = _orsRouteDataCar.value
            else if(currentRoute == 2) _orsRouteData.value = _orsRouteDataWalk.value
            else if(currentRoute == 3) _orsRouteData.value = _orsRouteDataBike.value
            else if(currentRoute == 4) _orsRouteData.value = _orsRouteDataWheelchair.value
        }
    }


    fun getOrsRouteAllVehicles(start: LatLng, end: LatLng){
        viewModelScope.launch {
            // Calcular automovil
            getOrsRoute("driving-car", start, end)
            delay(1000)
            _orsRouteCar.value = _orsRoute.value
            _orsRouteDataCar.value = _orsRouteData.value

            // Calcular a pie
            getOrsRoute("foot-walking", start, end)
            delay(1000)
            _orsRouteWalk.value = _orsRoute.value
            _orsRouteDataWalk.value = _orsRouteData.value

            // Calcular bicicleta
            getOrsRoute("cycling-regular", start, end)
            delay(1000)
            _orsRouteBike.value = _orsRoute.value
            _orsRouteDataBike.value = _orsRouteData.value

            // Calcular silla de ruedas
            getOrsRoute("wheelchair", start, end)
            delay(1000)
            _orsRouteWheelchair.value = _orsRoute.value
            _orsRouteDataWheelchair.value = _orsRouteData.value
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
