package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
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
    var isLoading by remember {
        mutableStateOf(true)
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            skipHiddenState = false
        )
    )

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
                StopBottomSheet(
                    totalStops = if(!isLoading) 34 else null,
                    onBackPressed = onBackPressed
                )
            }
        },
        sheetShadowElevation = 8.dp
    ) {
        if(!isLoading) {
            MapStopScreenContent(
                stopList = stopList,
                onClickMarker = { stop: Pair<RouteInformation, LatLng> ->
                    if(stopSelected != stop.first) {
                        markerPressed(stop.first)
                    } else {
                        markerPressed(null)
                    }
                    true
                },
                modifier = modifier
            )
        } else {
            ShimmerMapStopScreenContent()
        }
    }
}

@Composable
fun ShimmerMapStopScreenContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            Spacer(Modifier.size(16.dp))
            Text(text = stringResource(R.string.lblLoadingMap))
        }
    }
}

@Composable
fun MapStopScreenContent(
    modifier: Modifier = Modifier,
    stopList: List<Pair<RouteInformation, LatLng>>,
    onClickMarker: (Pair<RouteInformation, LatLng>) -> Boolean
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(20.126856880277188, -101.19127471960047), 17f) // San Francisco como posición inicial
    }

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
                    onClickMarker(stop)
                }
            )
        }
    }
}
