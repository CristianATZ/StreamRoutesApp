package net.streamroutes.sreamroutesapp.features.maps.presentation.fastest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.features.components.MapFullSize

@Composable
fun FastestScreen(
    modifier: Modifier = Modifier
) {
    val coroutine = rememberCoroutineScope()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(20.126856880277188, -101.19127471960047), 17f) // San Francisco como posición inicial
    }

    val markerDestinaton = rememberMarkerState()
    val markerMyLocation = rememberMarkerState(
        position = LatLng(20.126856880277188, -101.19127471960047)
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
    val onCalculareRoute = {
        // CAMBIAR PANTALLA
        // MANDAR DATOS A LA API Y RECUPERAR LOS 4 TIPOS DE VIAJE
    }

    // ROUTES INFORMATION
    var currentRoute by remember {
        mutableIntStateOf(1)
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
        MapFullSize(
            cameraPositionState = cameraPositionState,
            onMapClick = {  coords ->
                // SELECTPOINTS SCREEN
                /*markerDestinaton.position = coords
                updateCameraPosition(coords)*/
            },
            onMapLoaded = { /*TODO */ }
        ) {
            // SELECTPOINTS SCREEN
            /*Marker(
                state = markerMyLocation
            )
            Marker(
                state = markerDestinaton
            )*/

            // ROUTES INFORMATION
            // dibujar polilinea
        }

        FastestSelectPoints(
            currentRoute = "Padre Luis Gaytan, San Isidro 38887",
            dest = dest,
            onRestartLocation = restartLocation,
            onCalculateRoute = onCalculareRoute,
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