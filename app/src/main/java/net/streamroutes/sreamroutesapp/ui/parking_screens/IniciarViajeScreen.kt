package net.streamroutes.sreamroutesapp.ui.parking_screens

import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
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
import net.streamroutes.sreamroutesapp.data.model.parkinModel.Location
import net.streamroutes.sreamroutesapp.data.model.parkinModel.ParkingResultItem
import net.streamroutes.sreamroutesapp.data.model.Ruta
import net.streamroutes.sreamroutesapp.data.ORService
import net.streamroutes.sreamroutesapp.utils.BarcodeAnalyser
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
    val uiState by homePkViewModel.uiState.collectAsState()

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
            MapaRecorrido(homePkViewModel)
            HeaderIniciarViaje(homePkViewModel)

            if (!uiState.verEstacionamiento) {
                CancelarViaje(homePkViewModel)
            } else {
                RegresarViaje(homePkViewModel)
            }
        }

        AnimatedVisibility(
            visible = uiState.leerQR,
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
                homePkViewModel.updateEstacionamientoSeleccionado(ParkingResultItem(0.0,0,"","", Location(0.0,0.0),0,"", emptyList(),"",0,"", ""))
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
    val uiState by homePkViewModel.uiState.collectAsState()

    val estacionamiento = remember(uiState.estacionamientoSeleccionado.location) {
        LatLng(uiState.estacionamientoSeleccionado.location.latitude, uiState.estacionamientoSeleccionado.location.longitude)
    }
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(estacionamiento, 15f)
    }
    val ubicacion = remember { LatLng(20.141173538431637, -101.15040622126999) }

    var mapLoaded by remember { mutableStateOf(false) }

    /*if (mapLoaded) {
        LaunchedEffect(ubicacion, estacionamiento) {
            *//*calcularRuta(ubicacion, estacionamiento) { ruta ->
                val listaPuntos = mutableListOf<LatLng>()
                for (i in ruta.indices step 2) {
                    val latitud = ruta[i]
                    val longitud = ruta[i + 1]
                    listaPuntos.add(LatLng(latitud, longitud))
                }
                homePkViewModel.updateRutaEstacionamiento(listaPuntos)
            }
             *//*

            homePkViewModel.fetchBestRoute(ubicacion.toString(), estacionamiento.toString())
        }
    }*/
    LaunchedEffect(ubicacion, estacionamiento) {
        /*calcularRuta(ubicacion, estacionamiento) { ruta ->
            val listaPuntos = mutableListOf<LatLng>()
            for (i in ruta.indices step 2) {
                val latitud = ruta[i]
                val longitud = ruta[i + 1]
                listaPuntos.add(LatLng(latitud, longitud))
            }
            homePkViewModel.updateRutaEstacionamiento(listaPuntos)
        }
         */
        Log.d("ANTES", "si")
        homePkViewModel.fetchBestRoute("-101.150515,20.140496", "-101.149840,20.143444")
        //Log.d("VIAJE SCREEN", uiState.rutaEstacionamiento.features.last().geometry.coordinates.toString())
    }

    /*
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
        if (!uiState.verEstacionamiento) {
            Marker(
                state = MarkerState(position = ubicacion),
                title = "Aquí estás",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.coche_izq)
            )
            Marker(
                state = MarkerState(position = estacionamiento),
                title = "Estacionamiento",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_parking)
            )
            Polyline(
                // uiState.value.rutaEstacionamiento.features.last().geometry.coordinates.map { doubles -> LatLng(doubles.first(), doubles.last()) }
                points = uiState.rutaEstacionamiento,
                color = Color.Black,
                jointType = JointType.ROUND,
                startCap = RoundCap(),
                endCap = RoundCap()
            )
        } else {
            Marker(
                state = MarkerState(position = LatLng(20.13961981092977, -101.15076362059153)),
                title = "Estacionamiento",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_parking),
                anchor = Offset(0.5f, 0.5f)
            )
        }
    }*/
}

@Composable
private fun CancelarViaje(
    homePkViewModel: HomePkViewModel
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
                    homePkViewModel.updateLeerQR(true)
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
            Text(text = stringResource(id = R.string.lblYaLlegue), style = typography.titleMedium, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
private fun HeaderIniciarViaje(homePkViewModel: HomePkViewModel) {

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
        ParkingInfo(homePkViewModel)
    }
}

@Composable
fun ParkingInfo(homePkViewModel: HomePkViewModel) {
    val uiState by homePkViewModel.uiState.collectAsState()
    
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(colorScheme.tertiary, RoundedCornerShape(16.dp))
                .clickable {
                    homePkViewModel.updateIniciarRecorrido(false)
                    homePkViewModel.updateEstacionamientoSeleccionado(
                        ParkingResultItem(
                            0.0,
                            0,
                            "",
                            "",
                            Location(0.0, 0.0),
                            0,
                            "",
                            emptyList(),
                            "",
                            0,
                            "",
                            ""
                        ),
                    )
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
        
        Column {
            Text(
                text = uiState.estacionamientoSeleccionado.name,
                color = colorScheme.onPrimary,
                style = typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = uiState.estacionamientoSeleccionado.address,
                color = colorScheme.onPrimary,
                style = typography.titleMedium,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = "X Minutos para llegar",
                color = colorScheme.onPrimary,
                style = typography.titleMedium,
                fontWeight = FontWeight.Normal
            )
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

    val uiState by homePkViewModel.uiState.collectAsState()

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
                            parkingPkViewModel.updateEstacionamiento(uiState.estacionamientoSeleccionado)
                            parkingPkViewModel.updateTotal(uiState.estacionamientoSeleccionado.price)
                            parkingPkViewModel.updateVehiculo(uiState.vehiculoSeleccionado)
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

/*
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
}*/
