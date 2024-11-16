package net.streamroutes.sreamroutesapp.features.maps.presentation.planner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.Destinations
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.maps.components.PlannerFloatingButtons
import net.streamroutes.sreamroutesapp.features.maps.components.PlannerInfoWindow
import net.streamroutes.sreamroutesapp.features.maps.components.CardCurrentLocationWithIcon
import net.streamroutes.sreamroutesapp.features.maps.components.PlannerModalBottomSheet
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel
import net.streamroutes.sreamroutesapp.utils.ListUtils.moveItemDown
import net.streamroutes.sreamroutesapp.utils.ListUtils.moveItemUp
import net.streamroutes.sreamroutesapp.utils.ListUtils.removeItem
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannerScreen(
    modifier: Modifier = Modifier,
    transportViewModel: TransportViewModel = hiltViewModel()
) {
    //val markerAddress by transportViewModel.markerAdress.collectAsState()

    val coroutine = rememberCoroutineScope()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(21.018541825334918, -101.25880481892467), 17f) // San Francisco como posición inicial
    }
    // ACTUALIZAR LA POSICION DEL MARCADOR A TU UBICACION ACTUAL
    // PARA QUE FUNCIONE EL onMapClick
    val markerState = rememberMarkerState(
        position = LatLng(21.018541825334918, -101.25880481892467)
    )
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var address by remember { mutableStateOf("") }

    var markerVisible by remember {
        mutableStateOf(false)
    }
    var isOpen by remember {
        mutableStateOf(false)
    }
    var isCalculated by remember {
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
            address = transportViewModel.getAddress(markerState.position).toString()
            markerVisible = true
            //delay(2000)
            updateCameraPosition(coord)
            delay(100)
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
                    address = address
                )
            )
            markerVisible = false
        }
    }
    val onCalculateRoute = {
        // CALCULAR RUTA
        transportViewModel.planRoute(destinationsList.map { it.coords })
        isCalculated = true
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
        val orsRoute by transportViewModel.orsRoute.collectAsState()
        MapFullSize(
            cameraPositionState = cameraPositionState,
            onMapClick = { coord ->
                onMapClick(coord)
            },
            onMapLoaded = {

            },
            modifier = Modifier.fillMaxSize()
        ) {
            if (markerVisible) {
                MarkerInfoWindow(
                    state = markerState,
                    visible = markerVisible
                ) {
                    PlannerInfoWindow(address = address)
                }
            }
            if(isCalculated && orsRoute.isNotEmpty()){
                Polyline(
                    points = orsRoute,
                    color = Color.Black,
                    width = 8f
                )
            }

        }

        Column {
            Spacer(modifier = Modifier.size(16.dp))

            CardCurrentLocationWithIcon(
                icon = Icons.Outlined.MyLocation,
                iconDescription = stringResource(id = R.string.iconMyLocation),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

        }

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
