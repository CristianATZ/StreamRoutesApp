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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.components.ParkingDescription

data class AlertsExpiration(
    var fifteen: Boolean = false,
    var thirteen: Boolean = false,
    var fortyFive: Boolean = false,
    var oneHour: Boolean = false,
    var twoHour: Boolean = false
)

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(20.126856880277188, -101.19127471960047), 17f) // San Francisco como posición inicial
    }

    val sliderPosition by remember {
        mutableFloatStateOf(1f)
    }

    val sliderState = SliderState(
        value = sliderPosition,
        steps = 10,
        valueRange = 1f..12f
    )

    var alertsState by remember {
        mutableStateOf(AlertsExpiration())
    }

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
            MapFullSize(
                cameraPositionState = cameraPositionState,
                onMapClick = {

                },
                onMapLoaded = {

                },
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.35f)
            ) {

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
                    .background(colorScheme.background, RoundedCornerShape(topEnd = 28.dp, topStart = 28.dp))

            ) {
                Spacer(Modifier.size(16.dp))

                ParkingDescription(
                    name = "ITSUR",
                    price = 39.0,
                    address = "Padre Luis Gaytan #234"
                )

                Spacer(Modifier.size(32.dp))

                Text(
                    text = stringResource(R.string.lblHowMuchTime),
                    style = typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f).padding(horizontal = 16.dp)
                )

                Slider(
                    state = sliderState,
                    thumb = {

                    },
                    track = {
                        
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )

                Spacer(Modifier.size(32.dp))

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
                        Text(
                            text = stringResource(id = R.string.lblPrice, "600"),
                            style = typography.displayMedium
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        Text(
                            text = stringResource(id = R.string.lblTimeHours, "60"),
                            style = typography.bodyLarge
                        )
                    }
                }

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
                        onBookPressed()
                    },
                    shape = shapes.small,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.btnBooking))
                }
            }
        }
    }
}