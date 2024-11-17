package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.data.repository.RouteWithPlaces
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.maps.components.RouteBottomSheet
import net.streamroutes.sreamroutesapp.features.maps.components.RouteDetails
import net.streamroutes.sreamroutesapp.features.maps.components.ShimmerRouteDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapRouteScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onShareLocation: () -> Unit,
    transportViewModel: TransportViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    val selectedRoute by transportViewModel.selectedRoute.collectAsState()

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )

    val showBottomSheet = {
        scope.launch {
            scaffoldState.bottomSheetState.expand()
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            selectedRoute?.route?.name?.let {
                RouteBottomSheet(
                    onBackPressed = onBackPressed,
                    onShareLocation = onShareLocation,
                    name = it,
                    transportViewModel = transportViewModel,
                )
            }
        },
        sheetShadowElevation = 8.dp
        // CAMBIAR EL MAPA, CALLES BLANCAS
        // CONSTRUCCIONES GRIS PARA QUE SE VEA
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ){
            if(selectedRoute != null) {
                selectedRoute?.let {
                    showBottomSheet()
                    MapRouteScreenContent(
                        selectedRoute = it,
                        transportViewModel
                    )
                }
            } else {
                ShimmerRouteScreenContent()
            }
        }
    }
}



@Composable
fun ShimmerRouteScreenContent() {
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

    ShimmerRouteDetails()
}

@Composable
fun MapRouteScreenContent(
    selectedRoute: RouteWithPlaces,
    transportViewModel: TransportViewModel
) {

    val startRoute = LatLng(selectedRoute.startPlace.latitude.toDouble(), selectedRoute.startPlace.longitude.toDouble())
    val endRoute = LatLng(selectedRoute.endPlace.latitude.toDouble(), selectedRoute.endPlace.longitude.toDouble())

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            //LatLng(21.018189012668753, -101.26659563575932)
            startRoute,
            17f
        ) // San Francisco como posición inicial
    }

    LaunchedEffect(selectedRoute) {
        transportViewModel.getOrsRoute("driving-car", startRoute, endRoute)
    }

    val orsRoute by transportViewModel.orsRoute.collectAsState()


    MapFullSize(
        cameraPositionState = cameraPositionState,
        onMapClick = { },
        onMapLoaded = {

        }
    ) {
        Marker(
            state = MarkerState(
                startRoute
            )
        )
        Marker(
            state = MarkerState(
                endRoute
            )
        )
        if (orsRoute.isNotEmpty()) {
            Polyline(
                points = orsRoute,
                color = Color.Black,
                width = 8f
            )
        }



        // COLOCAR LAS POLILINEAS Y MARCADORES NECESARIOS
    }

    RouteDetails(currentStreet = selectedRoute.startPlace.name, transportViewModel = transportViewModel)
}


