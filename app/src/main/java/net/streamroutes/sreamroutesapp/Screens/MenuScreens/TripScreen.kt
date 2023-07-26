package net.streamroutes.sreamroutesapp.Screens.MenuScreens

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_textfield
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Preview ( showBackground = true )
@Composable
fun view() {

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TripScreen(navController: NavController) {
    var markers by remember { mutableStateOf(listOf<LatLng>()) }
    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }

    // funcion para remover
    fun removeMarker(location: LatLng) {
        markers = markers.filter { it != location }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ) {
        // top app bar
        TopAppBar(
            title = {
                Text(
                    text = "Planifica tu viaje",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(AppScreens.MenuScreen.route) }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Te enviara al menu de opciones",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults
                .smallTopAppBarColors(
                    containerColor = color_fondo_topappbar_alterno,
                    titleContentColor = color_letra_topappbar
                )
        )

        // cuerpo
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            ){
                // Mapa
                val itsur = LatLng(20.139468718311957, -101.15069924573676)
                val itsurState = MarkerState(position = itsur)
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
                    onMapClick = { latLng ->
                        selectedLocation = latLng
                    },
                    properties = MapProperties(
                        mapStyleOptions = MapStyleOptions(stringResource(id = R.string.stylejson)),
                        mapType = MapType.NORMAL,
                        maxZoomPreference = 17f
                    )
                ){
                    selectedLocation?.let {
                        Marker(
                            state = MarkerState(position = it),
                            title = "Ubicación seleccionada"
                        )
                    }
                }

                // barra de busqueda
                Column() {
                    Spacer(modifier = Modifier.size(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        var searchText by remember { mutableStateOf("") }
                        val keyboardController = LocalSoftwareKeyboardController.current

                        val onSearch: (String) -> Unit = { text ->
                            searchText = text
                            keyboardController?.hide()
                        }
                        SearchBar(
                            onSearch = onSearch,
                            placeholder = "Localizacion",
                            size = 70
                        )
                    }
                }

                val roundCornerShape = RoundedCornerShape(topEnd = 15.dp, bottomStart = 15.dp, topStart = 15.dp, bottomEnd = 15.dp)
                Button(
                    onClick = {
                        selectedLocation?.let {
                            markers = markers + it
                            selectedLocation = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF192833)
                    ),
                    shape = roundCornerShape,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription = "", tint = Color.White)
                }
            }


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize() // Aseguramos que el LazyColumn tenga un tamaño acotado
                    .weight(1f), // Permitimos que el LazyColumn se expanda para ocupar el espacio restante
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // encabezado
                item {
                    Text(
                        text = "Lugares seleccionados",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

                // Elementos principales (la lista de lugares)
                items(markers.size) { index ->
                    val location = markers[index]
                    PlaceOption(
                        nombreCalle = "Latitud: ${location.latitude}",
                        colonia = "Longitud: ${location.longitude}",
                        numero = index + 1,
                        onRemove = {
                            removeMarker(location)
                        }
                    )
                }
            }


            // Botón de aceptar
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp, topStart = 10.dp, bottomEnd = 10.dp)
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF192833), // Cambiamos el color de fondo del botón aquí
                        contentColor = Color.White
                    ),
                    shape = roundCornerShape,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp),
                ) {
                    Text(
                        text = "PLANEAR",
                        fontSize = 26.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchBar(
    onSearch: (String) -> Unit,
    placeholder: String,
    singleLine: Boolean = true,
    size: Int,
    backgroundColor: Color = Color(0xFF192833),
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(percent = 10)
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .background(backgroundColor, roundedCornerShape)
    ) {
        Row(
            modifier = Modifier
                .height(size.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            // icono
            Icon(imageVector = Icons.Filled.Search, contentDescription = "", modifier = Modifier.size(35.dp), tint = Color.White)

            // caja de texto
            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                singleLine = singleLine,
                modifier = Modifier
                    .height(size.dp)
                    .padding(4.dp),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                    color = color_letra_topappbar,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    onSearch(text.text)
                    // Hide the keyboard after submitting the search
                    keyboardController?.hide()
                    // or hide keyboard
                    focusManager.clearFocus()

                }),
                decorationBox = { innerTextField ->
                    Row(
                        Modifier
                            .background(backgroundColor, roundedCornerShape)
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        if (text.text.isEmpty()) {
                            Text(
                                text = placeholder,
                                fontSize = 18.sp,
                                color = color_letra_topappbar,
                                letterSpacing = 3.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)

                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}

@Composable
private fun PlaceOption(
    nombreCalle: String,
    colonia: String,
    numero: Int,
    onRemove: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(70.dp)
            .background(
                Color(0xFF192833),
                RoundedCornerShape(
                    topEnd = 10.dp,
                    bottomStart = 10.dp,
                    topStart = 10.dp,
                    bottomEnd = 10.dp
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = numero.toString(),
                fontSize = 16.sp,
                color = color_letra_topappbar,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.70f)
            ){
                Text(
                    text = nombreCalle,
                    fontSize = 16.sp,
                    color = color_letra_topappbar,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = colonia,
                    fontSize = 12.sp,
                    color = color_letra_topappbar
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize(0.85f)
                    .background(
                        color_letra_textfield,
                        RoundedCornerShape(
                            topEnd = 10.dp,
                            bottomStart = 10.dp,
                            topStart = 10.dp,
                            bottomEnd = 10.dp
                        )
                    )
                    .clickable { onRemove() },
                contentAlignment = Alignment.Center
            ){
                Icon(imageVector = Icons.Filled.Close, contentDescription = null, tint = color_letra, modifier = Modifier.fillMaxSize(1f))
            }
        }
    }
    Spacer(modifier = Modifier.size(15.dp))
}