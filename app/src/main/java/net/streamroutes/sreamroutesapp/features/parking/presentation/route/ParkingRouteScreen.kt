package net.streamroutes.sreamroutesapp.features.parking.presentation.route

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.components.ParkingDescription
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel
import net.streamroutes.sreamroutesapp.features.parking.components.InformationChip
import net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home.ParkingViewModel
import net.streamroutes.sreamroutesapp.features.settings.presentation.maps.MapSettingsViewModel
import net.streamroutes.sreamroutesapp.utils.shimmerEffect

@Composable
fun ParkingRouteScreen(
    onBackPressed: () -> Unit = {},
    onScanPressed: () -> Unit = {},
    parkingViewModel: ParkingViewModel = hiltViewModel(),
    transportViewModel: TransportViewModel = hiltViewModel(),
    mapSettingsViewModel: MapSettingsViewModel = hiltViewModel()
) {
    val routeColor by mapSettingsViewModel.routeColor.collectAsState()
    val lineSize by mapSettingsViewModel.lineSize.collectAsState()

    val coroutine = rememberCoroutineScope()
    val selectedParking by parkingViewModel.selectedParking.collectAsState()
    val orsRoute by transportViewModel.orsRoute.collectAsState()
    val routeData by transportViewModel.orsRouteData.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(21.017917732561727, -101.25808073954296), 17f) // San Francisco como posición inicial
    }

    val markerStart = rememberMarkerState(
        position = LatLng(21.017917732561727, -101.25808073954296)
    )

    val markerEnd = rememberMarkerState(
        position = LatLng(
            selectedParking?.place?.latitude?.toDouble() ?: 0.0,
            selectedParking?.place?.longitude?.toDouble() ?: 0.0
        )
    )

    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        coroutine.launch {
            transportViewModel.getOrsRoute("driving-car", markerStart.position, markerEnd.position)
            isLoading = false
        }
    }

    
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if(!isLoading) {
                MapFullSize(
                    cameraPositionState = cameraPositionState,
                    onMapClick = {

                    },
                    onMapLoaded = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f)
                        .align(Alignment.TopCenter)
                ) {
                    // mandar polilineas
                    Marker(
                        state = MarkerState(
                            markerStart.position
                        )
                    )
                    Marker(
                        state = MarkerState(
                            markerEnd.position
                        )
                    )
                    if(orsRoute.isNotEmpty()){
                        Polyline(
                            points = orsRoute,
                            color = Color(routeColor),
                            width = lineSize.toFloat()
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(Modifier.size(16.dp))
                    Text(text = stringResource(R.string.lblLoadingMap))
                }
            }

            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.size(8.dp))

                IconButton(
                    onClick = onBackPressed,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = colorScheme.background,
                        contentColor = colorScheme.onBackground
                    )
                ) {
                    Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = null)
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .background(Color.Black.copy(0.5f), shapes.extraLarge),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = selectedParking?.place?.name ?: "cargando..",
                        style = typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f)
                    .align(Alignment.BottomCenter)
                    .background(colorScheme.background, RoundedCornerShape(topEnd = 28.dp, topStart = 28.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val address = "${selectedParking?.place?.street ?: "SN"}, ${selectedParking?.place?.suburb ?: "SN"}, ${selectedParking?.place?.state ?: "SN"}"

                    ParkingDescription(
                        name = selectedParking?.place?.name ?: "cargando..",
                        address = address,
                        price = null
                    )

                    if(!isLoading) {
                        Text(
                            text = stringResource(R.string.lblSchedule, selectedParking?.parking?.openHour ?: "cargando...", selectedParking?.parking?.closeHour ?: "cargando..."),
                            style = typography.labelLarge,
                            modifier = Modifier.padding(horizontal = 16.dp).graphicsLayer(alpha = 0.5f)
                        )
                    } else {
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .height(25.dp)
                                .fillMaxWidth(0.5f)
                                .clip(shapes.small)
                                .shimmerEffect()
                        )
                    }

                    Spacer(Modifier.size(8.dp))

                    if(!isLoading) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            // distancia faltante
                            InformationChip(
                                text = stringResource(R.string.lblDistanceLeft, "${routeData?.get("distance")} km")
                            )

                            // tiempo faltante
                            val duration = routeData?.get("duration").toString().split('.')[0]
                            InformationChip(
                                text = stringResource(R.string.lblTimeAprox, "${duration} minutos")
                            )

                            // precio
                            selectedParking?.parking?.feePerHour?.let { stringResource(R.string.lblPriceFull, it) }
                                ?.let {
                                    InformationChip(
                                        text = it
                                    )
                                }
                        }
                    } else {
                        Row {
                            repeat(3) {
                                Spacer(
                                    modifier = Modifier
                                        .padding(start = 16.dp)
                                        .width(100.dp)
                                        .height(40.dp)
                                        .clip(shapes.small)
                                        .shimmerEffect()
                                )
                            }
                        }
                    }

                    Spacer(Modifier.size(8.dp))

                    if(!isLoading) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = colorScheme.tertiaryContainer,
                                contentColor = colorScheme.onTertiaryContainer
                            ),
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                val available = (selectedParking?.parking?.maxCapacity ?: 0) - (selectedParking?.parking?.currentEntrances ?: 0)

                                Text(
                                    text = available.toString(),
                                    style = typography.displayMedium
                                )

                                Spacer(modifier = Modifier.size(8.dp))

                                Text(
                                    text = stringResource(R.string.lblAvailableSpaces),
                                    style = typography.bodyLarge
                                )
                            }
                        }
                    } else {
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .height(50.dp)
                                .clip(shapes.small)
                                .shimmerEffect()
                        )
                    }

                    Spacer(Modifier.weight(1f))
                    
                    // escanear codigo qr
                    Button(
                        onClick = onScanPressed,
                        shape = shapes.small,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(text = stringResource(R.string.btnScanCode))
                    }

                    Spacer(Modifier.size(16.dp))
                }
            }
        }
    }
}