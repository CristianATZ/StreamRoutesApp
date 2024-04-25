package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.BackHand
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.parking.Estacionamiento
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.TipoVehiculo
import net.streamroutes.sreamroutesapp.viewmodel.parking.Vehiculo

@Composable
fun ParkingHomeScreen(homePkViewModel: HomePkViewModel) {
    Column {
        if( !homePkViewModel.iniciarRecorrido ){
            Header(homePkViewModel)

            if( !homePkViewModel.verTodo ){
                Vehicle(homePkViewModel)
            }

            Spots(homePkViewModel)
        } else {
            IniciarViaje(homePkViewModel)
        }
    }
}

@Composable
fun IniciarViaje(homePkViewModel: HomePkViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // mapa
        MapaRecorrido(homePkViewModel)

        // header iniciar viaje
        HeaderIniciarViaje(homePkViewModel)

        // cancelar viaje
        CancelarViaje(homePkViewModel)
    }
}

@Composable
fun MapaRecorrido(homePkViewModel: HomePkViewModel) {

    val ubicacion = LatLng(20.139609738093373, -101.1507421629189)

    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ubicacion, 15f)
    }

    GoogleMap(
        cameraPositionState = cameraPosition,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false
        ),
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions(stringResource(id = R.string.mapStyleLight))
        ),
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
fun CancelarViaje(
    homePkViewModel: HomePkViewModel
) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    if(openDialog){
        DialogCancelarRecorrido(homePkViewModel){
            openDialog = !openDialog
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                openDialog = !openDialog
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(50.dp)
        ) {
            Text(text = stringResource(id = R.string.lblCancelarViaje))
        }
        
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
fun DialogCancelarRecorrido(
    homePkViewModel: HomePkViewModel,
    onDismiss: () -> Unit
) {
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
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = null,
                    tint = colorScheme.error,
                    modifier = Modifier
                        .size(50.dp)
                )
                
                Spacer(modifier = Modifier.size(8.dp))

                // titulo
                Text(
                    text = stringResource(id = R.string.lblSeguroCancelarViaje),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.size(32.dp))

            // boton comenzar
            Button(
                onClick = {
                    homePkViewModel.updateIniciarRecorrido(false)
                    homePkViewModel.updateEstacionamientoSeleccionado(
                        Estacionamiento("","", "", "", "", -1)
                    )
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
                Text(text = stringResource(id = R.string.lblAceptar))
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
fun HeaderIniciarViaje(homePkViewModel: HomePkViewModel) {
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
        SpotItem(
            spot = homePkViewModel.estacionamientoSeleccionado,
            homePkViewModel = homePkViewModel
        )
        
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
private fun Spots(homePkViewModel: HomePkViewModel) {
    val spotList = listOf(
        Estacionamiento("Gueros","Padre Luis Gaytan", "San Isidro", "38887", "5.0", 30),
        Estacionamiento("Negros","Padre Luis ", "San ", "38887", "5.0", 24),
    )

    Column {
        // encabezado
        Row {
            Text(
                text = stringResource(id = R.string.lblEspaciosCercanos),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = stringResource(id = if(!homePkViewModel.verTodo) R.string.lblVerTodo else R.string.lblVerMenos),
                style = MaterialTheme.typography.titleMedium,
                color = colorScheme.tertiary,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        homePkViewModel.updateVerTodo(
                            !homePkViewModel.verTodo
                        )
                    }
            )
        }

        // espacios cercanos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(spotList.size) { index ->
                SpotItem(spotList[index], homePkViewModel)
            }
        }
    }
}

@Composable
private fun SpotItem(
    spot: Estacionamiento,
    homePkViewModel: HomePkViewModel
) {

    var rotated by remember {
        mutableStateOf(false)
    }

    val rotationFront by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val rotationBack by animateFloatAsState(
        targetValue = if (rotated) -180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val modifier = if(homePkViewModel.iniciarRecorrido){
        Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(0.9f)
    } else {
        Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(0.9f)
            .height(150.dp)
            .graphicsLayer {
                rotationY = if (!rotated) rotationFront else rotationBack
                cameraDistance = 8 * density
            }
            .clickable {
                //openDialog = !openDialog
                rotated = !rotated
            }
    }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = modifier
    ) {
        if(!rotated){
            // frente
            SpotItemFront(spot)
        } else {
            // reverso
            SportItemBack(spot, rotationBack, animateBack, homePkViewModel){
                rotated = !rotated
            }
        }

    }
}

@Composable
fun SportItemBack(
    spot: Estacionamiento,
    rotationBack: Float,
    animateBack: Float,
    homePkViewModel: HomePkViewModel,
    onBack: () -> Unit
) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    if(openDialog){
        DialogIniciarRecorrido(
            spot,
            homePkViewModel
        ){
            openDialog = !openDialog
        }
    }

    val modifier = Modifier
        .size(40.dp)
        .graphicsLayer {
            alpha = animateBack
            rotationY = rotationBack
        }

    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // regresar
        Button(
            onClick = { onBack() },
            shape = RoundedCornerShape(0.dp),
            colors = ButtonColors(
                containerColor = colorScheme.error,
                contentColor = colorScheme.onError,
                disabledContainerColor = colorScheme.error,
                disabledContentColor = colorScheme.onError
            ),
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Filled.Cancel,
                contentDescription = null,
                modifier = modifier
            )
        }

        // ver mapa
        Button(
            onClick = {  },
            shape = RoundedCornerShape(0.dp),
            colors = ButtonColors(
                containerColor = colorScheme.tertiary.copy(0.9f),
                contentColor = colorScheme.onTertiary,
                disabledContainerColor = colorScheme.tertiary.copy(0.9f),
                disabledContentColor = colorScheme.onTertiary
            ),
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Filled.Map,
                contentDescription = null,
                modifier = modifier
            )
        }

        // apartar lugar
        Button(
            onClick = {  },
            shape = RoundedCornerShape(0.dp),
            colors = ButtonColors(
                containerColor = colorScheme.secondary.copy(0.6f),
                contentColor = colorScheme.onSecondary,
                disabledContainerColor = colorScheme.secondary.copy(0.6f),
                disabledContentColor = colorScheme.onSecondary
            ),
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Filled.BackHand,
                contentDescription = null,
                modifier = modifier
            )
        }

        // iniciar recorrido
        Button(
            onClick = { openDialog = !openDialog },
            shape = RoundedCornerShape(0.dp),
            colors = ButtonColors(
                containerColor = Color.Green.copy(0.5f),
                contentColor = Color.DarkGray,
                disabledContainerColor = Color.Green.copy(0.5f),
                disabledContentColor = Color.DarkGray
            ),
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Filled.DoneAll,
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}

@Composable
fun SpotItemFront(
    spot: Estacionamiento
) {
    Row(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Icon(imageVector = Icons.Filled.RestartAlt, contentDescription = null, modifier = Modifier.size(100.dp))

        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = spot.nombre,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "${spot.calle}, ${spot.colonia}, ${spot.codigoPostal}",
                style = MaterialTheme.typography.bodyLarge,
                //letterSpacing = 2.sp,
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = colorScheme.tertiary,
                    modifier = Modifier
                        .size(25.dp)
                )
                Text(
                    text = spot.calificacion,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "$${spot.precio}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun DialogIniciarRecorrido(
    spot: Estacionamiento,
    homePkViewModel: HomePkViewModel,
    onDismiss: () -> Unit
) {
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
                    text = spot.nombre,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(id = R.string.lblIniciarRecorrido),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.size(16.dp))
            
            // boton comenzar
            Button(
                onClick = {
                    homePkViewModel.updateIniciarRecorrido(true)
                    homePkViewModel.updateEstacionamientoSeleccionado(spot)
                    onDismiss()
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
private fun Vehicle(homePkViewModel: HomePkViewModel) {
    val vehiculos = listOf(
        Vehiculo("58ECP3", TipoVehiculo.CARRO),
        Vehiculo("31EHP3", TipoVehiculo.MOTO),
        Vehiculo("HMN123", TipoVehiculo.TRACTOR)
    )

    Column {
        // encabezado
        Row {
            Text(
                text = stringResource(id = R.string.lblMisVehiculos),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }

        // vehiculos del usuario
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.95f)
        ) {
            if( true ){
                items( vehiculos.size/*Aqui agregar el viewmodel*/){ index ->
                    VehicleItem(index, vehiculos[index], homePkViewModel.vehiculoSeleccionado.matricula){
                        homePkViewModel.updateVehiculoSeleccionado(vehiculos[index])
                    }
                }
            } else {

            }
        }
    }
}

@Composable
private fun VehicleItem(
    index: Int,
    item: Vehiculo,
    selection: String,
    onClick: () -> Unit
) {
    val cont = if(item.matricula == selection) colorScheme.tertiary else colorScheme.primaryContainer
    val int = if(item.matricula == selection) colorScheme.onTertiary else colorScheme.onPrimaryContainer

    val icono = when(item.tipo) {
        TipoVehiculo.CARRO -> Icons.Filled.DirectionsCar
        TipoVehiculo.MOTO -> Icons.Filled.Motorcycle
        TipoVehiculo.TRACTOR -> Icons.Filled.Audiotrack
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
            Icon(
                imageVector = icono,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Text(
                text = item.matricula,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun Header(homePkViewModel: HomePkViewModel) {

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
            //.height(250.dp)
            .background(
                color = colorScheme.primary,
                RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // texto e imagen
        if(!homePkViewModel.iniciarRecorrido){
            if(!homePkViewModel.verTodo){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = headerText1, style = MaterialTheme.typography.displaySmall)
                        Text(text = headerText2, style = MaterialTheme.typography.displaySmall)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.logo_icono),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
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
                    style = MaterialTheme.typography.titleMedium
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
        
        Spacer(modifier = Modifier.size(16.dp))
    }
}

fun generaHeaderText(
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