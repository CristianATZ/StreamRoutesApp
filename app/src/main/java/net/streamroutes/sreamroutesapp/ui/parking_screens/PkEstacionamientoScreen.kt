package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.BeneficioItem
import net.streamroutes.sreamroutesapp.viewmodel.parking.HistorialItem
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel

@Composable
fun ParkingEstacionamientoScreen(parkingPkViewModel: ParkingPkViewModel) {
    val parkingState by parkingPkViewModel.uiState.collectAsState()

    Column {
        if(parkingState.estacionamiento != null){
            Header(parkingPkViewModel)
            VehiculoEstacionado(parkingPkViewModel)
        } else {
            VehiculoNoEstacionado()
        }
    }
}

@Composable
fun VehiculoNoEstacionado() {
    val label = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        ){
            //append(stringResource(id = R.string.lblHeader1) + " ")
            append("No hay coches estacionados.")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.estacionamiento),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = label,
            style = typography.displaySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun VehiculoEstacionado(parkingPkViewModel: ParkingPkViewModel) {

    Column {
        Spacer(modifier = Modifier.size(16.dp))

        Estacionados(parkingPkViewModel)
    }
}

@Composable
private fun Estacionados(parkingPkViewModel: ParkingPkViewModel) {
    val parkingState by parkingPkViewModel.uiState.collectAsState()

    /*LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            VehiculoItem(
                HistorialItem(
                    parkingState.vehiculo,
                    parkingState.estacionamiento,
                    parkingState.tiempo,
                    parkingState.total
                )
            )
        }
    }*/

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(colorScheme.background)
                        .padding(8.dp)
                ) {
                    BeneficioItem(
                        icon = painterResource(id = R.drawable.car),
                        title = stringResource(id = R.string.lblVehiculo),
                        subtitle = parkingState.vehiculo!!.matricula
                    )

                    BeneficioItem(
                        icon = painterResource(id = R.drawable.marker_parking),
                        title = stringResource(id = R.string.lblEstacionamiento),
                        subtitle = parkingState.estacionamiento!!.name
                    )

                    BeneficioItem(
                        icon = painterResource(id = R.drawable.hora_exacta),
                        title = stringResource(id = R.string.lblHoraInicio),
                        subtitle = "${parkingState.horaInicio}:${parkingState.minutoInicio}"
                    )
                }
            }

            Spacer(modifier = Modifier.size(8.dp))

            Column {
                Button(
                    onClick = { /*TODO*/ },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0.682f, 0.753f, 1.0f),
                        contentColor = Color(0.0f, 0.082f, 0.667f, 1.0f)
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 8.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(50.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.btnMostrarQR),
                        style = typography.bodyLarge,
                        fontWeight = FontWeight.W500
                    )
                }

                Spacer(modifier = Modifier.size(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        ),
                        shape = RoundedCornerShape(bottomStart = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(75.dp)
                                .background(colorScheme.tertiary, RoundedCornerShape(bottomStart = 8.dp)),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // intercambiar textos entre ESTACIONADOS y APARTADOS
                            Text(
                                text = stringResource(id = R.string.lblTiempoRestante),
                                style = typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = colorScheme.onTertiary
                            )
                            Text(
                                text = "${parkingState.horaFinal-parkingState.horaInicio} hora(s)",
                                style = typography.bodyLarge,
                                color = colorScheme.onTertiary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Card(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        ),
                        shape = RoundedCornerShape(bottomEnd = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.95f)
                                .height(75.dp)
                                .background(colorScheme.inverseSurface),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$${parkingState.total}",
                                style = typography.headlineLarge,
                                color = colorScheme.tertiary,
                                fontWeight = FontWeight.W500
                            )
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(colorScheme.background)
                        .padding(8.dp)
                ) {
                    BeneficioItem(
                        icon = painterResource(id = R.drawable.car),
                        title = stringResource(id = R.string.lblVehiculo),
                        subtitle = parkingState.vehiculo!!.matricula
                    )

                    BeneficioItem(
                        icon = painterResource(id = R.drawable.marker_parking),
                        title = stringResource(id = R.string.lblEstacionamiento),
                        subtitle = parkingState.estacionamiento!!.name
                    )
                }
            }

            Spacer(modifier = Modifier.size(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    shape = RoundedCornerShape(bottomStart = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(75.dp)
                            .background(colorScheme.tertiary, RoundedCornerShape(bottomStart = 8.dp)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // intercambiar textos entre ESTACIONADOS y APARTADOS
                        Text(
                            text = stringResource(id = R.string.lblTiempoActual),
                            style = typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.onTertiary
                        )
                        Text(
                            text = "${parkingState.tiempo} minutos",
                            style = typography.bodyLarge,
                            color = colorScheme.onTertiary
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    shape = RoundedCornerShape(bottomEnd = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .height(75.dp)
                            .background(colorScheme.inverseSurface),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$${parkingState.total}",
                            style = typography.headlineLarge,
                            color = colorScheme.tertiary,
                            fontWeight = FontWeight.W500
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun VehiculoItem(historialItem: HistorialItem) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.background,
            contentColor = colorScheme.onBackground
        ),
        modifier = Modifier
            .fillMaxWidth(0.9f)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.size(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.car),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Column {
                    Text(text = stringResource(id = R.string.lblVehiculo), style = typography.titleMedium)
                    Text(text = historialItem.vehiculo!!.matricula, style = typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.marker_parking),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Column {
                    Text(text = stringResource(id = R.string.lblEstacionamiento), style = typography.titleMedium)
                    Text(text = historialItem.estacionamiento!!.name, style = typography.bodyMedium)
                    Text(text = historialItem.estacionamiento.address, style = typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.size(32.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.hora_exacta),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "1 minuto", style = typography.titleMedium)

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.precio),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "$${historialItem.total}", style = typography.headlineSmall, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun Header(parkingPkViewModel: ParkingPkViewModel) {

    val headerText1 = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = colorScheme.background,
                fontWeight = FontWeight.Bold
            )
        ){
            append(stringResource(id = R.string.lblRelajate) + " ")
        }
    }

    val headerText2 = generaHeaderText(
        stringResource(id = R.string.lblTu),
        stringResource(id = R.string.lblVehiculo),
        stringResource(id = R.string.lblEsta),
        SpanStyle(fontWeight = FontWeight.Bold, color = colorScheme.background),
        SpanStyle(fontWeight = FontWeight.Bold, color = colorScheme.tertiary),
    )

    val headerText3 = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = colorScheme.tertiary,
                fontWeight = FontWeight.Bold
            )
        ){
            append(stringResource(id = R.string.lblAqui) + " ")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.height(250.dp)
            .background(
                color = colorScheme.primary,
                RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // texto
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            Text(text = headerText1, style = typography.displaySmall)
            Text(text = headerText2, style = typography.displaySmall)
            Text(text = headerText3, style = typography.displaySmall)
        }
    }
}

private fun generaHeaderText(
    lblHeader1: String,
    lblHeader2: String,
    lblHeader3: String,
    spanStyleNormal: SpanStyle,
    spanStyleBold: SpanStyle
): AnnotatedString {
    return buildAnnotatedString {
        withStyle(
            style = spanStyleNormal
        ){
            append("$lblHeader1 ")
        }

        withStyle(
            style = spanStyleBold
        ){
            append("$lblHeader2 ")
        }

        withStyle(
            style = spanStyleNormal
        ){
            append(lblHeader3)
        }
    }
}