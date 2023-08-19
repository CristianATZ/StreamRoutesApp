package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import android.content.Context
import android.location.Geocoder
import android.widget.Toast
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.AddressInfo
import net.streamroutes.sreamroutesapp.Colores.color_botones
import net.streamroutes.sreamroutesapp.Colores.color_botones_alt
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_icon
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_botones
import net.streamroutes.sreamroutesapp.Colores.color_letra_textfield
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.Colores.color_letra_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letrain
import net.streamroutes.sreamroutesapp.Colores.color_letraout
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.getAddressInfoFromCoordinates

@Preview ( showBackground = true )
@Composable
fun view() {

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TripScreen(myViewModel: MyViewModel, navController: NavController) {
    var markers by remember { mutableStateOf(listOf<AddressInfo>()) }
    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }
    val context = LocalContext.current

    // funcion para remover
    fun removeMarker(index: Int) {
        if (index in markers.indices) {
            markers = markers.toMutableList().apply {
                removeAt(index)
            }
        }
    }

    // remueve todos los elementos
    fun removeAll(){
        markers = emptyList()
    }


    Scaffold(
        topBar = { TopBarBody(myViewModel,navController) },
        containerColor = color_fondo
    ) { paddingValues ->
        // cuerpo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                            title = myViewModel.languageType().get(36)
                        )
                    }
                }

                // barra de busqueda
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
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
                            placeholder = myViewModel.languageType().get(37),
                            size = 70
                        )
                    }
                }

                val roundCornerShape = CircleShape
                Button(
                    onClick = {
                        selectedLocation?.let {

                            val ubicacion = getAddressInfoFromCoordinates(context, it.latitude, it.longitude)

                            if (ubicacion != null) {
                                markers = markers + ubicacion
                            }

                            selectedLocation = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color_fondo_topbar
                    ),
                    shape = roundCornerShape,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "", tint = color_icon)
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
                        text = myViewModel.languageType().get(38),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = color_letraout,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

                // Elementos principales (la lista de lugares)
                items(markers.size) { index ->
                    val location = markers[index]
                    PlaceOption(
                        nombreCalle = "${location.streetName}",
                        colonia = myViewModel.languageType().get(39) + ": ${location.neighborhood} - " + myViewModel.languageType().get(40) + ": ${location.postalCode}",
                        numero = index + 1,
                        onRemove = {
                            removeMarker(index)
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
                        Toast.makeText(context, myViewModel.languageType().get(41) + " ${markers.size} " + myViewModel.languageType().get(42), Toast.LENGTH_LONG).show()
                        removeAll()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color_botones, // Cambiamos el color de fondo del botón aquí
                        contentColor = color_letra_botones
                    ),
                    shape = roundCornerShape,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp),
                ) {
                    Text(
                        text = myViewModel.languageType().get(43),
                        fontSize = 26.sp,
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
            .background(color_fondo_topbar, roundedCornerShape)
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
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "",
                modifier = Modifier.size(35.dp),
                tint = color_icon
            )

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
                    color = color_letra_topbar,
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
                            .background(color_fondo_topbar, roundedCornerShape)
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        if (text.text.isEmpty()) {
                            Text(
                                text = placeholder,
                                fontSize = 18.sp,
                                color = color_letra_topbar,
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
            .heightIn(70.dp)
            .background(
                color_fondo_topbar,
                RoundedCornerShape(
                    topEnd = 10.dp,
                    bottomStart = 10.dp,
                    topStart = 10.dp,
                    bottomEnd = 10.dp
                )
            )
            .padding(vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = numero.toString(),
                fontSize = 20.sp,
                color = color_letra_topbar,
                fontWeight = FontWeight.Bold
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.70f)
            ){
                Text(
                    text = nombreCalle,
                    fontSize = 16.sp,
                    color = color_letra_topbar,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = colonia,
                    fontSize = 12.sp,
                    color = color_letra_topbar
                )
            }

            IconButton(
                onClick = { onRemove() },
                modifier = Modifier
                    .size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = color_letrain,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.size(10.dp))
        }
    }
    Spacer(modifier = Modifier.size(15.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    myViewModel: MyViewModel,
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                text = myViewModel.languageType().get(35),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(AppScreens.MainScreen.route) }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Te enviara al menu de opciones",
                    tint = color_icon
                )
            }
        },
        colors = TopAppBarDefaults
            .smallTopAppBarColors(
                containerColor = color_fondo_topbar,
                titleContentColor = color_letra_topbar
            )
    )
}