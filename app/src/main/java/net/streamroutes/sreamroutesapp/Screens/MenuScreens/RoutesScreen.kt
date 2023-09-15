@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import android.location.Address
import android.location.Geocoder
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.google.android.filament.Material
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun RoutesScreen(myViewModel: MyViewModel, navController: NavController){
    RoutesScreenView(myViewModel,navController)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun RoutesScreenView(
    myViewModel: MyViewModel,
    navController: NavController
){

    val autobus = remember { mutableStateOf(true) }
    val paradas = remember { mutableStateOf(true) }
    var destino = remember { mutableStateOf(true) }

    var dest by remember {
        mutableStateOf("")
    }

   /* if( autobus.value ){
        DialogAutobusCercano(
            dialogo = autobus
        ) {

        }
    }

    if( paradas.value ){
        DialogParada(
            dialogo = paradas
        ) {

        }
    }

    if( destino.value ){
        DialogDestino(
            dialogo = destino
        ) {

        }
    }*/

    // variable de tiempo
    var currentTime by remember { mutableStateOf(Calendar.getInstance().time) }

    // efecto continuo para cambiar el valor de la hora
    LaunchedEffect(Unit){
        while (true) {
            delay(500) // Esperar 0.5 segundo
            currentTime = Calendar.getInstance().time // Obtener la hora actual
        }
    }

    // modificacion de la hora
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedTime = sdf.format(currentTime)

    Scaffold(
        topBar = { TopBarBody(navController) },
        floatingActionButton = { Fab(dest) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ){
            val itsur = LatLng(20.139468718311957, -101.15069924573676)
            val cameraPositionState = rememberCameraPositionState(){
                position = CameraPosition.fromLatLngZoom(itsur,17f)
            }
            
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(
                    compassEnabled = false,
                    indoorLevelPickerEnabled = false,
                    mapToolbarEnabled = false,
                    myLocationButtonEnabled = false,
                    rotationGesturesEnabled = true,
                    scrollGesturesEnabled = true,
                    scrollGesturesEnabledDuringRotateOrZoom = false,
                    tiltGesturesEnabled = false,
                    zoomControlsEnabled = false,
                    zoomGesturesEnabled = true
                ),
                properties = MapProperties(
                    mapStyleOptions = MapStyleOptions(stringResource(id = R.string.stylejson)),
                    mapType = MapType.NORMAL,
                    maxZoomPreference = 17f
                )
            ) {

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                OutlinedTextField(
                    value = dest,
                    onValueChange = {dest = it},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    },
                    trailingIcon = {
                        if(!dest.isEmpty()){
                            IconButton(
                                onClick = { dest = "" }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Clear,
                                    contentDescription = "Borrar texto de destino",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    },
                    placeholder = {
                        Text(
                            text = "Buscar destino",
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.5f)
                        )
                    },
                    shape = RoundedCornerShape(15),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        focusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                        cursorColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier
                        .height(60.dp)
                        .weight(1f)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Row(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(15)
                        )
                        .size(60.dp)
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ){
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = "Cambiar el tipo de mapa",
                        modifier = Modifier
                            .padding(10.dp)
                            .size(35.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }

    /*Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.18f)
                .background(MaterialTheme.colorScheme.primary) // Color de fondo del Box
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp, end = 12.dp)
            ){
                Row{
                    IconButton(onClick = { navController.navigate(AppScreens.MainScreen.route) }) {
                        Icon(
                            painterResource(id = R.drawable.back),
                            contentDescription = "Te enviara al menu de opciones",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Column {
                        Row {
                            Text(
                                text = myViewModel.languageType().get(29),
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.padding(start = 2.dp)
                            )
                        }
                        Row {
                            val origen = remember { mutableStateOf("") }
                            BasicTextField(
                                value = SharedState.MarcadorOrigen,//origen.value,
                                onValueChange = {newValue ->
                                    SharedState.MarcadorOrigen = newValue
                                                },
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight(.3f)
                                    .background(
                                        MaterialTheme.colorScheme.primaryContainer,
                                        RoundedCornerShape(15.dp)
                                    )
                                    .padding(start = 12.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically),
                                textStyle = TextStyle(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                        }
                    }

                }

                Row{
                    Text(
                        text=myViewModel.languageType().get(30),
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(start = 50.dp)
                    )
                }
                Row{
                    Spacer(modifier = Modifier.width(48.dp))

                    val dest = remember { mutableStateOf("") }
                    BasicTextField(
                        value = SharedState.MarcadorDestino,//dest.value,
                        onValueChange = {newValue ->
                            SharedState.MarcadorDestino = newValue},
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.53f)
                            .background(
                                MaterialTheme.colorScheme.primaryContainer,
                                RoundedCornerShape(15.dp)
                            )
                            .padding(start = 12.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically),
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }

                Row{
                    Text(
                        text = myViewModel.languageType().get(31)+ ": $formattedTime",
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(start = 16.dp, top = 5.dp)
                    )
                }
            }
        }

        //MAP
        map(myViewModel)
    }*/

}

object SharedState {
    var MarcadorOrigen by mutableStateOf("")
    var MarcadorDestino by mutableStateOf("")
}

@Composable
fun map(
    myViewModel: MyViewModel
) {

    /*
    *VARIABLES PARA EL MARCADOR DE ORIGEN
    */
    //acceder al contexto actual del componente @Composable.
    val context = LocalContext.current
    //Variable de seleccion de ubicacion
    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }

    /*
    *VARIABLES PARA EL MARCADOR DE DESTINO
    */
    //acceder al contexto actual del componente @Composable.
    val contextD = LocalContext.current
    //Variable de seleccion de ubicacion
    var selectedLocationD by remember { mutableStateOf<LatLng?>(null) }


    // Mapa
    val itsur = LatLng(20.139468718311957, -101.15069924573676)
    val cameraPositionState = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(itsur, 17f)
    }

    // Variable para almacenar el tipo de mapa actual y su estado
    val defaultMapType = MapType.NORMAL
    var currentMapType by remember { mutableStateOf(defaultMapType) }
    var changeMap by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                compassEnabled = false,
                indoorLevelPickerEnabled = false,
                mapToolbarEnabled = false,
                myLocationButtonEnabled = false,
                rotationGesturesEnabled = true,
                scrollGesturesEnabled = true,
                scrollGesturesEnabledDuringRotateOrZoom = false,
                tiltGesturesEnabled = false,
                zoomControlsEnabled = false,
                zoomGesturesEnabled = true
            ),
            onMapClick = { latLng ->
                if (selectedLocation == null) {
                    // Agregar el primer marcador
                    selectedLocation = latLng

                    // Crear un objeto Geocoder para realizar geocodificación inversa (latitud y longitud a dirección)
                    val geocoder = Geocoder(context, Locale.getDefault())

                    try {
                        // Obtener la lista de direcciones correspondientes a la latitud y longitud
                        val addresses: List<Address> = geocoder.getFromLocation(
                            latLng.latitude, latLng.longitude, 1
                        ) as List<Address>

                        if (addresses.isNotEmpty()) {
                            // Si se encontró al menos una dirección, tomar la primera
                            val address: Address = addresses[0]

                            // Construir la dirección completa a partir de las partes disponibles
                            val fullAddress = buildString {
                                address.getAddressLine(0)?.let { append(it) } // Direccion
                            }

                            // Actualizar el estado compartido con la dirección completa
                            SharedState.MarcadorOrigen = fullAddress
                        } else {
                            // Si no se encontraron direcciones, mostrar las coordenadas
                            SharedState.MarcadorOrigen = "Latitud: ${latLng.latitude}, Longitud: ${latLng.longitude}"
                        }
                    } catch (e: IOException) {
                        // Manejar errores de geocodificación
                        SharedState.MarcadorOrigen = "Latitud: ${latLng.latitude}, Longitud: ${latLng.longitude}"
                    }
                }
                //Destino MARCADOR
                else if (selectedLocationD == null) {
                    // Agregar el segundo marcador
                    selectedLocationD = latLng

                    // Crear un objeto Geocoder para realizar geocodificación inversa (latitud y longitud a dirección)
                    val geocoder = Geocoder(contextD, Locale.getDefault())

                    try {
                        // Obtener la lista de direcciones correspondientes a la latitud y longitud
                        val addresses: List<Address> = geocoder.getFromLocation(
                            latLng.latitude, latLng.longitude, 1
                        ) as List<Address>

                        if (addresses.isNotEmpty()) {
                            // Si se encontró al menos una dirección, tomar la primera
                            val address: Address = addresses[0]

                            // Construir la dirección completa a partir de las partes disponibles
                            val fullAddress = buildString {
                                address.getAddressLine(0)?.let { append(it) } // Direccion
                            }

                            // Actualizar el estado compartido con la dirección completa
                            SharedState.MarcadorDestino = fullAddress

                        } else {
                            // Si no se encontraron direcciones, mostrar las coordenadas
                            SharedState.MarcadorDestino = "Latitud: ${latLng.latitude}, Longitud: ${latLng.longitude}"
                        }
                    } catch (e: IOException) {
                        // Manejar errores de geocodificación
                        SharedState.MarcadorDestino = "Latitud: ${latLng.latitude}, Longitud: ${latLng.longitude}"
                    }
                }


            },

            properties = MapProperties(
                mapStyleOptions = MapStyleOptions(stringResource(id = R.string.stylejson)),
                mapType = currentMapType, // Usar el tipo de mapa actual
                maxZoomPreference = 17f
            )
        ){
            selectedLocation?.let {
                val originMarker = Marker(
                    state = MarkerState(position = it),
                    title = myViewModel.languageType().get(36)
                )
            }

            selectedLocationD?.let {
                val destinyMarker = Marker(
                    state = MarkerState(position = it),
                    title = "Marcador Destino"

                )
            }

            // Dibuja la polilínea si ambos marcadores están disponibles
            if (selectedLocation != null && selectedLocationD != null) {
                Polyline(
                    points = listOf(selectedLocation!!, selectedLocationD!!),
                    color = Color.Blue,
                    width = 5f
                )
            }
        }

        // Botón cambio tipo de mapa en la parte superior derecha
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.End
        ){
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(percent = 10))
                    .clickable {
                        when (changeMap) {
                            1 -> {
                                currentMapType = MapType.NORMAL
                            }

                            2 -> {
                                currentMapType = MapType.SATELLITE
                            }

                            3 -> {
                                currentMapType = MapType.TERRAIN
                            }

                            4 -> {
                                currentMapType = MapType.HYBRID
                            }
                        }
                        changeMap++
                        if (changeMap == 5) changeMap = 1
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.change),
                    contentDescription = "Tipo de mapa",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(34.dp),
                    colorFilter = ColorFilter.tint(
                        MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
        Botones(myViewModel)
    }
}

@Composable
fun Botones(
    myViewModel: MyViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            shape = RoundedCornerShape(percent = 40),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 5.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp)
        ) {
            Text(
                text = "Buscar",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Fab(
    destino: String
) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    if(openDialog){
        DialogBody(){
            openDialog = !openDialog
        }
    }

    ExtendedFloatingActionButton(
        modifier = Modifier
            .padding(16.dp),
        text = {
            Text(
                text = if (destino.isEmpty()) "Rutas" else "Buscar"
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
                    .size(34.dp)
            )
        },
        onClick = {
            if( destino.isEmpty() ) openDialog = !openDialog
            else {
                // buscar una ruta
            }
        },
        containerColor = MaterialTheme.colorScheme.tertiary
    )
}

@ExperimentalMaterial3Api
@Composable
fun DialogBody(
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.surface,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RutasHeader(
                    tittle = "Ciudad",
                    onClick = {
                        onClick()
                    }
                )
                RutasBody()
            }
        }
    }
}

@Composable
fun RutasHeader(
    tittle: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = "Icono para cerrar el dialogo",
                modifier = Modifier
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = tittle,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    Divider(
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Preview
@Composable
fun RutasBody() {
    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ){
                RouteInformation()
                Spacer(Modifier.weight(1f))
                RouteButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if(expanded){
                // inicio y destino
                RouteDirection()
                // paradas
                RouteStops()
            }
        }
    }

    // menu de iconos
    IconsMenu()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteStops(
    listOf: List<String> = listOf("","","","","")
) {
    Text(
        text = "Paradas",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp)
    )

    LazyRow(
        modifier = Modifier
            .padding(bottom = 8.dp)
    ){
        items(listOf.size){ index ->
            AssistChip(
                onClick = {},
                label = {
                    Text(text = listOf[index])
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    labelColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                border = AssistChipDefaults.assistChipBorder(
                    borderColor = MaterialTheme.colorScheme.tertiaryContainer,
                    borderWidth = 0.dp
                ),
                elevation = AssistChipDefaults.elevatedAssistChipElevation(
                    defaultElevation = 5.dp
                )
            )
        }
    }
}

@Composable
fun IconsMenu(
    modifier: Modifier = Modifier,
    onClickFav: () -> Unit = {},
    onClickAdd: () -> Unit = {},
    onClickShare: () -> Unit = {}
) {
    // iconos de accion FAV, agregar a la ruta como destino
    Card(
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp,MaterialTheme.colorScheme.onSurface),
        modifier = Modifier
            .padding(top = 8.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // fav
            IconButton(
                onClick = onClickFav
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Icono de favorito",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier
                .height(50.dp)
                .width(1.dp)
                .background(MaterialTheme.colorScheme.onSurfaceVariant))

            // add
            IconButton(
                onClick = onClickAdd
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Icono de agregar como destino",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier
                .height(50.dp)
                .width(1.dp)
                .background(MaterialTheme.colorScheme.onSurfaceVariant))

            // share
            IconButton(
                onClick = onClickShare
            ) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = "Icono de compartir ruta",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun RouteDirection(
    modifier: Modifier = Modifier,
    start: String = "",
    end: String = ""
) {
    Divider()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = "Inicio:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = start,
            fontSize = 12.sp
        )
        Text(
            text = "Destino:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = end,
            fontSize = 12.sp
        )
    }

    Divider()
}

@Composable
fun RouteButton(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = "Mas informacion",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun RouteInformation(
    name: String = "Nombre Ruta",
    duration: String = "Duracion Ruta",
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = duration,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBody(
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Rutas",
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.navigate(AppScreens.MainScreen.route) }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
        )
    )
}