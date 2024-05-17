package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.viewmodel.routes.FastViewModel
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.ui.start_screens.CustomOutlinedTextField


@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun FastScreen(
    fastViewModel: FastViewModel,
    onBack: () -> Unit
) {
    /*scope.launch(Dispatchers.IO) {
        val priority = if (true) {
            Priority.PRIORITY_HIGH_ACCURACY
        } else {
            Priority.PRIORITY_BALANCED_POWER_ACCURACY
        }
        val result = locationClient.getCurrentLocation(
            priority,
            CancellationTokenSource().token,
        ).await()
        result?.let { fetchedLocation ->
            locationInfo =
                "Current location is \n" + "lat : ${fetchedLocation.latitude}\n" +
                        "long : ${fetchedLocation.longitude}\n" + "fetched at ${System.currentTimeMillis()}"
            Log.d("GIVOX", locationInfo)
            cameraState.geoPoint = GeoPoint(fetchedLocation.latitude, fetchedLocation.longitude)
            cameraState.zoom = 17.0

        }
    }*/

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
            MapBodyFast(fastViewModel){ it ->
                fastViewModel.updateSelectedLocation(com.google.maps.model.LatLng(it.longitude, it.longitude))
            }

            CalcularDestino()
        }
    }
}

@Composable
fun CalcularDestino() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                //navController.navigate(AppScreens.SelectOptionScreen.route)
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
fun MapBodyFast(fastViewModel: FastViewModel, onMapClick: (LatLng) -> Unit) {
    val itsur = LatLng(20.139539228288044, -101.15073143400946)
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(itsur, 15f)
    }

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
        onMapClick = {
            onMapClick(it)
        },
        modifier = Modifier
            .fillMaxSize()
    ) {
        fastViewModel.selectedLocation.let {
            Marker(
                state = MarkerState(
                    position = LatLng(it.lat,it.lng)
                )
            )
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
                onValueChange = {destino = it},
                placeholderText = stringResource(id = R.string.txtDestino),
                leadingIcon = Icons.Filled.Search,
                ancho = 0.9f
            )
        }
    }
}
