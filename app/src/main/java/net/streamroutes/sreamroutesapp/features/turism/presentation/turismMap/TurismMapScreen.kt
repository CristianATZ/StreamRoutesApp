package net.streamroutes.sreamroutesapp.features.turism.presentation.turismMap

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.TuristicPointWithInfo
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.turism.components.TurismBottomSheet
import net.streamroutes.sreamroutesapp.features.turism.components.TurismInformationBottomSheet
import net.streamroutes.sreamroutesapp.features.turism.presentation.turismList.TourismViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TurismMapScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    turismViewModel: TourismViewModel = hiltViewModel()
) {
    val turisticPoints by turismViewModel.turisticPoints.collectAsState()
    val scope = rememberCoroutineScope()


    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        scope.launch {
            turismViewModel.getAllTuristicPoints()
            isLoading = false
        }
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            skipHiddenState = false
        )
    )


    var turismSelected by remember {
        mutableStateOf<TuristicPointWithInfo?>(null)
    }

    val updateTurism = { turism: TuristicPointWithInfo? ->
        turismSelected = turism
    }

    val markerPressed = { turism: TuristicPointWithInfo? ->
        scope.launch {
            scaffoldState.bottomSheetState.hide()// esconder la hoja
            delay(300) // esperar 300 milisegundos
            updateTurism(turism) // actualizar valor junto con la animacion
            scaffoldState.bottomSheetState.expand() // expandir la hoja
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            if(turismSelected != null) {
                turismSelected?.let {
                    TurismInformationBottomSheet(
                        turisticPoint = it,
                        onClose = {
                            markerPressed(null)
                        },
                        onMore = {

                        }
                    )
                }
            } else {
                TurismBottomSheet(
                    onBackPressed = onBackPressed,
                    totalTurism = if(!isLoading) turisticPoints?.size else null
                )
            }
        },
        sheetShadowElevation = 8.dp
    ) {
        if(!isLoading) {
            TourismMapScreenContent(
                turisticPoints = turisticPoints,
                turismSelected = turismSelected,
                markerPressed = { turism: TuristicPointWithInfo? ->
                    markerPressed(turism)
                },
                modifier = modifier
            )
        } else {
            ShimmerTourismMapScreenContent()
        }
    }
}

@Composable
fun ShimmerTourismMapScreenContent() {
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
fun TourismMapScreenContent(
    modifier: Modifier = Modifier,
    turisticPoints: List<TuristicPointWithInfo>?,
    turismSelected: TuristicPointWithInfo?,
    markerPressed: (TuristicPointWithInfo?) -> Job
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(21.024836955368098, -101.25738698049604), 17f) // San Francisco como posición inicial
    }

    MapFullSize(
        cameraPositionState = cameraPositionState,
        onMapClick = {

        },
        onMapLoaded = { /*TODO*/ },
        modifier = modifier
    ) {
        turisticPoints?.forEach { tp ->
            Marker(
                state = MarkerState(
                    position = LatLng(tp.place.latitude.toDouble(), tp.place.longitude.toDouble())
                ),
                onClick = { _ ->
                    if(turismSelected != tp) {
                        markerPressed(tp)
                    } else {
                        markerPressed(null)
                    }
                    true
                }
            )
        }
    }
}
