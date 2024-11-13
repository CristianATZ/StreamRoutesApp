package net.streamroutes.sreamroutesapp.features.turism.presentation.turismRoute

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.maps.components.RouteBottomSheet
import net.streamroutes.sreamroutesapp.features.maps.components.RouteDetails
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.ShimmerRouteScreenContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TurismRouteScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onShareLocation: () -> Unit
) {
    // ELIMINAR ESTA VARIABLE CUANDO CARGUES LA INFORMACION
    // GUIATE CON MapRouteScreen.kt
    var isLoading by remember {
        mutableStateOf(true)
    }

    val scope = rememberCoroutineScope()

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
            RouteBottomSheet(
                onBackPressed = onBackPressed,
                onShareLocation = onShareLocation,
                selectedRoute = null
            )
        },
        sheetShadowElevation = 8.dp
        // CAMBIAR EL MAPA, CALLES BLANCAS
        // CONSTRUCCIONES GRIS PARA QUE SE VEA
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ){
            if(!isLoading) {
                showBottomSheet()
                TourismRouteScreenContent()
            } else {
                ShimmerRouteScreenContent()
            }
        }
    }
}

@Composable
fun TourismRouteScreenContent() {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(20.126856880277188, -101.19127471960047), 17f) // San Francisco como posición inicial
    }

    MapFullSize(
        cameraPositionState = cameraPositionState,
        onMapClick = { },
        onMapLoaded = {

        }
    ) {
        // COLOCAR LAS POLILINEAS Y MARCADORES NECESARIOS
    }

    RouteDetails()
}
