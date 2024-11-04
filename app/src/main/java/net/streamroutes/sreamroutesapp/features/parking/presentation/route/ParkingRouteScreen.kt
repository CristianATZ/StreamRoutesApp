package net.streamroutes.sreamroutesapp.features.parking.presentation.route

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.parking.components.InformationChip

@Composable
fun ParkingRouteScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(20.126856880277188, -101.19127471960047), 17f) // San Francisco como posición inicial
    }

    val onScanPressed = {
        
    }
    
    Scaffold(

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            MapFullSize(
                cameraPositionState = cameraPositionState,
                onMapClick = {

                },
                onMapLoaded = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .align(Alignment.TopCenter)
            ) {
                // mandar polilineas
            }

            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.size(8.dp))

                IconButton(
                    onClick = onBackPressed,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = colorScheme.background,
                        contentColor = colorScheme.onBackground
                    )
                ) {
                    Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = null)
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .background(Color.Black.copy(0.5f), shapes.extraLarge),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "ITSUR",
                        style = typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f)
                    .align(Alignment.BottomCenter)
                    .background(colorScheme.background, RoundedCornerShape(topEnd = 28.dp, topStart = 28.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.lblAllowPlaces, "5"),
                        style = typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Padre Luis Gaytan $234",
                        style = typography.labelLarge,
                        modifier = Modifier.graphicsLayer(alpha = 0.5f)
                    )

                    Spacer(Modifier.size(16.dp))

                    Text(
                        text = stringResource(R.string.lblSchedule, "07:00", "23:00"),
                        style = typography.labelLarge,
                    )

                    Spacer(Modifier.size(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                    ) {
                        // distancia faltante
                        InformationChip(
                            text = stringResource(R.string.lblDistanceLeft, "400 m")
                        )

                        // tiempo faltante
                        InformationChip(
                            text = stringResource(R.string.lblTimeLeft, "20 min")
                        )

                        // precio
                        InformationChip(
                            text = stringResource(R.string.lblPriceFull, "31")
                        )
                    }

                    Spacer(Modifier.weight(1f))
                    
                    // escanear codigo qr
                    Button(
                        onClick = {
                            onScanPressed()
                        },
                        shape = shapes.small,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.btnScanCode))
                    }
                }
            }
        }
    }
}