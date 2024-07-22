package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.annotation.DrawableRes
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.OrsState
import net.streamroutes.sreamroutesapp.viewmodel.OrsViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ViajePkViewModel

@Composable
fun IniciarViajeScreen(
    viajePkViewModel: ViajePkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    orsViewModel: OrsViewModel,
    navHostController: NavHostController
) {
    IniciarViaje(viajePkViewModel, parkingPkViewModel, orsViewModel, navHostController)
}

@androidx.annotation.OptIn(ExperimentalGetImage::class)
@Composable
private fun IniciarViaje(
    viajePkViewModel: ViajePkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    orsViewModel: OrsViewModel,
    navHostController: NavHostController
) {
    val uiState by viajePkViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // mapa
        AnimatedVisibility(
            visible = !uiState.leerQR,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            // mapa
            MapaRecorrido(viajePkViewModel, orsViewModel)

            // header con informacion
            HeaderIniciarViaje(viajePkViewModel, orsViewModel, navHostController)

            // botones llegue o regresar
            if (uiState.vehiculoSeleccionado != null) {
                ViajeFinalizado(viajePkViewModel, orsViewModel)
            } else {
                LugaresTuristicos()
            }
        }

        AnimatedVisibility(
            visible = uiState.leerQR,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            QRScreen(viajePkViewModel, parkingPkViewModel, navHostController)
        }
    }
}

@Composable
fun LugaresTuristicos() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .background(colorScheme.background, RoundedCornerShape(8.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.lblLugaresTuristicos),
                    style = typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    LugarTuristicoItem(
                        img = R.drawable.lugar_minas,
                        name = "Minas"
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    LugarTuristicoItem(
                        img = R.drawable.lugar_callejon_beso,
                        name = "Callejón del Beso"
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    LugarTuristicoItem(
                        img = R.drawable.lugar_alhondiga,
                        name = "Alhóndiga de Granaditas"
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    LugarTuristicoItem(
                        img = R.drawable.lugar_teatro_juarez,
                        name = "Teatro Juárez"
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun LugarTuristicoItem(
    @DrawableRes img: Int,
    name: String
) {
    Box(
        modifier = Modifier
            .size(150.dp),
        contentAlignment = Alignment.BottomStart
    ){
        Image(
            painter = painterResource(id = img),
            contentDescription = name,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = name,
            style = typography.bodyLarge,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}


@Composable
private fun MapaRecorrido(
    viajePkViewModel: ViajePkViewModel,
    orsViewModel: OrsViewModel
) {
    val viajeState by viajePkViewModel.uiState.collectAsState()
    val orsState by orsViewModel.uiState.collectAsState()

    val estacionamiento = remember(viajeState.estacionamientoSeleccionado!!.location) {
        LatLng(viajeState.estacionamientoSeleccionado!!.location.latitude, viajeState.estacionamientoSeleccionado!!.location.longitude)
    }
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(estacionamiento, 15f)
    }
    val ubicacion = remember { LatLng(20.141173538431637, -101.15040622126999) }

    var mapLoaded by remember { mutableStateOf(false) }

    if (mapLoaded) {
        LaunchedEffect(ubicacion, estacionamiento) {
            orsViewModel.fetchRouteInfo("${ubicacion.longitude},${ubicacion.latitude}", "${estacionamiento.longitude},${estacionamiento.latitude}")
        }
    }

    GoogleMap(
        cameraPositionState = cameraPosition,
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions(stringResource(id = R.string.mapStyleLight)),
            maxZoomPreference = 18f,
            minZoomPreference = 15f
        ),
        modifier = Modifier.fillMaxSize(),
        onMapLoaded = { mapLoaded = true }
    ) {

        if(mapLoaded){
            Marker(
                state = MarkerState(position = estacionamiento),
                title = "Estacionamiento",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_parking)
            )

            if(viajeState.vehiculoSeleccionado != null){
                Marker(
                    state = MarkerState(position = ubicacion),
                    title = "Aquí estás",
                    icon = BitmapDescriptorFactory.fromResource(R.drawable.coche_izq)
                )

                if(orsState.state == OrsState.SUCCESSFUL){
                    Polyline(
                        points = orsState.geometry!!.coordinates.map { LatLng(it.last(), it.first()) },
                        color = Color.Black,
                        jointType = JointType.ROUND,
                        startCap = RoundCap(),
                        endCap = RoundCap()
                    )
                }
            }
        }
    }
}

@Composable
private fun ViajeFinalizado(
    viajePkViewModel: ViajePkViewModel,
    orsViewModel: OrsViewModel
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                scope.launch {
                    viajePkViewModel.updateLeerQR(true)
                    orsViewModel.clear()
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = colorScheme.tertiary,
                contentColor = colorScheme.onTertiary,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(50.dp)
        ) {
            Text(text = stringResource(id = R.string.btnLlegue), style = typography.titleMedium, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
private fun HeaderIniciarViaje(
    viajePkViewModel: ViajePkViewModel,
    orsViewModel: OrsViewModel,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorScheme.primary,
                RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ParkingInfo(viajePkViewModel, orsViewModel, navHostController)
    }
}

@Composable
fun ParkingInfo(
    viajePkViewModel: ViajePkViewModel,
    orsViewModel: OrsViewModel,
    navHostController: NavHostController
) {
    val viajeState by viajePkViewModel.uiState.collectAsState()
    val orsState by orsViewModel.uiState.collectAsState()

    val time = orsState.properties?.summary?.duration ?: 0.0


    val infoTime = "${if (time % 60 != 0.0) (time / 60).toInt() + 1 else (time / 60).toInt()} minuto(s)."

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Spacer(modifier = Modifier.size(16.dp))

        IconButton(
            onClick = {
                navHostController.popBackStack()
                orsViewModel.clear()
            },
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                tint = colorScheme.onPrimary,
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.size(32.dp))

        if(time == 0.0){
            Spacer(modifier = Modifier.size(32.dp))
            CircularProgressIndicator(color = colorScheme.onPrimary)
        } else {
            Column {
                Text(
                    text = viajeState.estacionamientoSeleccionado!!.name,
                    color = colorScheme.onPrimary,
                    style = typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = viajeState.estacionamientoSeleccionado!!.address,
                    color = colorScheme.onPrimary,
                    style = typography.titleMedium,
                    fontWeight = FontWeight.Normal
                )

                if(viajeState.vehiculoSeleccionado != null){
                    Spacer(modifier = Modifier.size(16.dp))

                    Text(
                        text = infoTime,
                        color = colorScheme.onPrimary,
                        style = typography.titleMedium,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}