package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.ui.start_screens.CustomOutlinedTextField
import net.streamroutes.sreamroutesapp.utils.AddressInfo
import net.streamroutes.sreamroutesapp.utils.MyViewModel
import net.streamroutes.sreamroutesapp.utils.getAddressInfoFromCoordinates

data class PlaceItem(
    val coordenadas: AddressInfo,
    val action: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripScreen(
    myViewModel: MyViewModel = MyViewModel(),
    onBack: () -> Unit
) {
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

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheet(markers){
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
        topBar = {
            TopBarBody(
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
                            markers = (markers + item)
                        }
                        selectedLocation = null
                    }
                },
                onCancel = { selectedLocation = null }
            ) {
                onBack()
            }
        },
        sheetSwipeEnabled = true,
        sheetShadowElevation = 4.dp,
        sheetContainerColor = colorScheme.inverseSurface,
        sheetContentColor = colorScheme.inverseOnSurface
    ) { innerPadding ->
        // cuerpo del scaffold
        val context = LocalContext.current
        val itsur = LatLng(20.139539228288044, -101.15073143400946)
        val cameraState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(itsur, 17f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraState,
            properties = MapProperties(
                maxZoomPreference = 18f,
                minZoomPreference = 15f,
                isMyLocationEnabled = false,
                isBuildingEnabled = false
            ),
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false
            ),
            onMapClick = {
                selectedLocation = LatLng(it.latitude,it.longitude)
            }
        ) {
            selectedLocation?.let {
                Marker(state = MarkerState(position = it))
            }
        }
    }
}

@Composable
fun BottomSheet(
    markers: List<PlaceItem?>,
    removeAll: () -> Unit
) {
    Spacer(modifier = Modifier.size(16.dp))

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderRemoveAll(removeAll)
        Ubicaciones(markers)
        Planificar(removeAll)
    }
}

@Composable
fun Planificar(removeAll: () -> Unit) {
    Button(
        onClick = {
            removeAll()
        },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorScheme.tertiary,
            contentColor = colorScheme.onTertiary
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(vertical = 16.dp)
            .height(50.dp)
    ) {
        Text(
            text = stringResource(id = R.string.btnPlanificar),
            style = typography.bodyLarge
        )
    }
}

@Composable
fun Ubicaciones(
    markers: List<PlaceItem?>
) {
    Spacer(modifier = Modifier.size(16.dp))
    
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // elementos de la lista
        items(markers.size) { index ->
            val item = markers[index]
            if (item != null) {
                PlaceOption(
                    nombreCalle = "${item.coordenadas.streetName}",
                    colonia = stringResource(id = R.string.lblColonia) + ": ${item.coordenadas.neighborhood} - " + stringResource(id = R.string.lblCodigoPostal) + ": ${item.coordenadas.postalCode}",
                    numero = index + 1,
                    onRemove = {
                        item.action()
                    }
                )
            }
        }
    }
}

@Composable
fun HeaderRemoveAll(
    removeAll: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(id = R.string.lblDestinos),
            style = typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                removeAll()
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.surfaceContainerLow,
                contentColor = colorScheme.onSurface
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btnBorrarTodo),
                style = typography.bodyLarge
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

    Card(
        modifier = Modifier
            .padding(PaddingValues(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = numero.toString(),
                style = typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

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
            
            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(colorScheme.error),
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { onRemove() },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Eliminar destino",
                        tint = colorScheme.onError,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBarBody(
    onAdd: () -> Unit,
    onCancel: () -> Unit,
    onBack: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Te enviara al menu de opciones",
                )
            }

            CustomOutlinedTextField(
                value = "",
                onValueChange = {},
                placeholderText = stringResource(id = R.string.txtBuscaAqui),
                leadingIcon = Icons.Filled.Search,
                ancho = 1f
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { onAdd() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.tertiary,
                    contentColor = colorScheme.onTertiary
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = stringResource(id = R.string.btnAgregar),
                    style = typography.labelLarge
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = {
                    onCancel()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.error,
                    contentColor = colorScheme.onError
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.DeleteOutline,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = stringResource(id = R.string.btnCancelar),
                    style = typography.labelLarge
                )
            }
            
            Spacer(modifier = Modifier.size(16.dp))
        }

        Spacer(modifier = Modifier.size(8.dp))
    }
}