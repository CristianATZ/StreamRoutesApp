package net.streamroutes.sreamroutesapp.features.parking.presentation.qr

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.compose.orange
import com.example.compose.yellow

@Composable
fun ParkingQrScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {
    val background = listOf(orange, yellow)

    var onScannSuccess by remember { mutableStateOf(false) }

    val onSuccess = {
        onScannSuccess = true
    }

    val onAcceptPressed = {

    }

    Scaffold(

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(background)
                )
        ) {
            Crossfade(
                targetState = onScannSuccess,
                animationSpec = tween(1000),
                label = ""
            ) { isScanned ->
                if (isScanned) {
                    SuccessfullScanScreen(
                        onAcceptPressed = onAcceptPressed
                    )
                } else {
                    ScannerScreen(
                        onSuccess = onSuccess,
                        onBackPressed = onBackPressed
                    )
                }
            }
        }
    }
}