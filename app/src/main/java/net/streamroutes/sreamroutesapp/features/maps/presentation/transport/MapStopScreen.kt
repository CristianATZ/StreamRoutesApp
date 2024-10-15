package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.domain.model.RouteInformation
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.maps.components.StopBottomSheet
import net.streamroutes.sreamroutesapp.features.maps.components.StopInformationBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapStopScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            skipHiddenState = false
        )
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(20.126856880277188, -101.19127471960047), 17f) // San Francisco como posición inicial
    }

    // MOVER SIN AFECTAR EL ZOOM
    /*val updateCameraPosition = {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLng(
                LatLng(37.7749, -122.4194)
            )
        )
    }*/

    // MOVER AFECTANDO EL ZOOM
    /*val updateCameraZoom = {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(37.7749, -122.4194), 10f
            )
        )
    }*/

    val stopList = listOf(
        Pair(
            RouteInformation(
                name = "Ruta 11 - El charco",
                countTurism = 4,
                hourAprox = 2,
                minutesAprox = 30,
                officialStops = 16,
                start = "C. Pipila, Col. Ninios Herores",
                end = "C. Francisco Marquez, Col. Zona Centro"
            ),
            LatLng(20.126856880277188, -101.19127471960047)
        ),
        Pair(
            RouteInformation(
                name = "Ruta 12 - Itsur",
                countTurism = 4,
                hourAprox = 2,
                minutesAprox = 30,
                officialStops = 16,
                start = "C. Pipila, Col. Ninios Herores",
                end = "C. Francisco Marquez, Col. Zona Centro"
            ),
            LatLng(20.13685688027719, -101.20127471960048)
        )
    )

    var stopSelected by remember {
        mutableStateOf<RouteInformation?>(null)
    }

    val updateStop = { stop: RouteInformation? ->
        stopSelected = stop
    }

    val markerPressed = { stop: RouteInformation? ->
        scope.launch {
            scaffoldState.bottomSheetState.hide()// esconder la hoja
            delay(300) // esperar 300 milisegundos
            updateStop(stop) // actualizar valor junto con la animacion
            scaffoldState.bottomSheetState.expand() // expandir la hoja
        }
    }
    
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            if(stopSelected != null) {
                stopSelected?.let {
                    StopInformationBottomSheet(
                        routeInformation = it,
                        onClose = {
                            markerPressed(null)
                        }
                    )
                }
            } else {
                StopBottomSheet()
            }
        },
        sheetShadowElevation = 8.dp
        // CAMBIAR EL MAPA, CALLES BLANCAS
        // CONSTRUCCIONES GRIS PARA QUE SE VEA
    ) {
        MapFullSize(
            cameraPositionState = cameraPositionState,
            onMapClick = {},
            onMapLoaded = {  },
            modifier = modifier
        ) {
            stopList.forEach { stop ->
                Marker(
                    state = MarkerState(position = stop.second),
                    onClick = {
                        if(stopSelected != stop.first) {
                            markerPressed(stop.first)
                        } else {
                            markerPressed(null)
                        }
                        true
                    }
                )
            }
        }
    }
}