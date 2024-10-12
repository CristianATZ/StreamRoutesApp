package net.streamroutes.sreamroutesapp.features.maps.presentation.planner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose.RumappAppTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.Destinations
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.maps.components.PlannerFloatingButtons
import net.streamroutes.sreamroutesapp.features.maps.components.PlannerInfoWindow
import net.streamroutes.sreamroutesapp.features.maps.components.PlannerMenu
import net.streamroutes.sreamroutesapp.features.maps.components.PlannerModalBottomSheet
import net.streamroutes.sreamroutesapp.utils.ListUtils.moveItemDown
import net.streamroutes.sreamroutesapp.utils.ListUtils.moveItemUp
import net.streamroutes.sreamroutesapp.utils.ListUtils.removeItem
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannerScreen(
    modifier: Modifier = Modifier
) {
    val coroutine = rememberCoroutineScope()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(20.126856880277188, -101.19127471960047), 17f) // San Francisco como posición inicial
    }
    // ACTUALIZAR LA POSICION DEL MARCADOR A TU UBICACION ACTUAL
    // PARA QUE FUNCIONE EL onMapClick
    val markerState = rememberMarkerState(
        position = LatLng(20.126856880277188, -101.19127471960047)
    )
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var markerVisible by remember {
        mutableStateOf(false)
    }
    var isOpen by remember {
        mutableStateOf(false)
    }
    val destinationsList = remember { mutableStateListOf<Destinations>() }

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
    // ACTUALIZAR EL MARCADOR PARA QUE MUESTRE AL PRIMER TOQUE
    val onMapClick = { coord: LatLng ->
        coroutine.launch {
            markerState.position = coord
            markerVisible = true
            delay(100)
            updateCameraPosition(coord)
            markerState.showInfoWindow()
        }
    }
    // ABRIR MODALBOTTOm
    val openBottomSheet = {
        isOpen = !isOpen
    }
    // AGREGAR DESTINO A LA LISTA
    val onAdd = {
        if(markerVisible) {
            destinationsList.add(
                Destinations(
                    coords = markerState.position,
                    address = "Padre Luis Gaytan #${Random.nextInt(1,500)}"
                )
            )
            markerVisible = false
        }
    }
    val onCalculateRoute = {
        // CALCULAR RUTA
    }
    val onMyLocation = {
        // CAMBIAR CAMARA A MI UBICACION ACTUAL
        //updateCameraPosition() 
    }
    val moveItemUp = { index: Int ->
        moveItemUp(destinationsList, index)
    }
    val moveItemDown = { index: Int ->
        moveItemDown(destinationsList, index)
    }
    val removeItem = { index: Int ->
        removeItem(destinationsList, index)
    }

    // HACER LISTA DE DESITNO
    // PROGRAMAR VIEW DEL PUNTO SELECCIONADO
    // FUNCION PARA AGREGAR A LA LISTA

    if(isOpen) {
        PlannerModalBottomSheet(
            destinationsList = destinationsList,
            sheetState = sheetState,
            onDismiss = openBottomSheet,
            onCalculateRoute = onCalculateRoute,
            onMoveItemUp = { index ->
                moveItemUp(index)
            },
            onMoveItemDown = { index ->
                moveItemDown(index)
            },
            onRemoveItem = { index ->
                removeItem(index)
            }
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        MapFullSize(
            cameraPositionState = cameraPositionState,
            onMapClick = { coord ->
                onMapClick(coord)
            },
            onMapLoaded = {

            }
        ) {
            MarkerInfoWindow(
                state = markerState,
                visible = markerVisible
            ) {
                PlannerInfoWindow()
            }
        }

        PlannerMenu(
            modifier = Modifier.align(Alignment.TopCenter)
        )

        PlannerFloatingButtons(
            isVisible = markerVisible,
            onMyLocation = {
                updateCameraPosition(it)
            },
            onOpenList = openBottomSheet,
            onAddItem = {
                onAdd()
            },
            onCancelItem = {
                markerVisible = false
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
