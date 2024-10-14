package net.streamroutes.sreamroutesapp.features.turism.presentation.turismMap

import androidx.compose.material.ExperimentalMaterialApi
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
import net.streamroutes.sreamroutesapp.core.domain.model.TurismInformation
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.turism.components.TurismBottomSheet
import net.streamroutes.sreamroutesapp.features.turism.components.TurismInformationBottomSheet

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun TurismMapScreen(
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

    val turismList = listOf(
        Pair(
            TurismInformation(
                name = "Alhondiga de granaditas",
                totalRoutes = 4,
                calendar = "08:00 - 16:00",
                price = "36 MXN",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim adLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim adLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad"
            ),
            LatLng(20.126856880277188, -101.19127471960047)
        )
    )

    var isMarkerPressed by remember {
        mutableStateOf(false)
    }

    val markerPressed = {
        scope.launch {
            scaffoldState.bottomSheetState.hide()// esconder la hoja
            delay(300) // esperar 300 milisegundos
            isMarkerPressed = !isMarkerPressed // cambiar el valor para cambiar el contenido de la hoja
            scaffoldState.bottomSheetState.expand() // expandir la hoja
        }
    }

    var turismSelected by remember {
        mutableStateOf<TurismInformation?>(null)
    }

    val updateTurism = { turism: TurismInformation ->
        turismSelected = turism
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            if(isMarkerPressed) {
                turismSelected?.let {
                    TurismInformationBottomSheet(
                        turismInformation = it,
                        onClose = {
                            markerPressed()
                        },
                        onMore = {

                        }
                    )
                }
            } else {
                TurismBottomSheet()
            }
        },
        sheetShadowElevation = 8.dp
    ) {
        MapFullSize(
            cameraPositionState = cameraPositionState,
            onMapClick = {

            },
            onMapLoaded = { /*TODO*/ },
            modifier = modifier
        ) {
            turismList.forEach { turism ->
                Marker(
                    state = MarkerState(position = turism.second),
                    onClick = { _ ->
                        updateTurism(
                            turism.first
                        )
                        markerPressed()
                        true
                    }
                )
            }
        }
    }
}