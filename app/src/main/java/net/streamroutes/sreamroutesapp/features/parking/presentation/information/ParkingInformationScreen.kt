package net.streamroutes.sreamroutesapp.features.parking.presentation.information

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.ParkingDescription
import net.streamroutes.sreamroutesapp.features.parking.components.InformationChip
import net.streamroutes.sreamroutesapp.features.profile.components.ShimmerHistoryItem
import net.streamroutes.sreamroutesapp.utils.shimmerEffect

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ParkingInformationScreen(
    onBackPressed: () -> Unit = {},
    onBookingPressed: () -> Unit,
    onSelectPressed: () -> Unit
) {
    var isLoading by remember {
        mutableStateOf(false)
    }


    val services = listOf(
        "Camaras",
        "Horario de 07:00 - 23:00",
        "Servicio de lavada de auto"
    )

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f)
                    .background(Color.Gray)
                    .align(Alignment.TopCenter)
            ) {
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

                    if(!isLoading) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .background(Color.Black.copy(0.5f), shapes.extraLarge),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.lblAllowPlaces, "5"),
                                style = typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    } else {
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .height(40.dp)
                                .clip(shapes.extraLarge)
                                .shimmerEffect()
                        )
                    }
                }
            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .align(Alignment.BottomCenter)
                    .background(colorScheme.background)
            ) {

                Spacer(Modifier.size(16.dp))

                // decripcion estacionamiento
                ParkingDescription(
                    name = "ITSUR",
                    address = "Padre Luis Gaytan #234",
                    price = 29.5
                )
                if(!isLoading) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                    ) {
                        InformationChip(
                            text = stringResource(R.string.lblDistance, "400 m")
                        )

                        InformationChip(
                            text = stringResource(R.string.lblCapacity, "30")
                        )

                        InformationChip(
                            text = stringResource(R.string.lblWaitPlace, "3.2 Hrs")
                        )

                        InformationChip(
                            text = stringResource(R.string.lblRating, "4.6")
                        )
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

                Spacer(Modifier.size(32.dp))

                // servicios
                if(!isLoading) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.lblOfferServices),
                            style = typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.size(8.dp))

                        services.forEach { item ->
                            Text(
                                text = stringResource(R.string.lblDout, item),
                                style = typography.labelLarge,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .graphicsLayer(alpha = 0.5f)
                            )
                        }
                    }
                } else {
                    Column {
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .fillMaxWidth(0.4f)
                                .height(25.dp)
                                .clip(shapes.small)
                                .shimmerEffect()
                        )

                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                                .fillMaxWidth()
                                .height(25.dp)
                                .clip(shapes.small)
                                .shimmerEffect()
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                                .fillMaxWidth()
                                .height(25.dp)
                                .clip(shapes.small)
                                .shimmerEffect()
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                                .fillMaxWidth()
                                .height(25.dp)
                                .clip(shapes.small)
                                .shimmerEffect()
                        )
                    }
                }

                Spacer(Modifier.size(16.dp))

                if(!isLoading) {
                    // descripcion
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.lblDescription),
                            style = typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.size(8.dp))

                        Text(
                            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                            style = typography.labelLarge,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .graphicsLayer(alpha = 0.5f)
                        )
                    }
                } else {
                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth(0.4f)
                            .height(25.dp)
                            .clip(shapes.small)
                            .shimmerEffect()
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .clip(shapes.small)
                            .shimmerEffect()
                    )
                }

                Spacer(Modifier.weight(1f))

                if(!isLoading) {
                    // boton de apartar espacio
                    OutlinedButton(
                        onClick = onBookingPressed,
                        shape = shapes.small,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.lblBookPlace))
                    }

                    // boton de iniciar viaje
                    Button(
                        onClick = onSelectPressed,
                        shape = shapes.small,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.lblStartParkingRoute))
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

                    Spacer(
                        modifier = Modifier.size(8.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(shapes.small)
                            .shimmerEffect()
                    )
                }

                Spacer(Modifier.size(16.dp))
            }
        }
    }
}