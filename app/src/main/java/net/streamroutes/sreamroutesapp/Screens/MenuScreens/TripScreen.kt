package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.RestoreFromTrash
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberOverlayManagerState
import net.streamroutes.sreamroutesapp.AddressInfo
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.getAddressInfoFromCoordinates
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.CopyrightOverlay

data class PlaceItem(
    val coordenadas: AddressInfo,
    val action: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun TripScreen(myViewModel: MyViewModel, navController: NavController) {
    var markers by remember { mutableStateOf(listOf<PlaceItem?>()) }
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

    val scaffoldState = rememberBottomSheetScaffoldState()

    var destino by remember {
        mutableStateOf("")
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheet(markers,myViewModel){
                removeAll()
            }
        },
        sheetDragHandle = {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowUp,
                contentDescription = null,
                modifier = Modifier
                    .padding(PaddingValues(8.dp))
            )
        },
        sheetSwipeEnabled = true,
        sheetShadowElevation = 8.dp
    ) { innerPadding ->
        // cuerpo del scaffold


        // Mapa
        val cameraState = rememberCameraState {
            geoPoint = GeoPoint(19.035229199074546, -98.23207582752717)
            zoom = 17.0
        }

        val context = LocalContext.current
        val overlayManagerState = rememberOverlayManagerState()

        var point by remember {
            mutableStateOf(GeoPoint(12,12))
        }

        Box(){
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
        }

        TopBarBody(
            myViewModel = myViewModel,
            navController = navController,
            onAdd = {
                selectedLocation?.let {

                    val ubicacion = getAddressInfoFromCoordinates(context, it.latitude, it.longitude)
                    val index = markers.size

                    val item = ubicacion?.let { it1 ->
                        PlaceItem(
                            coordenadas = it1,
                            action = { removeMarker(index) }
                        )

                    }

                    if (ubicacion != null) {
                        markers = (markers + item)!!
                    }

                    selectedLocation = null
                }
            },
            onCancel = {
                selectedLocation = null
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    markers: List<PlaceItem?>,
    myViewModel: MyViewModel,
    removeAll: () -> Unit
) {
    Spacer(modifier = Modifier.size(16.dp))

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(0.75f)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // titulo y boton borrar todo
        item { 
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = myViewModel.languageType()[346],
                    style = typography.titleLarge
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                Button(
                    onClick = { removeAll() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 30.dp
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DeleteOutline,
                        contentDescription = null,
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = myViewModel.languageType()[347],
                        style = typography.labelLarge
                    )
                }
            }
        }

        // elementos de la lista
        items(markers.size) { index ->
            val item = markers[index]
            if (item != null) {
                PlaceOption(
                    nombreCalle = "${item.coordenadas.streetName}",
                    colonia = myViewModel.languageType()[207] + ": ${item.coordenadas.neighborhood} - " + myViewModel.languageType()[208] + ": ${item.coordenadas.postalCode}",
                    numero = index + 1,
                    onRemove = {
                        item.action()
                    }
                )
            }
        }

        item {
            Column(
                modifier = Modifier
                    .padding(PaddingValues(16.dp))
            ) {
                Button(
                    onClick = {
                        removeAll()
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.tertiary,
                        contentColor = colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = myViewModel.languageType()[348],
                        style = typography.bodyLarge
                    )
                }
            }
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

    Card(
        modifier = Modifier
            .padding(PaddingValues(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = numero.toString(),
                    style = typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }

            Column {
                Text(
                    text = nombreCalle,
                    style = typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = colonia,
                    style = typography.bodySmall
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Red),
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Eliminar destino",
                        tint = Color.White,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    myViewModel: MyViewModel,
    navController: NavController,
    onAdd: () -> Unit,
    onCancel: () -> Unit
) {

    var destino by remember {
        mutableStateOf("")
    }

    Column {
        OutlinedTextField(
            value = destino,
            onValueChange = { destino = it },
            placeholder = {
                Text(text = myViewModel.languageType()[343])
            },
            leadingIcon = {
                IconButton(
                    onClick = { navController.navigate(AppScreens.MainScreen.route) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Regresar al menu principal"
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.Mic,
                        contentDescription = "Buscar por audio"
                    )
                }
            },
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorScheme.background,
                unfocusedContainerColor = colorScheme.background,
                focusedBorderColor = colorScheme.background,
                unfocusedBorderColor = colorScheme.background,
                focusedLeadingIconColor = colorScheme.onBackground,
                unfocusedLeadingIconColor = colorScheme.onBackground,
                focusedTrailingIconColor = colorScheme.onBackground,
                unfocusedTrailingIconColor = colorScheme.onBackground
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(30.dp, CircleShape)
                .padding(8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { onAdd() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.background,
                    contentColor = colorScheme.onBackground
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 30.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    tint = colorScheme.onBackground
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = myViewModel.languageType()[344],
                    style = typography.labelLarge
                )
            }
            
            Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = {
                    onCancel()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.background,
                    contentColor = colorScheme.onBackground
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 30.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.DeleteOutline,
                    contentDescription = null,
                    tint = colorScheme.onBackground
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = myViewModel.languageType()[345],
                    style = typography.labelLarge
                )
            }
        }
    }
}