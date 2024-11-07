package net.streamroutes.sreamroutesapp.features.booking.presentation.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.MapFullSize
import net.streamroutes.sreamroutesapp.features.components.ParkingDescription

@Composable
fun ParkingRouteBookingScreen(
    onBackPressed: () -> Unit
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(20.126856880277188, -101.19127471960047), 17f) // San Francisco como posición inicial
    }

    Box(
        modifier = Modifier
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .align(Alignment.BottomCenter)
                .background(colorScheme.background, RoundedCornerShape(topEnd = 28.dp, topStart = 28.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ParkingDescription(
                    name = "Estacionamiento 1",
                    address = "Padre Luis Gaytan #234",
                    price = null
                )

                Text(
                    text = stringResource(R.string.lblSchedule, "07:00", "23:00"),
                    style = typography.labelLarge,
                    modifier = Modifier.padding(horizontal = 16.dp).graphicsLayer(alpha = 0.5f)
                )

                Spacer(Modifier.size(8.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = colorScheme.tertiaryContainer,
                        contentColor = colorScheme.onTertiaryContainer
                    ),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.lblMetersLeft, "400"),
                            style = typography.displayMedium
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        Text(
                            text = stringResource(id = R.string.lblTimeTripAprox, "20"),
                            style = typography.bodyLarge
                        )
                    }
                }

                Spacer(Modifier.weight(1f))

                // escanear codigo qr
                Button(
                    onClick = onBackPressed,
                    shape = shapes.small,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(text = stringResource(R.string.btnBack))
                }

                Spacer(Modifier.size(16.dp))
            }
        }
    }
}