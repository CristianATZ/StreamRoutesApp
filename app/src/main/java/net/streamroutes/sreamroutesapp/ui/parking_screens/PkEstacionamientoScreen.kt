package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.BeneficioItem
import net.streamroutes.sreamroutesapp.utils.EstacionadoItem
import net.streamroutes.sreamroutesapp.utils.ReservacionItem
import net.streamroutes.sreamroutesapp.utils.brush
import net.streamroutes.sreamroutesapp.viewmodel.parking.ApartarPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel

@Composable
fun ParkingEstacionamientoScreen(parkingPkViewModel: ParkingPkViewModel, apartarPkViewModel: ApartarPkViewModel) {
    val parkingState by parkingPkViewModel.uiState.collectAsState()

    Column {
        if(parkingState.estacionamiento != null){
            Header(parkingPkViewModel)
            VehiculoEstacionado(parkingPkViewModel, apartarPkViewModel)
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
fun VehiculoEstacionado(
    parkingPkViewModel: ParkingPkViewModel,
    apartarPkViewModel: ApartarPkViewModel
) {

    Column {
        Spacer(modifier = Modifier.size(16.dp))

        Estacionados(parkingPkViewModel, apartarPkViewModel)
    }
}

@Composable
private fun Estacionados(
    parkingPkViewModel: ParkingPkViewModel,
    apartarPkViewModel: ApartarPkViewModel
) {
    val parkingState by parkingPkViewModel.uiState.collectAsState()

    var openDialog by remember {
        mutableStateOf(false)
    }

    if(openDialog) {
        QrDialog(
            apartarPkViewModel = apartarPkViewModel,
            onDismiss = { openDialog = !openDialog }
        )
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        if(parkingState.apartado){
            ReservacionObject(
                reservacionItem = ReservacionItem(
                    matricula = parkingState.vehiculo?.matricula ?: "",
                    estacionamientoNombre = parkingState.estacionamiento?.name ?: "",
                    horaInicio = parkingState.horaInicio,
                    minutoInicio = parkingState.minutoInicio,
                    horaFInal = parkingState.horaFinal,
                    total = parkingState.total
                )
            ) {
                apartarPkViewModel.generateQRCode("idApartado:123|idEstacionamiento:1")
                openDialog = !openDialog
            }
        } else {
            EstacionadoObject(
                estacionadoItem = EstacionadoItem(
                    matricula = parkingState.vehiculo?.matricula ?: "",
                    estacionamientoNombre = parkingState.estacionamiento?.name ?: "",
                    tiempo = parkingState.tiempo,
                    total = parkingState.total
                )
            )
        }
    }
}

@Composable
fun EstacionadoObject(
    estacionadoItem: EstacionadoItem
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
                    subtitle = estacionadoItem.matricula
                )

                BeneficioItem(
                    icon = painterResource(id = R.drawable.marker_parking),
                    title = stringResource(id = R.string.lblEstacionamiento),
                    subtitle = estacionadoItem.estacionamientoNombre
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
                        .background(
                            colorScheme.tertiary,
                            RoundedCornerShape(bottomStart = 8.dp)
                        ),
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
                        text = "${estacionadoItem.tiempo} minutos",
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
                        text = "$${estacionadoItem.total}",
                        style = typography.headlineLarge,
                        color = colorScheme.tertiary,
                        fontWeight = FontWeight.W500
                    )
                }
            }
        }
    }
}

@Composable
fun ReservacionObject(
    reservacionItem: ReservacionItem,
    showQR: () -> Unit
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
                    subtitle = reservacionItem.matricula
                )

                BeneficioItem(
                    icon = painterResource(id = R.drawable.marker_parking),
                    title = stringResource(id = R.string.lblEstacionamiento),
                    subtitle = reservacionItem.estacionamientoNombre
                )

                BeneficioItem(
                    icon = painterResource(id = R.drawable.hora_exacta),
                    title = stringResource(id = R.string.lblHoraInicio),
                    subtitle = "${reservacionItem.horaInicio}:${reservacionItem.minutoInicio}"
                )
            }
        }

        Spacer(modifier = Modifier.size(8.dp))

        Column {
            Button(
                onClick = {
                    showQR()
                },
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
                            .background(
                                colorScheme.tertiary,
                                RoundedCornerShape(bottomStart = 8.dp)
                            ),
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
                            text = "${reservacionItem.horaFInal-reservacionItem.horaInicio} hora(s)",
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
                            text = "$${reservacionItem.total}",
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


@Suppress("PreviewAnnotationInFunctionWithParameters")
@Preview
@Composable
private fun QrDialog(
    apartarPkViewModel: ApartarPkViewModel,
    onDismiss: () -> Unit = {}
) {
    val apartarUiState = apartarPkViewModel.uiState.collectAsState()

    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .background(brush, RoundedCornerShape(16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.White, RoundedCornerShape(16.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(apartarUiState.value.qrCode != null ){
                    Image(
                        bitmap = apartarUiState.value.qrCode!!.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(250.dp)
                            .padding(8.dp)
                    )
                } else {
                    CircularProgressIndicator()
                }
            }

            Text(
                text = apartarUiState.value.estacionamiento!!.name,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onTertiary,
                style = typography.displaySmall
            )

            Spacer(modifier = Modifier.size(16.dp))
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