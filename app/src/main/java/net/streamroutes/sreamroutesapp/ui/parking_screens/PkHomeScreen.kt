package net.streamroutes.sreamroutesapp.ui.parking_screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.data.model.ParkingResultItem
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.BeneficioItem
import net.streamroutes.sreamroutesapp.viewmodel.parking.AccountPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.TipoVehiculo
import net.streamroutes.sreamroutesapp.viewmodel.parking.Vehiculo

@Composable
fun ParkingHomeScreen(
    homePkViewModel: HomePkViewModel,
    accountPkViewModel: AccountPkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    qrScanner: () -> Unit
) {
    val uiState by homePkViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(visible = !uiState.iniciarRecorrido && !uiState.verEstacionamiento) {
            Column {
                Header(homePkViewModel)

                AnimatedVisibility(visible = !uiState.verTodo) {
                    Vehicle(homePkViewModel, accountPkViewModel)
                }

                Spots(homePkViewModel)
            }
        }

        if (uiState.iniciarRecorrido || uiState.verEstacionamiento) {
            IniciarViajeScreen(homePkViewModel, parkingPkViewModel) {
                qrScanner()
            }
        }
    }
}


@Composable
private fun Spots(homePkViewModel: HomePkViewModel) {
    val uiState by homePkViewModel.uiState.collectAsState()

    Column {
        // encabezado
        Row {
            Text(
                text = stringResource(id = R.string.lblEspaciosCercanos),
                style = typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = if (!uiState.verTodo) R.string.lblVerTodo else R.string.lblVerMenos),
                style = typography.titleMedium,
                color = colorScheme.tertiary,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        homePkViewModel.updateVerTodo(!uiState.verTodo)
                    }
            )
        }

        // espacios cercanos
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(uiState.parkingList.size) { index ->
                SpotItem(uiState.parkingList[index], homePkViewModel)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpotItem(
    spot: ParkingResultItem,
    homePkViewModel: HomePkViewModel
) {

    val uiState by homePkViewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    val waves = colorScheme.secondary

    var openBottomSheet by remember {
        mutableStateOf(false)
    }

    val sheetState = SheetState(
        skipPartiallyExpanded = true,
        density = LocalDensity.current,
        initialValue = SheetValue.Hidden
    )

    if(openBottomSheet){
        BottomSheetInfo(sheetState, spot, homePkViewModel){
            scope.launch {
                sheetState.hide()
            }.invokeOnCompletion { openBottomSheet = false }
        }
    }

    val modifier = if(uiState.verEstacionamiento || uiState.iniciarRecorrido){
        Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(0.9f)
    } else {
        Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(0.9f)
            .clickable {
                openBottomSheet = !openBottomSheet
            }
    }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.secondaryContainer,
            contentColor = colorScheme.onSecondaryContainer
        ),
        modifier = modifier
    ) {
        Column {
            Spacer(modifier = Modifier.size(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.size(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.estacionamiento),
                    contentDescription = null,
                    modifier = Modifier
                        .size(75.dp)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = spot.name,
                        style = typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                    )

                    Text(
                        text = "${spot.direccion}, ${spot.postalCode}",
                        style = typography.bodyLarge,
                        //letterSpacing = 2.sp,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))
            }

            Spacer(modifier = Modifier.size(16.dp))

            // importante
            Box(modifier = Modifier.fillMaxWidth()) {
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    val path = androidx.compose.ui.graphics.Path().apply {
                        moveTo(0f, 0f)
                        cubicTo(
                            size.width * 0.25f, 40f,
                            size.width * 0.75f, -40f,
                            size.width, 0f
                        )
                        lineTo(size.width, size.height)
                        lineTo(0f, size.height)
                        close()
                    }
                    drawPath(path, color = waves)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.size(16.dp))

                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = colorScheme.tertiary,
                        modifier = Modifier
                            .size(25.dp)
                    )
                    Text(
                        text = "${spot.calification}",
                        style = typography.titleMedium,
                        color = colorScheme.onSecondary
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "$${spot.price}",
                        style = typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.tertiary
                    )

                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetInfo(
    sheetState: SheetState,
    spot: ParkingResultItem,
    homePkViewModel: HomePkViewModel,
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()

    /*var openDialogIniciarRecorrido by remember {
        mutableStateOf(false)
    }

    if(openDialogIniciarRecorrido){
        DialogIniciarRecorrido(spot = spot, homePkViewModel = homePkViewModel, closeBottom = {
            onDismiss()
        }) {
            openDialogIniciarRecorrido = !openDialogIniciarRecorrido
        }
    }*/

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BeneficioItem(
                icon = painterResource(id = R.drawable.estacionamiento),
                title = stringResource(id = R.string.lblEstacionamiento),
                subtitle = spot.name
            )

            BeneficioItem(
                icon = painterResource(id = R.drawable.direccion),
                title = stringResource(id = R.string.lblDireccion),
                subtitle = "${spot.direccion}, ${spot.postalCode}."
            )

            BeneficioItem(
                icon = painterResource(id = R.drawable.opinion),
                title = stringResource(id = R.string.lblOpiniones),
                subtitle = spot.opinions.size.toString()
            )

            BeneficioItem(
                icon = painterResource(id = R.drawable.calificacion),
                title = stringResource(id = R.string.lblCalificacion),
                subtitle = spot.calification.toString()
            )

            Spacer(modifier = Modifier.size(32.dp))

            CustomCard(
                backgroundColor = colorScheme.background,
                content = {
                    BeneficioItem(
                        icon = painterResource(id = R.drawable.hora_exacta),
                        title = stringResource(id = R.string.lblTiempoAproxEspera),
                        subtitle = spot.timeAprox
                    )
                },
                button1Text = stringResource(id = R.string.btnApartarEspacio),
                button1OnClick = {
                    // Acción del primer botón
                },
                color1 = ButtonDefaults.buttonColors(
                    containerColor = Color(0f, 0f, 1f, 0.2f),
                    contentColor = Color(0.2f, 0.3f, 1f)
                ),
                shape1 = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            )

            Spacer(modifier = Modifier.size(32.dp))

            CustomCard(
                backgroundColor = colorScheme.background,
                content = {
                    BeneficioItem(
                        icon = painterResource(id = R.drawable.precio),
                        title = stringResource(id = R.string.lblPrecioPorHora),
                        subtitle = "$${spot.price}"
                    )
                },
                button1Text = stringResource(id = R.string.btnIniciarRecorrido),
                button1OnClick = {
                    //openDialogIniciarRecorrido = !openDialogIniciarRecorrido
                    scope.launch {
                        onDismiss()
                        delay(200)
                        onDismiss()
                        homePkViewModel.updateIniciarRecorrido(true)
                        Log.d("ESTACIONAMIENTO", spot.toString())
                        homePkViewModel.updateEstacionamientoSeleccionado(spot)
                    }
                },
                color1 = ButtonDefaults.buttonColors(
                    containerColor = Color(0f, 0.5f, 0f, 0.2f),
                    contentColor = Color(0f, 0.7f, 0f)
                ),
                shape1 = RoundedCornerShape(bottomStart = 16.dp),
                button2Text = stringResource(id = R.string.btnVerMapa),
                button2OnClick = {
                    scope.launch {
                        onDismiss()
                        homePkViewModel.updateEstacionamientoSeleccionado(spot)
                        homePkViewModel.updateVerEstacionamiento(true)
                    }
                },
                color2 = ButtonDefaults.buttonColors(
                    containerColor = Color(1f, 0.647f, 0f, 0.2f),
                    contentColor = Color(1f, 0.55f, 0f)
                ),
                shape2 = RoundedCornerShape(bottomEnd = 16.dp)
            )


            Spacer(modifier = Modifier.size(64.dp))
        }
    }
}

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    content: @Composable () -> Unit,
    button1Text: String,
    button1OnClick: () -> Unit,
    shape1: RoundedCornerShape,
    color1: ButtonColors,
    button2Text: String? = null,
    button2OnClick: (() -> Unit)? = null,
    shape2: Shape = CircleShape,
    color2: ButtonColors = ButtonColors(Color.Transparent,Color.Transparent, Color.Transparent, Color.Transparent)
) {
    Column(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .background(backgroundColor)
    ) {
        content()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = button1OnClick,
                shape = shape1,
                colors = color1,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text(
                    text = button1Text,
                    style = typography.bodyLarge
                )
            }
            if (button2Text != null && button2OnClick != null) {
                Button(
                    onClick = button2OnClick,
                    shape = shape2,
                    colors = color2,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text(
                        text = button2Text,
                        style = typography.bodyLarge
                    )
                }
            }
        }
    }
}


@Composable
private fun DialogIniciarRecorrido(
    spot: ParkingResultItem,
    homePkViewModel: HomePkViewModel,
    closeBottom: () -> Unit,
    onDismiss: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    Dialog(onDismissRequest = { onDismiss() }) {
        Card {
            // nombre stacionamiento y pregunta
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // nombre estacionamiento
                Spacer(modifier = Modifier.size(16.dp))

                Icon(
                    imageVector = Icons.Filled.DoneAll,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = spot.name,
                    textAlign = TextAlign.Center,
                    style = typography.displayMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(id = R.string.lblIniciarRecorrido),
                    textAlign = TextAlign.Center,
                    style = typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            // boton comenzar
            Button(
                onClick = {
                    scope.launch {
                        closeBottom()
                        delay(200)
                        onDismiss()
                        homePkViewModel.updateIniciarRecorrido(true)
                        homePkViewModel.updateEstacionamientoSeleccionado(spot)
                    }
                },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorScheme.onPrimaryContainer,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(id = R.string.lblComenzar))
            }

            // boton cancelar
            Button(
                onClick = { onDismiss() },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonColors(
                    containerColor = colorScheme.error,
                    contentColor = colorScheme.onError,
                    disabledContainerColor = colorScheme.error,
                    disabledContentColor = colorScheme.onError
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(id = R.string.lblCancelar))
            }
        }
    }
}

@Composable
private fun Vehicle(
    homePkViewModel: HomePkViewModel,
    accountPkViewModel: AccountPkViewModel
) {
    val uiState by homePkViewModel.uiState.collectAsState()

    val vehiculos = accountPkViewModel.vehicles

    Column {
        // encabezado
        Row {
            Text(
                text = stringResource(id = R.string.lblMisVehiculos),
                style = typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }

        // vehiculos del usuario
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.95f)
        ) {
            items( vehiculos.size/*Aqui agregar el viewmodel*/){ index ->
                VehicleItem(index, vehiculos[index], uiState.vehiculoSeleccionado.matricula){
                    homePkViewModel.updateVehiculoSeleccionado(vehiculos[index])
                }
            }
        }
    }
}

@Composable
fun VehicleItem(
    index: Int,
    item: Vehiculo,
    selection: String,
    onClick: () -> Unit
) {
    val cont = if(item.matricula == selection) colorScheme.tertiary else colorScheme.primaryContainer
    val int = if(item.matricula == selection) colorScheme.onTertiary else colorScheme.onPrimaryContainer

    val icono = when(item.tipo) {
        TipoVehiculo.CARRO -> painterResource(id = R.drawable.car)
        TipoVehiculo.MOTO -> painterResource(id = R.drawable.motorcycle)
        TipoVehiculo.TRACTOR -> painterResource(id = R.drawable.car)
        TipoVehiculo.NINGUNO -> TODO()
    }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardColors(
            containerColor = cont,
            contentColor = int,
            disabledContainerColor = cont,
            disabledContentColor = int
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier
                .size(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = icono,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Text(
                text = item.matricula,
                style = typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun Header(homePkViewModel: HomePkViewModel) {

    val uiState by homePkViewModel.uiState.collectAsState()

    val headerText1 = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = colorScheme.background,
                fontWeight = FontWeight.Bold
            )
        ){
            append(stringResource(id = R.string.lblHeader1) + " ")
        }
    }

    val headerText2 = generaHeaderText(
        stringResource(id = R.string.lblHeader2),
        stringResource(id = R.string.lblHeader3),
        stringResource(id = R.string.lblHeader4),
        SpanStyle(fontWeight = FontWeight.Bold, color = colorScheme.background),
        SpanStyle(fontWeight = FontWeight.Bold, color = colorScheme.tertiary),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorScheme.primary,
                RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(32.dp))

        // texto e imagen
        AnimatedVisibility(visible = (!uiState.iniciarRecorrido && !uiState.verTodo)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = headerText1, style = typography.displaySmall)
                    Text(text = headerText2, style = typography.displaySmall)
                }
                Image(
                    painter = painterResource(id = R.drawable.logo_icono),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                )
            }
        }

        Spacer(modifier = Modifier.size(8.dp))

        // barra de busqueda
        OutlinedTextField(
            value = "",
            onValueChange = {  },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.lblBarraBusquedaInicio),
                    style = typography.titleMedium
                )
            },
            shape = RoundedCornerShape(16.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = colorScheme.background,
                unfocusedBorderColor = colorScheme.background,
                focusedContainerColor = colorScheme.background,
                focusedBorderColor = colorScheme.background,
            ),
            modifier = Modifier
                .fillMaxWidth(0.85f)
        )

        Spacer(modifier = Modifier.size(32.dp))
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
            style = spanStyleBold
        ){
            append("$lblHeader1 ")
        }

        withStyle(
            style = spanStyleNormal
        ){
            append("$lblHeader2 ")
        }

        withStyle(
            style = spanStyleBold
        ){
            append(lblHeader3)
        }
    }
}