package net.streamroutes.sreamroutesapp.features.parks.presentation.parks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.components.ParkingDescription
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.ShimmerTransportScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel
import net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home.ParkingViewModel
import net.streamroutes.sreamroutesapp.features.settings.presentation.maps.MapSettingsViewModel

@Composable
fun ParkRouteScreen(
    onBackPressed: () -> Unit,
    parkingViewModel: ParkingViewModel,
    transportViewModel: TransportViewModel = hiltViewModel(),
    mapSettingsViewModel: MapSettingsViewModel = hiltViewModel(),
) {

    val routeColor by mapSettingsViewModel.routeColor.collectAsState()
    val lineSize by mapSettingsViewModel.lineSize.collectAsState()

    val selectedReservation by parkingViewModel.selectedReservation.collectAsState()
    val orsRoute by transportViewModel.orsRoute.collectAsState()
    val routeData by transportViewModel.orsRouteData.collectAsState()

    val start = LatLng(21.017917732561727, -101.25808073954296)
    val end = LatLng(selectedReservation?.place?.latitude?.toDouble() ?: 0.0, selectedReservation?.place?.longitude?.toDouble() ?: 0.0)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(21.017917732561727, -101.25808073954296), 17f) // San Francisco como posición inicial
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit){
        transportViewModel.getOrsRoute("driving-car", start, end)
        isLoading = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if(!isLoading){
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
                        LatLng(start.latitude, start.longitude)
                    )
                )

                Marker(
                    state = MarkerState(
                        LatLng(end.latitude, start.longitude)
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
                    val address = "${selectedReservation?.place?.street}, ${selectedReservation?.place?.suburb}, ${selectedReservation?.place?.state}"
                    ParkingDescription(
                        name = selectedReservation?.place?.name ?: stringResource(R.string.lblLoading),
                        address = address,
                        price = null
                    )

                    Text(
                        text = stringResource(R.string.lblSchedule,
                            selectedReservation?.parking?.openHour ?: stringResource(R.string.lblLoading),
                            selectedReservation?.parking?.closeHour ?: stringResource(R.string.lblLoading)
                        ),
                        style = typography.labelLarge,
                        modifier = Modifier.padding(horizontal = 16.dp).graphicsLayer(alpha = 0.5f)
                    )

                    Spacer(Modifier.size(8.dp))

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
                            Text(
                                text = stringResource(R.string.lblMetersLeft, "${routeData?.get("distance")}"),
                                style = typography.displayMedium
                            )

                            Spacer(modifier = Modifier.size(8.dp))

                            Text(
                                text = stringResource(id = R.string.lblTimeTripAprox, "${routeData?.get("duration")}"),
                                style = typography.bodyLarge
                            )
                        }
                    }

                    Spacer(Modifier.weight(1f))

                    // escanear codigo qr
                    Button(
                        onClick = onBackPressed,
                        shape = shapes.small,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(text = stringResource(R.string.btnBack))
                    }

                    Spacer(Modifier.size(16.dp))
                }
            }
        }
    }
}