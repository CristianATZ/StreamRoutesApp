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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.BarcodeAnalyser
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ViajePkViewModel
import java.util.concurrent.Executors

@Composable
fun IniciarViajeScreen(
    viajePkViewModel: ViajePkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    navHostController: NavHostController
) {
    IniciarViaje(viajePkViewModel, parkingPkViewModel, navHostController)
}

@androidx.annotation.OptIn(ExperimentalGetImage::class)
@Composable
private fun IniciarViaje(
    viajePkViewModel: ViajePkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    navHostController: NavHostController
) {
    val uiState by viajePkViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // mapa
        AnimatedVisibility(
            visible = !uiState.leerQR,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            // mapa
            MapaRecorrido(viajePkViewModel)

            // header con informacion
            HeaderIniciarViaje(viajePkViewModel, navHostController)

            // botones llegue o regresar
            if (uiState.vehiculoSeleccionado != null) {
                CancelarViaje(viajePkViewModel)
            }
        }

        AnimatedVisibility(
            visible = uiState.leerQR,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ){
                // qr scanner y texto
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    BarCodeScanner(viajePkViewModel, parkingPkViewModel, navHostController)

                    Spacer(modifier = Modifier.size(48.dp))

                    Text(
                        text = stringResource(id = R.string.lblAnalizaElCodigo),
                        style = typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )
                }

                // boton para cerrar el scanner
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    IconButton(onClick = { viajePkViewModel.updateLeerQR(false) }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MapaRecorrido(
    viajePkViewModel: ViajePkViewModel
) {
    val uiState by viajePkViewModel.uiState.collectAsState()

    val estacionamiento = remember(uiState.estacionamientoSeleccionado!!.location) {
        LatLng(uiState.estacionamientoSeleccionado!!.location.latitude, uiState.estacionamientoSeleccionado!!.location.longitude)
    }
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(estacionamiento, 15f)
    }
    val ubicacion = remember { LatLng(20.141173538431637, -101.15040622126999) }

    var mapLoaded by remember { mutableStateOf(false) }

    if (mapLoaded) {
        LaunchedEffect(ubicacion, estacionamiento) {
            viajePkViewModel.fetchBestRoute("${ubicacion.longitude},${ubicacion.latitude}", "${estacionamiento.longitude},${estacionamiento.latitude}")
        }
    }

    GoogleMap(
        cameraPositionState = cameraPosition,
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions(stringResource(id = R.string.mapStyleLight)),
            maxZoomPreference = 18f,
            minZoomPreference = 15f
        ),
        modifier = Modifier.fillMaxSize(),
        onMapLoaded = { mapLoaded = true }
    ) {

        if(mapLoaded){
            Marker(
                state = MarkerState(position = estacionamiento),
                title = "Estacionamiento",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_parking)
            )

            if(uiState.vehiculoSeleccionado != null){
                Marker(
                    state = MarkerState(position = ubicacion),
                    title = "Aquí estás",
                    icon = BitmapDescriptorFactory.fromResource(R.drawable.coche_izq)
                )

                if(uiState.rutaEstacionamiento != null){
                    Polyline(
                        points = uiState.rutaEstacionamiento!!.features.last().geometry.coordinates.map { LatLng(it.last(), it.first()) },
                        color = Color.Black,
                        jointType = JointType.ROUND,
                        startCap = RoundCap(),
                        endCap = RoundCap()
                    )
                }
            }
        }
    }
}

@Composable
private fun CancelarViaje(
    viajePkViewModel: ViajePkViewModel
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                scope.launch {
                    viajePkViewModel.updateLeerQR(true)
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = colorScheme.tertiary,
                contentColor = colorScheme.onTertiary,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(50.dp)
        ) {
            Text(text = stringResource(id = R.string.btnLlegue), style = typography.titleMedium, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
private fun HeaderIniciarViaje(
    viajePkViewModel: ViajePkViewModel,
    navHostController: NavHostController
) {
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
        ParkingInfo(viajePkViewModel, navHostController)
    }
}

@Composable
fun ParkingInfo(viajePkViewModel: ViajePkViewModel, navHostController: NavHostController) {
    val uiState by viajePkViewModel.uiState.collectAsState()

    val time = uiState.rutaEstacionamiento?.features?.lastOrNull()?.properties?.segments?.lastOrNull()?.duration ?: 0.0

    val infoTime = "${if (time % 60 != 0.0) (time / 60).toInt() + 1 else (time / 60).toInt()} minuto(s)."

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Spacer(modifier = Modifier.size(32.dp))

        Column(
            modifier = Modifier
                .background(colorScheme.tertiary, RoundedCornerShape(16.dp))
                .clickable {
                    navHostController.popBackStack()
                }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = colorScheme.onTertiary,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.size(32.dp))

        if(time == 0.0){
            Spacer(modifier = Modifier.size(32.dp))
            CircularProgressIndicator(color = colorScheme.tertiary)
        } else {
            Column {
                Text(
                    text = uiState.estacionamientoSeleccionado!!.name,
                    color = colorScheme.onPrimary,
                    style = typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = uiState.estacionamientoSeleccionado!!.address,
                    color = colorScheme.onPrimary,
                    style = typography.titleMedium,
                    fontWeight = FontWeight.Normal
                )

                if(uiState.vehiculoSeleccionado != null){
                    Spacer(modifier = Modifier.size(16.dp))

                    Text(
                        text = infoTime,
                        color = colorScheme.onPrimary,
                        style = typography.titleMedium,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@ExperimentalGetImage
@Composable
fun BarCodeScanner(
    viajePkViewModel: ViajePkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    navHostController: NavHostController
) {
    val viajeUiState by viajePkViewModel.uiState.collectAsState()

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
                            parkingPkViewModel.agregarEstacionamiento(
                                e = viajeUiState.estacionamientoSeleccionado!!,
                                t = viajeUiState.estacionamientoSeleccionado!!.price,
                                v = viajeUiState.vehiculoSeleccionado!!
                            )

                            viajePkViewModel.updateLeerQR(false)

                            delay(250)

                            navHostController.popBackStack()
                        }
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