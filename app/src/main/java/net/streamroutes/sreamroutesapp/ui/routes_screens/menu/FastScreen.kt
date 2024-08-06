package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import FastViewModel
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.Location
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.ui.start_screens.CustomOutlinedTextField
import net.streamroutes.sreamroutesapp.viewmodel.OrsState
import net.streamroutes.sreamroutesapp.viewmodel.OrsViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun FastScreen(
    fastViewModel: FastViewModel,
    orsViewModel: OrsViewModel,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(true) {
        systemUiController.setNavigationBarColor(Color.Black)
        systemUiController.setStatusBarColor(Color(0xFFFEFBFF))
    }

    val bottomSheetState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetContent = {
            PanelContent()//Contenido
        },
        sheetPeekHeight = 0.dp, //Altura del panel cuando esta contraido
    ) { paddingValues ->
        Scaffold (
            topBar = { TopBar(onBack) }
        ){paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                MapBodyFast(fastViewModel, orsViewModel) { latLng ->
                    fastViewModel.updateSelectedLocation(LatLng(latLng.latitude, latLng.longitude))
                }

                CalcularDestino(fastViewModel, orsViewModel,bottomSheetState, coroutineScope)
            }
        }
    }
}

@Composable
fun PanelContent() {
    // Contenido del panel deslizante
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Este es el contenido del panel deslizante")
        // Agrega más contenido según tus necesidades
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcularDestino(
    fastViewModel: FastViewModel,
    orsViewModel: OrsViewModel,
    bottomSheetState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope
) {

    val fastState by fastViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        bottomSheetState.bottomSheetState.expand()
                    }
                },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.reloj),
                    contentDescription = "Datos",
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }

        Button(
            onClick = {
                try {
                    val currentLocation = fastState.currentLocation
                    val selectedLocation = fastState.selectedLocation
                    orsViewModel.fetchRouteInfo(
                        "${currentLocation.longitude},${currentLocation.latitude}",
                        "${selectedLocation.longitude},${selectedLocation.latitude}"
                    )
                } catch (e: Exception) {
                    // Manejar la excepción aquí, por ejemplo, mostrar un mensaje de error
                    // Puedes usar un estado para mostrar un mensaje de error en la UI
                    println("Error al calcular la ruta: ${e.message}")
                }
            },
            shape = CircleShape, // Mantener el border radius
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.tertiaryContainer, // Mantener el color de fondo
                contentColor = colorScheme.onTertiaryContainer // Mantener el color del texto
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 11.dp // Aumentar la elevación para mayor resalte
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 16.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btnCalcularDestino),
                style = typography.bodyLarge.copy(
                    fontSize = 20.sp // Ajustar el tamaño del texto aquí
                )
            )
        }
    }
}

@Composable
fun MapBodyFast(
    fastViewModel: FastViewModel,
    orsViewModel: OrsViewModel,
    onMapClick: (LatLng) -> Unit
) {
    //20.139539228288044, -101.15073143400946 ITSUR
    val itsur = LatLng(19.645925, -101.230075)
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(itsur, 15f)
    }

    val orsUiState by orsViewModel.uiState.collectAsState()
    val fastState by fastViewModel.uiState.collectAsState()
    val context = LocalContext.current
    // Obtener la ubicación actual y actualizar el estado
    LaunchedEffect(Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        try {
            @SuppressLint("MissingPermission")
            val locationResult = fusedLocationClient.lastLocation
            locationResult.addOnSuccessListener { location: Location? ->
                location?.let {
                    fastViewModel.updateCurrentLocation(LatLng(it.latitude, it.longitude))
                }
            }.addOnFailureListener { e ->
                // Manejar la excepción aquí
                println("Error al obtener la ubicación: ${e.message}")
            }
        } catch (e: Exception) {
            // Manejar cualquier excepción general
            println("Error al obtener la ubicación: ${e.message}")
        }

    }

    GoogleMap(
        cameraPositionState = cameraPosition,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
        ),
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions(stringResource(id = R.string.mapStyleLight)),
            maxZoomPreference = 18f,
            minZoomPreference = 15f
        ),
        onMapClick = { onMapClick(it) },
        modifier = Modifier
            .fillMaxSize()
    ) {
        val selectedLocation = fastState.selectedLocation
        val currentLocation = fastState.currentLocation

        Marker(
            state = MarkerState(
                position = selectedLocation
            ),
            title = "Destino"
        )

        currentLocation?.let {
            Marker(
                state = MarkerState(
                    position = it
                ),
                title = "Mi localización",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.ubicacion)
            )
        }

        orsUiState.geometry?.coordinates?.let { coordinates ->
            Polyline(
                points = coordinates.map { LatLng(it[1], it[0]) },
                color = Color.Black,  // Cambiar a color negro
                width = 8f  // Hacer la línea más gruesa
            )
        }

        // Maneja el estado de la solicitud y muestra mensajes según sea necesario
        when (orsUiState.state) {
            OrsState.LOADING -> {
                // Mostrar indicador de carga
            }
            OrsState.SUCCESSFUL -> {
                // Mostrar ruta en el mapa (ya lo haces con la Polyline)
            }
            OrsState.FAILURE -> {
                // Mostrar mensaje de error
                Text(text = orsUiState.message, color = Color.Red, modifier = Modifier.padding(16.dp))
            }
            else -> {
                // No hacer nada
            }
        }
    }
}

@Composable
private fun TopBar(
    onBack: () -> Unit
) {
    var destino by remember {
        mutableStateOf("")
    }

    Row {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Te enviará al menú de opciones",
                )
            }

            CustomOutlinedTextField(
                value = destino,
                onValueChange = { destino = it },
                placeholderText = stringResource(id = R.string.txtDestino),
                leadingIcon = Icons.Filled.Search,
                ancho = 0.9f
            )
        }
    }
}

fun getResizedBitmap(context: Context, resId: Int, newWidth: Int, newHeight: Int): BitmapDescriptor {
    // Obtener el drawable
    val drawable: Drawable = context.getDrawable(resId) ?: return BitmapDescriptorFactory.defaultMarker()

    // Convertir drawable a bitmap
    val bitmap = (drawable as BitmapDrawable).bitmap

    // Redimensionar bitmap
    val resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)

    // Convertir bitmap redimensionado a BitmapDescriptor
    return BitmapDescriptorFactory.fromBitmap(resizedBitmap)
}