package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.maps.components.RouteBottomSheet
import net.streamroutes.sreamroutesapp.features.maps.components.RouteDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapRouteScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onShareLocation: () -> Unit
) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
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

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            RouteBottomSheet(
                onBackPressed = onBackPressed,
                onShareLocation = onShareLocation
            )
        },
        sheetShadowElevation = 8.dp
        // CAMBIAR EL MAPA, CALLES BLANCAS
        // CONSTRUCCIONES GRIS PARA QUE SE VEA
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ){
            MapFullSize(
                cameraPositionState = cameraPositionState,
                onMapClick = { },
                onMapLoaded = { /*TODO*/ }
            ) {
                // COLOCAR LAS POLILINEAS Y MARCADORES NECESARIOS
            }

            RouteDetails(
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}