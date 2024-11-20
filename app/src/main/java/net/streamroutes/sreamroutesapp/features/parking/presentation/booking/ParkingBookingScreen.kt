package net.streamroutes.sreamroutesapp.features.parking.presentation.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.ReservationParking
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.components.ParkingDescription
import net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home.ParkingViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class AlertsExpiration(
    var fifteen: Boolean = false,
    var thirteen: Boolean = false,
    var fortyFive: Boolean = false,
    var oneHour: Boolean = false,
    var twoHour: Boolean = false
)

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ParkingBookingScreen(
    onBackPressed: () -> Unit = {},
    parkingViewModel: ParkingViewModel
) {
    val selectedParking by parkingViewModel.selectedParking.collectAsState()
    val parkingLocation = LatLng(selectedParking?.place?.latitude?.toDouble() ?: 0.0, selectedParking?.place?.longitude?.toDouble() ?: 0.0)

    var isLoading by remember {
        mutableStateOf(true)
    }


    var cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(21.017917732561727, -101.25808073954296), 17f) // San Francisco como posición inicial
    }

    val sliderState = SliderState(
        value = 1f,
        steps = 10,
        valueRange = 1f..12f
    )

    LaunchedEffect(Unit) {
        isLoading = false
    }

    var alertsState by remember {
        mutableStateOf(AlertsExpiration())
    }

    var total = 0.0

    fun updateAlerState(x: AlertsExpiration) {
        alertsState = x
    }

    val onBookPressed = {

    }

    Scaffold(

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(parkingLocation.latitude, parkingLocation.longitude), 17f)
            }
            if(!isLoading) {
                MapFullSize(
                    cameraPositionState = cameraPositionState,
                    onMapClick = {

                    },
                    onMapLoaded = {

                    },
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.35f)
                ) {
                    Marker(
                        state = MarkerState(parkingLocation)
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.35f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(Modifier.size(16.dp))
                    Text(text = stringResource(R.string.lblLoadingMap))
                }
            }

            if(selectedParking != null) {

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
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .background(Color.Black.copy(0.5f), shapes.extraLarge),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "03:00",
                            style = typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                        .align(Alignment.BottomCenter)
                        .background(
                            colorScheme.background,
                            RoundedCornerShape(topEnd = 28.dp, topStart = 28.dp)
                        )

                ) {
                    Spacer(Modifier.size(16.dp))

                    val address =
                        "${selectedParking?.place?.street}, ${selectedParking?.place?.suburb}, ${selectedParking?.place?.state}"
                    ParkingDescription(
                        name = selectedParking?.place?.name ?: "cargando...",
                        price = selectedParking?.parking?.feePerHour,
                        address = address
                    )

                    Spacer(Modifier.size(32.dp))

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.lblHowMuchTime),
                            style = typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.graphicsLayer(alpha = 0.5f).weight(0.75f)
                        )

                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = colorScheme.tertiaryContainer,
                                contentColor = colorScheme.onTertiaryContainer
                            ),
                            modifier = Modifier
                                .weight(0.25f)
                                .height(50.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(
                                        R.string.lblForHour,
                                        sliderState.value.toInt()
                                    ),
                                    style = typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = colorScheme.onTertiaryContainer
                                )
                            }
                        }
                    }

                    Slider(
                        state = sliderState,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    )

                    Spacer(Modifier.size(16.dp))

                    Text(
                        text = stringResource(R.string.lblExpirationDate),
                        style = typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.graphicsLayer(alpha = 0.5f).padding(horizontal = 16.dp)
                    )

                    FlowRow(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                        //.horizontalScroll(rememberScrollState())
                    ) {

                        FilterChip(
                            selected = alertsState.fifteen,
                            label = {
                                Text(text = stringResource(R.string.lblFiteenMinutes))
                            },
                            onClick = {
                                updateAlerState(
                                    alertsState.copy(fifteen = !alertsState.fifteen)
                                )
                            }
                        )

                        Spacer(Modifier.size(8.dp))

                        FilterChip(
                            selected = alertsState.thirteen,
                            label = {
                                Text(text = stringResource(R.string.lblThirteenMinutes))
                            },
                            onClick = {
                                updateAlerState(
                                    alertsState.copy(thirteen = !alertsState.thirteen)
                                )
                            }
                        )

                        Spacer(Modifier.size(8.dp))

                        FilterChip(
                            selected = alertsState.fortyFive,
                            label = {
                                Text(text = stringResource(R.string.lblFortyFiveMinutes))
                            },
                            onClick = {
                                updateAlerState(
                                    alertsState.copy(fortyFive = !alertsState.fortyFive)
                                )
                            }
                        )

                        Spacer(Modifier.size(8.dp))

                        FilterChip(
                            selected = alertsState.oneHour,
                            label = {
                                Text(text = stringResource(R.string.lblOneHour))
                            },
                            onClick = {
                                updateAlerState(
                                    alertsState.copy(oneHour = !alertsState.oneHour)
                                )
                            }
                        )

                        Spacer(Modifier.size(8.dp))

                        FilterChip(
                            selected = alertsState.twoHour,
                            label = {
                                Text(text = stringResource(R.string.lblTwoHours))
                            },
                            onClick = {
                                updateAlerState(
                                    alertsState.copy(twoHour = !alertsState.twoHour)
                                )
                            }
                        )
                    }

                    Spacer(Modifier.size(32.dp))

                    // total y cantidad de horas
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
                            total =
                                (selectedParking?.parking?.feePerHour?.times(sliderState.value.toInt())
                                    ?: 0) as Double
                            //val total = selectedReservation?.parking?.feePerHour?.times(sliderState.value.toInt())
                            //val roundedTotal = String.format("%.2f", total).toDouble()

                            Text(
                                text = stringResource(id = R.string.lblPrice, String.format("%.2f", total.toDouble())),
                                style = typography.displayMedium
                            )

                            Spacer(modifier = Modifier.size(8.dp))

                            Text(
                                text = stringResource(
                                    id = R.string.lblTimeHours,
                                    sliderState.value.toInt()
                                ),
                                style = typography.bodyLarge
                            )
                        }
                    }

                    Spacer(Modifier.weight(1f))

                    TextButton(
                        onClick = onBackPressed,
                        shape = shapes.small,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.btnCancel))
                    }

                    // apartar lugar
                    Button(
                        onClick = {
                            val currentDateTime = LocalDateTime.now()

                            // Formateador para la fecha (AAAA-MM-DD)
                            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            val currentDate = currentDateTime.format(dateFormatter)

                            // Formateador para la hora (HH:mm:ss)
                            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                            val currentTime = currentDateTime.format(timeFormatter)

                            parkingViewModel.createReservation(
                                ReservationParking(
                                    amount = total,
                                    date = currentDate,
                                    hour = currentTime,
                                    idParking = selectedParking?.parking?.idParking ?: "",
                                    incomingAlertInMinutes = 45,
                                    reservationHours = sliderState.value.toInt(),
                                    typeVehicle = 2
                                )
                            )
                            onBookPressed()
                        },
                        shape = shapes.small,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.btnBooking))
                    }

                    Spacer(Modifier.size(16.dp))
                }
            }
        }
    }
}