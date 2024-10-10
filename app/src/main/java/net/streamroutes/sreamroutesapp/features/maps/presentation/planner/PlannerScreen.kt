package net.streamroutes.sreamroutesapp.features.maps.presentation.planner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.maps.components.PlannerFloatingButtons
import net.streamroutes.sreamroutesapp.features.maps.components.PlannerMenu
import net.streamroutes.sreamroutesapp.features.maps.components.PlannerModalBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannerScreen(
    modifier: Modifier = Modifier
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(20.126856880277188, -101.19127471960047), 17f) // San Francisco como posición inicial
    }

    // MOVER SIN AFECTAR EL ZOOM
    val updateCameraPosition = {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLng(
                LatLng(37.7749, -122.4194)
            )
        )
    }

    // MOVER AFECTANDO EL ZOOM
    /*val updateCameraZoom = {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(37.7749, -122.4194), 10f
            )
        )
    }*/

    var isOpen by remember {
        mutableStateOf(false)
    }

    val openBottomSheet = {
        isOpen = !isOpen
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val onCalculateRoute = {
        // CALCULAR RUTA
    }

    // HACER LISTA DE DESITNO
    // PROGRAMAR VIEW DEL PUNTO SELECCIONADO
    // FUNCION PARA AGREGAR A LA LISTA

    if(isOpen) {
        PlannerModalBottomSheet(
            sheetState = sheetState,
            onDismiss = openBottomSheet,
            onCalculateRoute = onCalculateRoute
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        MapFullSize(
            cameraPositionState = cameraPositionState,
            onMapClick = {

            },
            onMapLoaded = {

            }
        ) {

        }

        PlannerMenu(
            modifier = Modifier.align(Alignment.TopCenter)
        )

        PlannerFloatingButtons(
            onMyLocation = updateCameraPosition,
            onOpenList = openBottomSheet,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
