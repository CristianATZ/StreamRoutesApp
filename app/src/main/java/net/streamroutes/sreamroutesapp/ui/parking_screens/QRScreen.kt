package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.activity.ComponentActivity
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.BarcodeAnalyser
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ViajePkViewModel
import java.util.concurrent.Executors

@OptIn(ExperimentalGetImage::class)
@Composable
fun QRScreen(
    viajePkViewModel: ViajePkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    navHostController: NavHostController
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