package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.model.Ruta
import net.streamroutes.sreamroutesapp.network.ORService
import net.streamroutes.sreamroutesapp.viewmodel.parking.Estacionamiento
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun IniciarViajeScreen(homePkViewModel: HomePkViewModel) {
    IniciarViaje(homePkViewModel)
}

@Composable
private fun IniciarViaje(homePkViewModel: HomePkViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // mapa
        MapaRecorrido(homePkViewModel)

        // header iniciar viaje
        HeaderIniciarViaje(homePkViewModel)

        if(!homePkViewModel.verEstacionamiento){
            // cancelar viaje
            CancelarViaje(homePkViewModel)
        } else {
            // regresar al inicio
            RegresarViaje(homePkViewModel)
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
                    Estacionamiento("","", "", "", "", -1)
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

    val ubicacion = LatLng(20.139609738093373, -101.1507421629189)

    val destino = LatLng(20.13374121427186, -101.19009201321087)

    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(homePkViewModel.currentLocation, 15f)
    }


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
            mapStyleOptions = MapStyleOptions(stringResource(id = R.string.mapStyleDark)),
            maxZoomPreference = 18f,
            minZoomPreference = 15f
        ),
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Marcador ubicación
        if(homePkViewModel.rutaEstacionamiento.isNotEmpty()){
            Marker(
                state = MarkerState(position = ubicacion),
                title = "Aquí estás",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.coche_izq)
            )
            // Marcador destino
            Marker(
                state = MarkerState(position = destino),
                title = "Estacionamiento",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.parking_2)
            )

            Polyline(
                points = homePkViewModel.rutaEstacionamiento,
                color = Color.White,
                jointType = JointType.ROUND,
                startCap = RoundCap(),
                endCap = RoundCap()
            )
        }
    }
}

@Composable
private fun CancelarViaje(
    homePkViewModel: HomePkViewModel
) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    if(openDialog){
        DialogCancelarRecorrido(homePkViewModel){
            openDialog = !openDialog
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                openDialog = !openDialog
            },
            shape = RoundedCornerShape(8.dp),
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
                        Estacionamiento("","", "", "", "", -1)
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

private fun extraccionJSON(ruta: Ruta?, puntos: MutableList<LatLng>) {
    ruta?.features?.firstOrNull()?.geometry?.coordinates?.forEach {
        val punto = LatLng(it[1], it[0])
        puntos.add(punto)
    }
}