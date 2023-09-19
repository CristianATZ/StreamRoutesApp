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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
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
import com.google.maps.android.compose.rememberCameraPositionState
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberOverlayManagerState
import net.streamroutes.sreamroutesapp.AddressInfo
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.getAddressInfoFromCoordinates
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.CopyrightOverlay

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
        containerColor = MaterialTheme.colorScheme.background
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
                val cameraState = rememberCameraState {
                    geoPoint = GeoPoint(20.139468718311957, -101.15069924573676)
                    zoom = 17.0
                }

                val context = LocalContext.current
                val overlayManagerState = rememberOverlayManagerState()

                var point by remember {
                    mutableStateOf(GeoPoint(12,12))
                }


                OpenStreetMap(
                    modifier = Modifier
                        .fillMaxSize(),
                    cameraState = cameraState,
                    properties = DefaultMapProperties.copy(
                        maxZoomLevel = 18.0,
                        minZoomLevel = 15.0,
                        tileSources = TileSourceFactory.MAPNIK,
                        zoomButtonVisibility = ZoomButtonVisibility.NEVER
                    ),
                    overlayManagerState = overlayManagerState,
                    onFirstLoadListener = {
                        val copyright = CopyrightOverlay(context)
                        overlayManagerState.overlayManager.add(copyright)

                    },
                    onMapClick = {
                        selectedLocation = LatLng(it.latitude,it.longitude)
                    }
                ) {
                    selectedLocation?.let {
                        com.utsman.osmandcompose.Marker(
                            state = com.utsman.osmandcompose.MarkerState(
                                geoPoint = GeoPoint(selectedLocation!!.latitude, selectedLocation!!.longitude)
                            )
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

                        OutlinedTextField(
                            value = searchText,
                            onValueChange = {searchText = it},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            },
                            trailingIcon = {
                                if(!searchText.isEmpty()){
                                    IconButton(
                                        onClick = { searchText = "" }
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
                    }
                }

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
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize() // Aseguramos que el LazyColumn tenga un tamaño acotado
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.background), // Permitimos que el LazyColumn se expanda para ocupar el espacio restante
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // encabezado
                item {
                    Text(
                        text = myViewModel.languageType().get(38),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
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
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    shape = roundCornerShape,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp),
                ) {
                    Text(
                        text = myViewModel.languageType().get(43),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiary
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
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(percent = 10)
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier
            .height(size.dp)
            .fillMaxWidth(0.85f)
            .background(MaterialTheme.colorScheme.tertiary, roundedCornerShape),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        // icono
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = MaterialTheme.colorScheme.onTertiary
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
                color = MaterialTheme.colorScheme.onTertiary,
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
                        .background(MaterialTheme.colorScheme.tertiary, roundedCornerShape)
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    if (text.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onTertiary.copy(0.5f),
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
                MaterialTheme.colorScheme.primary,
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
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.70f)
            ){
                Text(
                    text = nombreCalle,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = colonia,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimary
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
                    tint = MaterialTheme.colorScheme.error,
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
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(AppScreens.MainScreen.route) }) {
                Icon(
                    painterResource(id = R.drawable.back),
                    contentDescription = "Te enviara al menu de opciones",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults
            .smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
    )
}