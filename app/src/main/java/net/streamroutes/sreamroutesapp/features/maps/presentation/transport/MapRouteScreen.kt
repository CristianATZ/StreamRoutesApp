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
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.maps.components.RouteBottomSheet
import net.streamroutes.sreamroutesapp.features.maps.components.RouteDetails
import net.streamroutes.sreamroutesapp.features.maps.components.ShimmerRouteDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapRouteScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onShareLocation: () -> Unit
) {
    val scope = rememberCoroutineScope()

    // CALCULAR LA INFORMACION PARA EN ROUTE DETAILS
    // INFORMACION DE LA RUTA
    // CAMBIAR EL ESTADO
    var isLoading by remember {
        mutableStateOf(false)
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Hidden, skipHiddenState = false)
    )

    val showBottomSheet = {
        scope.launch {
            scaffoldState.bottomSheetState.expand()
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(20.126856880277188, -101.19127471960047), 17f) // San Francisco como posición inicial
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            RouteBottomSheet(
                onBackPressed = onBackPressed,
                onShareLocation = onShareLocation,
                timeToStop = if(isLoading) null else "5 minutos"
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
                MapRouteScreenContent(
                    cameraPositionState = cameraPositionState,
                    onMapLoaded = {

                    }
                    // pasar en parametro la informacion de la ruta
                )
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
    cameraPositionState: CameraPositionState,
    onMapLoaded: () -> Unit
) {
    MapFullSize(
        cameraPositionState = cameraPositionState,
        onMapClick = { },
        onMapLoaded = onMapLoaded
    ) {
        // COLOCAR LAS POLILINEAS Y MARCADORES NECESARIOS
    }

    RouteDetails()
}


