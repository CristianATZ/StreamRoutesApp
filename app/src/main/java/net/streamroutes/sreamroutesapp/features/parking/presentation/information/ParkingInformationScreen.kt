package net.streamroutes.sreamroutesapp.features.parking.presentation.information

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.ParkingDescription
import net.streamroutes.sreamroutesapp.features.parking.components.InformationChip

@Composable
fun ParkingInformationScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {

    val services = listOf(
        "Camaras",
        "Horario de 07:00 - 23:00",
        "Servicio de lavada de auto"
    )

    val onStartRoute = {

    }

    val onBookingRoute = {

    }

    Scaffold(

    ) { innerPadding ->
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

                Spacer(Modifier.size(32.dp))

                // servicios
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

                Spacer(Modifier.size(16.dp))

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

                Spacer(Modifier.weight(1f))

                // boton de apartar espacio
                OutlinedButton(
                    onClick = {
                        onBookingRoute()
                    },
                    shape = shapes.small,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.lblBookPlace))
                }

                // boton de iniciar viaje
                Button(
                    onClick = {
                        onStartRoute()
                    },
                    shape = shapes.small,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.lblStartParkingRoute))
                }

                Spacer(Modifier.size(16.dp))
            }
        }
    }
}