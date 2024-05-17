package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.model.Ruta
import net.streamroutes.sreamroutesapp.network.ORService
import net.streamroutes.sreamroutesapp.utils.BarcodeAnalyser
import net.streamroutes.sreamroutesapp.viewmodel.parking.Estacionamiento
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

@Composable
fun IniciarViajeScreen(homePkViewModel: HomePkViewModel, parkingPkViewModel: ParkingPkViewModel, qrScanner: () -> Unit) {
    IniciarViaje(homePkViewModel, parkingPkViewModel){
        qrScanner()
    }
}

@androidx.annotation.OptIn(ExperimentalGetImage::class)
@Composable
private fun IniciarViaje(
    homePkViewModel: HomePkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    qrScanner: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // mapa
        AnimatedVisibility(
            visible = !homePkViewModel.leerQR,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MapaRecorrido(homePkViewModel)
            HeaderIniciarViaje(homePkViewModel)

            if (!homePkViewModel.verEstacionamiento) {
                CancelarViaje(homePkViewModel)
            } else {
                RegresarViaje(homePkViewModel)
            }
        }

        AnimatedVisibility(
            visible = homePkViewModel.leerQR,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BarCodeScanner(homePkViewModel, parkingPkViewModel){
                    qrScanner()
                }

                Spacer(modifier = Modifier.size(48.dp))

                Text(
                    text = stringResource(id = R.string.lblAnalizaElCodigo),
                    style = typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
            }
        }
    }
}

@Composable
private fun RegresarViaje(homePkViewModel: HomePkViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                homePkViewModel.updateVerEstacionamiento(false)
                homePkViewModel.updateEstacionamientoSeleccionado(
                    Estacionamiento("","", "", "", "", "", "", -1)
                )
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(50.dp)
        ) {
            Text(text = stringResource(id = R.string.btnRegresarViaje))
        }

        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
private fun MapaRecorrido(homePkViewModel: HomePkViewModel) {
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(homePkViewModel.currentLocation, 15f)
    }

    val ubicacion = LatLng(20.141173538431637, -101.15040622126999)

    val destino = LatLng(20.13961981092977, -101.15076362059153)


    LaunchedEffect(key1 = Unit) {
        calcularRuta(ubicacion, destino) { ruta ->
            val listaPuntos = mutableListOf<LatLng>()
            for(i in ruta.indices step 2) {
                val latitud = ruta[i]
                val longitud = ruta[i+1]
                listaPuntos.add(LatLng(latitud, longitud))
            }
            homePkViewModel.updateRutaEstacionamiento(listaPuntos)
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
        modifier = Modifier
            .fillMaxSize()
    ) {
        if(!homePkViewModel.verEstacionamiento){
            Marker(
                state = MarkerState(position = ubicacion),
                title = "Aquí estás",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.coche_izq)
            )
            // Marcador destino
            Marker(
                state = MarkerState(position = destino),
                title = "Estacionamiento",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_parking)
            )

            Polyline(
                points = homePkViewModel.rutaEstacionamiento,
                color = Color.Black,
                jointType = JointType.ROUND,
                startCap = RoundCap(),
                endCap = RoundCap()
            )
        }

        if(homePkViewModel.verEstacionamiento){
            Marker(
                state = MarkerState(position = LatLng(20.13961981092977, -101.15076362059153)),
                title = "Estacionamiento",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_parking),
                anchor = Offset(0.5f, 0.5f)
            )
        }
    }
}

@Composable
private fun CancelarViaje(
    homePkViewModel: HomePkViewModel
) {
    val scope = rememberCoroutineScope()

    /*var openDialog by remember {
        mutableStateOf(false)
    }

    if(openDialog){
        DialogCancelarRecorrido(homePkViewModel){
            openDialog = !openDialog
        }
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                scope.launch {
                    homePkViewModel.updateLeerQR(true)
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = colorScheme.primary,
                contentColor = colorScheme.tertiary,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(50.dp)
        ) {
            Text(text = stringResource(id = R.string.lblYaLlegue))
        }

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = {
                //openDialog = !openDialog
                homePkViewModel.updateIniciarRecorrido(false)
                homePkViewModel.updateEstacionamientoSeleccionado(
                    Estacionamiento("","", "", "", "", "", "", -1)
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(50.dp)
        ) {
            Text(text = stringResource(id = R.string.btnCancelarViaje))
        }

        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
private fun DialogCancelarRecorrido(
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
                    tint = MaterialTheme.colorScheme.error,
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
                        Estacionamiento("","", "", "", "", "", "", -1)
                    )
                },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
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
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                    disabledContainerColor = MaterialTheme.colorScheme.error,
                    disabledContentColor = MaterialTheme.colorScheme.onError
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
private fun HeaderIniciarViaje(homePkViewModel: HomePkViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpotItem(
    spot: Estacionamiento,
    homePkViewModel: HomePkViewModel
) {
    val scope = rememberCoroutineScope()
    val waves = MaterialTheme.colorScheme.secondary

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

    val modifier = if(homePkViewModel.verEstacionamiento || homePkViewModel.iniciarRecorrido){
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
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
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

                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = "${spot.calle}, ${spot.colonia}, ${spot.codigoPostal}",
                        style = MaterialTheme.typography.bodyLarge,
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
                    val path = Path().apply {
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
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .size(25.dp)
                    )
                    Text(
                        text = spot.calificacion,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "$${spot.precio}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )

                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@ExperimentalGetImage
@Composable
fun BarCodeScanner(
    homePkViewModel: HomePkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    qrScanner: () -> Unit
) {
    val scope = rememberCoroutineScope()

    AndroidView({ context ->
        val cameraExecutor = Executors.newSingleThreadExecutor()
        val previewView = PreviewView(context).also {
            it.scaleType = PreviewView.ScaleType.FILL_CENTER
        }
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            val imageCapture = ImageCapture.Builder().build()

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, BarcodeAnalyser{
                        scope.launch {
                            parkingPkViewModel.updateEstacionamiento(homePkViewModel.estacionamientoSeleccionado)
                            parkingPkViewModel.updateVehiculo(homePkViewModel.vehiculoSeleccionado)
                            parkingPkViewModel.updateEstacionado(true)
                            homePkViewModel.resetViewModel()
                            delay(250)
                        }
                        qrScanner()
                    })
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    context as ComponentActivity, cameraSelector, preview, imageCapture, imageAnalyzer)

            } catch(exc: Exception) {
                //Log.e("DEBUG", "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(context))
        previewView
    },
        modifier = Modifier
            .size(width = 250.dp, height = 250.dp))
}

private fun calcularRuta(
    inicio: LatLng,
    fin: LatLng,
    callback: (List<Double>) -> Unit
){
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openrouteservice.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val puntos = mutableListOf<LatLng>()
    CoroutineScope(Dispatchers.IO).launch {
        val response = retrofit.create(ORService::class.java)
            .getRuta(
                key = "5b3ce3597851110001cf6248cf096e9bff7543a9b65bfeea90be20ac",
                start = "${inicio.longitude},${inicio.latitude}",
                end = "${fin.longitude},${fin.latitude}"
            )
        if(response.isSuccessful){
            // Log.d("SI SE PUDO", response.body().toString())
            extraccionJSON(response.body(), puntos)
            val listaPuntos = puntos.flatMap {
                listOf(it.latitude, it.longitude)
            }
            callback(listaPuntos)
        } else {
            // Log.d("NO SE PUDO LOCO", "CHEQUELE PRIMO")
        }
    }
}

private fun extraccionJSON(
    ruta: Ruta?,
    puntos: MutableList<LatLng>
) {
    ruta?.features?.firstOrNull()?.geometry?.coordinates?.forEach {
        val punto = LatLng(it[1], it[0])
        puntos.add(punto)
    }
}
