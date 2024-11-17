package net.streamroutes.sreamroutesapp.features.maps.presentation.fastest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel

@Composable
fun FastestScreen(
    modifier: Modifier = Modifier,
    transportViewModel: TransportViewModel = hiltViewModel()
) {
    val coroutine = rememberCoroutineScope()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(21.017917732561727, -101.25808073954296), 17f) // San Francisco como posición inicial
    }

    // ROUTES INFORMATION
    var currentRoute by remember {
        mutableIntStateOf(1)
    }

    var isCalculated by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf("") }
    var currentAdress by remember { mutableStateOf("") }

    val markerDestinaton = rememberMarkerState()
    val markerMyLocation = rememberMarkerState(
        position = LatLng(21.017917732561727, -101.25808073954296)
    )

    // MOVER SIN AFECTAR EL ZOOM
    // CON ANIMACION INCLUIDA
    val updateCameraPosition = { coord: LatLng ->
        coroutine.launch {
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLng(coord),
                500 // Duración de la animación en milisegundos
            )
        }
    }

    val onMyLocation = {
        // cambiar camara a posicion actual
        //updateCameraPosition()
    }

    // SELECTPOINTS SCREEN
    val dest = if(markerDestinaton.position == LatLng(0.0,0.0)) "" else markerDestinaton.position.toString()
    val restartLocation = {
        markerDestinaton.position = LatLng(0.0, 0.0)
    }
    val onCalculateRoute = {
        // CAMBIAR PANTALLA
        // MANDAR DATOS A LA API Y RECUPERAR LOS 4 TIPOS DE VIAJE
        transportViewModel.getOrsRoute("foot-walking", markerMyLocation.position, markerDestinaton.position)
        isCalculated = true
    }

    // EVENTO ON CLICK DEL MAPA
    val onMapClick = { coords: LatLng ->
        // SELECTPOINTS SCREEN
        coroutine.launch {
            markerDestinaton.position = coords
            address = transportViewModel.getAddress(markerDestinaton.position).toString()
            updateCameraPosition(coords)
        }
    }


    LaunchedEffect(Unit) {
        coroutine.launch {
            currentAdress = transportViewModel.getAddress(markerMyLocation.position).toString()
        }
    }
    
    val onChangeRoute = { route: Int ->
        currentRoute = route
    }

    // FASTEST ROUTE
    val onCancelRoute = {
        // CANCELAR RUTA
        // ENVIAR AL INICIO
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        val orsRoute by transportViewModel.orsRoute.collectAsState()
        MapFullSize(
            cameraPositionState = cameraPositionState,
            /*
            onMapClick = {  coords ->
                // SELECTPOINTS SCREEN
                coroutine.launch {
                    markerDestinaton.position = coords
                    address = transportViewModel.getAddress(markerDestinaton.position).toString()
                    updateCameraPosition(coords)
                }
            },*/
            onMapClick = { coords ->
                onMapClick(coords)
            },
            onMapLoaded = { /*TODO */ },
            modifier = Modifier.fillMaxSize()
        ) {
            // SELECTPOINTS SCREEN
            Marker(
                state = markerMyLocation
            )
            Marker(
                state = markerDestinaton
            )

            if(isCalculated && orsRoute.isNotEmpty()){
                Polyline(
                    points = orsRoute,
                    color = Color.Black,
                    width = 8f
                )

            }

            // ROUTES INFORMATION
            // dibujar polilinea
        }

        FastestSelectPoints(
            currentRoute = currentAdress,
            dest = address,
            onRestartLocation = restartLocation,
            onCalculateRoute = onCalculateRoute,
            onMyLocation = onMyLocation,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        /*FastestRoutesInformation(
            currentRoute = currentRoute,
            onChangeRoute = { route ->
                onChangeRoute(route)
            },
            onSelectRoute = {
                // SELECCIONAR LA RUTA SELECCIONADA EN BASE
                // A LA RUTA ACTUAL SELECCIONADA
            },
            onMyLocation = onMyLocation
        )*/

        /*FastestRoute(
            currentRoute = "Padre Luis Gaytan",
            onCancelRoute = onCancelRoute,

        )*/
    }
}