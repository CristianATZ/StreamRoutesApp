package net.streamroutes.sreamroutesapp.features.parking.components

import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import net.streamroutes.sreamroutesapp.utils.BarcodeAnalyser
import java.util.concurrent.Executors

@Composable
fun QrScanner(
    modifier: Modifier = Modifier,
    onSuccess: () -> Unit
) {
    AndroidView(
        { context ->
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
                        it.setAnalyzer(
                            cameraExecutor,
                            BarcodeAnalyser{
                                onSuccess()
                            }
                        )
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
        modifier = modifier
            .size(width = 250.dp, height = 250.dp)
    )
}