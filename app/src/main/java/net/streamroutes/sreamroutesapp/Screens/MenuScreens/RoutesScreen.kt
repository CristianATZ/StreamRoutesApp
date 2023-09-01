package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import android.location.Address
import android.location.Geocoder
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import net.streamroutes.sreamroutesapp.Colores.color_botones
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_icon
import net.streamroutes.sreamroutesapp.Colores.color_letra_botones
import net.streamroutes.sreamroutesapp.Dialogs.DialogAutobusCercano
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
fun RoutesScreenView(myViewModel: MyViewModel, navController: NavController){

    val autobus = remember { mutableStateOf(true) }
    val paradas = remember { mutableStateOf(true) }
    val destino = remember { mutableStateOf(true) }

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

    Column(
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
                                    .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(15.dp))
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
                            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(15.dp))
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
    }
}

object SharedState {
    var MarcadorOrigen by mutableStateOf("")
    var MarcadorDestino by mutableStateOf("")
}

@Composable
fun map(myViewModel: MyViewModel) {

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
            .fillMaxWidth()
            .fillMaxHeight()
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
    Box(
        modifier = Modifier
            .fillMaxSize())
    {
        //Boton parte inferior
        val roundCornerShape = RoundedCornerShape(
            topEnd = 30.dp,
            bottomStart = 30.dp,
            topStart = 10.dp,
            bottomEnd = 10.dp
        )
        Button(
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            shape = roundCornerShape,
            modifier = Modifier
                .wrapContentSize()
                .padding(7.dp)
                .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = myViewModel.languageType().get(32),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
    }
}