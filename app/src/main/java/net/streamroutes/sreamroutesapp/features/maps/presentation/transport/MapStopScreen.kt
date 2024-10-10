package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.RouteInformation
import net.streamroutes.sreamroutesapp.features.components.CardOption
import net.streamroutes.sreamroutesapp.features.components.MapFullSize

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MapStopScreen(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            skipHiddenState = false
        )
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(20.126856880277188, -101.19127471960047), 17f) // San Francisco como posición inicial
    }

    // MOVER SIN AFECTAR EL ZOOM
    /*val updateCameraPosition = {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLng(
                LatLng(37.7749, -122.4194)
            )
        )
    }*/

    // MOVER AFECTANDO EL ZOOM
    /*val updateCameraZoom = {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(37.7749, -122.4194), 10f
            )
        )
    }*/

    var isMarkerPressed by remember {
        mutableStateOf(false)
    }

    val markerPressed = {
        coroutineScope.launch {
            scaffoldState.bottomSheetState.hide()// esconder la hoja
            delay(300) // esperar 300 milisegundos
            isMarkerPressed = !isMarkerPressed // cambiar el valor para cambiar el contenido de la hoja
            scaffoldState.bottomSheetState.expand() // expandir la hoja
        }
    }

    var stopSelected: RouteInformation? = null
    fun updateStop(routeInformation: RouteInformation) {
        stopSelected = routeInformation
    }
    
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            if(isMarkerPressed) {
                stopSelected?.let {
                    StopInformationBottomSheet(
                        routeInformation = it,
                        onClose = {
                            markerPressed()
                        }
                    )
                }
            } else {
                StopBottomSheet()
            }
        },
        sheetShadowElevation = 8.dp
        // CAMBIAR EL MAPA, CALLES BLANCAS
        // CONSTRUCCIONES GRIS PARA QUE SE VEA
    ) {
        MapFullSize(
            cameraPositionState = cameraPositionState,
            onMapClick = {},
            onMapLoaded = {  },
            modifier = modifier
        ) {
            Marker(
                state = MarkerState(
                    position = LatLng(20.126856880277188, -101.19127471960047)
                ),
                onClick = {
                    updateStop(
                        RouteInformation(
                            name = "Ruta 11 - El charco",
                            countTurism = 4,
                            hourAprox = 2,
                            minutesAprox = 30,
                            officialStops = 16,
                            start = "C. Pipila, Col. Ninios Herores",
                            end = "C. Francisco Marquez, Col. Zona Centro"
                        )
                    )
                    markerPressed()
                    true
                }
            )
        }
    }
}

@Composable
fun StopInformationBottomSheet(
    routeInformation: RouteInformation,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // nombre de la ruta
        Text(
            text = routeInformation.name,
            style = typography.headlineSmall
        )

        // lugares turisticos
        LazyRow(
            contentPadding = PaddingValues(16.dp)
        ) {
            items(routeInformation.officialStops) {
                CardOption(
                    modifier = Modifier.size(200.dp)
                )

                Spacer(modifier = Modifier.size(16.dp))
            }
        }

        // informacion de la ruta
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.lblRouteDetails),
                style = typography.titleLarge,
                modifier = Modifier.graphicsLayer(alpha = 0.5f)
            )

            // tiempo aproximado
            Text(
                text = stringResource(id = R.string.lblAproxTime, routeInformation.hourAprox, routeInformation.minutesAprox),
                style = typography.labelLarge
            )

            Spacer(modifier = Modifier.size(8.dp))

            // paradas oficiales
            Text(
                text = stringResource(
                    id = R.string.lblOfficialStops,
                    routeInformation.officialStops
                ),
                style = typography.labelLarge
            )

            Spacer(modifier = Modifier.size(8.dp))

            // inicio de ruta
            Text(
                text = stringResource(id = R.string.lblStartRoute, routeInformation.start),
                style = typography.labelLarge
            )

            Spacer(modifier = Modifier.size(8.dp))

            // fin de ruta
            Text(
                text = stringResource(id = R.string.lblEndRoute, routeInformation.end),
                style = typography.labelLarge
            )
        }

        // cerrar
        Button(
            onClick = onClose,
            shape = shapes.small,
            modifier = Modifier
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.btnClose))
        }
    }
}

@Composable
fun StopBottomSheet(
    totalStops: Int = 34
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.lblBusStops),
            style = typography.headlineSmall
        )

        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorScheme.tertiaryContainer,
                contentColor = colorScheme.onTertiaryContainer
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = totalStops.toString(),
                    style = typography.displayMedium
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = stringResource(id = R.string.lblStopsCount),
                    style = typography.bodyLarge
                )
            }
        }
    }
}
