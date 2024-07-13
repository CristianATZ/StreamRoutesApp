package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import FastViewModel
import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.ui.start_screens.CustomOutlinedTextField
import net.streamroutes.sreamroutesapp.viewmodel.OrsState
import net.streamroutes.sreamroutesapp.viewmodel.OrsViewModel

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun FastScreen(
    fastViewModel: FastViewModel,
    orsViewModel: OrsViewModel,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(true) {
        systemUiController.setNavigationBarColor(Color.Black)
        systemUiController.setStatusBarColor(Color(0xFFFEFBFF))
    }

    Scaffold(
        topBar = { TopBar(onBack) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            MapBodyFast(fastViewModel, orsViewModel) { latLng ->
                fastViewModel.updateSelectedLocation(com.google.maps.model.LatLng(latLng.latitude, latLng.longitude))
            }

            CalcularDestino(fastViewModel, orsViewModel)
        }
    }
}

//Falla
@Composable
fun CalcularDestino(
    fastViewModel: FastViewModel,
    orsViewModel: OrsViewModel
) {

    val fastState by fastViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                val currentLocation = fastState.currentLocation
                val selectedLocation = fastState.selectedLocation
                orsViewModel.fetchRouteInfo(
                    "${currentLocation.lng},${currentLocation.lat}",
                    "${selectedLocation.lng},${selectedLocation.lat}"
                )
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.tertiaryContainer,
                contentColor = colorScheme.onTertiaryContainer
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 16.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btnCalcularDestino),
                style = typography.bodyLarge
            )
        }

    }
}

@Composable
fun MapBodyFast(
    fastViewModel: FastViewModel,
    orsViewModel: OrsViewModel,
    onMapClick: (LatLng) -> Unit
) {
    val itsur = LatLng(20.139539228288044, -101.15073143400946)
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(itsur, 15f)
    }

    val orsUiState by orsViewModel.uiState.collectAsState()
    val fastState by fastViewModel.uiState.collectAsState()

    GoogleMap(
        cameraPositionState = cameraPosition,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
        ),
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions(stringResource(id = R.string.mapStyleLight)),
            maxZoomPreference = 18f,
            minZoomPreference = 15f
        ),
        onMapClick = { onMapClick(it) },
        modifier = Modifier
            .fillMaxSize()
    ) {
        val selectedLocation = fastState.selectedLocation

        Marker(
            state = MarkerState(
                position = LatLng(selectedLocation.lat, selectedLocation.lng)
            )
        )

        orsUiState.geometry?.coordinates?.let { coordinates ->
            Polyline(
                points = coordinates.map { LatLng(it[1], it[0]) },
                color = Color.Blue,
                width = 5f
            )
        }

        // Maneja el estado de la solicitud y muestra mensajes según sea necesario
        when (orsUiState.state) {
            OrsState.LOADING -> {
                // Mostrar indicador de carga
            }
            OrsState.SUCCESSFUL -> {
                // Mostrar ruta en el mapa (ya lo haces con la Polyline)
            }
            OrsState.FAILURE -> {
                // Mostrar mensaje de error
                Text(text = orsUiState.message, color = Color.Red, modifier = Modifier.padding(16.dp))
            }
            else -> {
                // No hacer nada
            }
        }
    }
}

@Composable
private fun TopBar(
    onBack: () -> Unit
) {
    var destino by remember {
        mutableStateOf("")
    }

    Row {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Te enviara al menu de opciones",
                )
            }

            CustomOutlinedTextField(
                value = destino,
                onValueChange = { destino = it },
                placeholderText = stringResource(id = R.string.txtDestino),
                leadingIcon = Icons.Filled.Search,
                ancho = 0.9f
            )
        }
    }
}
