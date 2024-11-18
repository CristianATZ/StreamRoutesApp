package net.streamroutes.sreamroutesapp.features.maps.presentation.fastest

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import net.streamroutes.sreamroutesapp.features.settings.presentation.maps.MapSettingsViewModel

@Composable
fun FastestScreen(
    modifier: Modifier = Modifier,
    transportViewModel: TransportViewModel = hiltViewModel(),
    mapSettingsViewModel: MapSettingsViewModel = hiltViewModel()
) {
    val routeColor by mapSettingsViewModel.routeColor.collectAsState()
    val lineSize by mapSettingsViewModel.lineSize.collectAsState()
    val orsRoute by transportViewModel.orsRoute.collectAsState()

    var currenTab by remember {
        mutableIntStateOf(0)
    }

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

    // SELECTPOINTS SCREEN
    val dest = if(markerDestinaton.position == LatLng(0.0,0.0)) "" else markerDestinaton.position.toString()
    val restartLocation = {
        markerDestinaton.position = LatLng(0.0, 0.0)
        address = ""
    }
    val onCalculateRoute = {
        // CAMBIAR PANTALLA
        // MANDAR DATOS A LA API Y RECUPERAR LOS 4 TIPOS DE VIAJE
        transportViewModel.getOrsRoute("foot-walking", markerMyLocation.position, markerDestinaton.position)
        isCalculated = true
        currenTab = 1
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

    val onChangeRoute = { route: Int ->
        currentRoute = route
    }

    LaunchedEffect(Unit) {
        coroutine.launch {
            currentAdress = transportViewModel.getAddress(markerMyLocation.position).toString()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
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
                    color = Color(routeColor),
                    width = lineSize.toFloat()
                )

            }

            // ROUTES INFORMATION
            // dibujar polilinea
        }

        AnimatedVisibility(
            visible = currenTab == 0,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            FastestSelectPoints(
                currentRoute = currentAdress,
                dest = address,
                onRestartLocation = restartLocation,
                onCalculateRoute = onCalculateRoute,
                onMyLocation = {
                    updateCameraPosition(
                        markerMyLocation.position
                    )
                },
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

        AnimatedVisibility(
            visible = currenTab == 1,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            FastestRoutesInformation(
                currentRoute = currentRoute,
                onChangeRoute = { route ->
                    onChangeRoute(route)
                },
                onSelectRoute = {
                    currenTab = 2
                },
                onMyLocation = {
                    updateCameraPosition(
                        markerMyLocation.position
                    )
                },
                onCancelRoute = {
                    currenTab = 0
                    isCalculated = false
                    transportViewModel.restartOrsRoute()
                }
            )
        }

        AnimatedVisibility(
            visible = currenTab == 2,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            FastestRoute(
                currentRoute = "Padre Luis Gaytan",
                onCancelRoute = {
                    currenTab = 1
                } ,
                onMyLocation = {
                    updateCameraPosition(
                        markerMyLocation.position
                    )
                }
            )
        }
    }
}