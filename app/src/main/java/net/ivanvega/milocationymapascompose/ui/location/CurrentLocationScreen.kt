/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.ivanvega.milocationymapascompose.ui.location

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import net.ivanvega.milocationymapascompose.network.LocationRequest
import net.ivanvega.milocationymapascompose.network.NetworkRemoteReposiroty
import net.ivanvega.milocationymapascompose.network.WebSocketViewModel
import net.ivanvega.milocationymapascompose.permission.ui.PermissionBox

@SuppressLint("MissingPermission")
@Composable
fun CurrentLocationScreen(repo: NetworkRemoteReposiroty, wsViewModel: WebSocketViewModel) {


    val permissions = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    PermissionBox(
        permissions = permissions,
        requiredPermissions = listOf(permissions.first()),
        onGranted = {
            CurrentLocationContent(
                usePreciseLocation = it.contains(Manifest.permission.ACCESS_FINE_LOCATION),
                repo = repo,
                wsViewModel = wsViewModel
            )
        },
    )
}

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun CurrentLocationContent(
    usePreciseLocation: Boolean,
    repo: NetworkRemoteReposiroty,
    wsViewModel: WebSocketViewModel
) {
    val uiState by wsViewModel.socketStatus.collectAsState()
    val messages by wsViewModel.messages.collectAsState(emptyList())
    var text by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var locationInfo by remember {
        mutableStateOf("LatLng(X,Y)")
    }

    var userLocation by remember {
        mutableStateOf(LatLng(20.139539228288044, -101.15073143400946))
    }

    var mapLoaded by remember {
        mutableStateOf(false)
    }

    var startGPS by remember {
        mutableStateOf(false)
    }

    /*LaunchedEffect(startGPS) {
       while (startGPS) {
           delay(3000)
           val petition = locationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, CancellationTokenSource().token).await()

           *//*val petition = locationClient.lastLocation.await()*//*

           Log.d("ENTRO", "Envio")
           petition.let { location ->
               repo.insertLocation(LocationRequest(location.latitude, location.longitude))
               locationInfo = LatLng(location.latitude, location.longitude).toString()
           }
       }
    }

    LaunchedEffect(!startGPS) {
        while (!startGPS) {
            delay(1000) // Espera 3 segundos
            val location = repo.getLastLocation()

            userLocation = LatLng(location.body()!!.last().latitud.toDouble(), location.body()!!.last().longitud.toDouble())

            Log.d("ENTRO", "Jalo")

            locationInfo = userLocation.toString()
        }
    }*/

    Column(
        Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /*Button(onClick = { startGPS = !startGPS }) {
            Text(text = if(startGPS) "Cancelar envios" else "Empezar envios")
        }

        Text(text = locationInfo)

        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = CameraPositionState(
                position = CameraPosition.fromLatLngZoom(userLocation, 17f)
            ),
            onMapLoaded = {
                mapLoaded = true
            }
        ){
            Marker(
                state = MarkerState(
                    position = userLocation
                )
            )
        }*/

        Button(onClick = { wsViewModel.connect() }) {
            Text(text = "Conectar")
        }

        Button(onClick = { wsViewModel.disconnect() }) {
            Text(text = "Desconectar")
        }

        Text(text = if(uiState) "CONECTADO" else "DESCONECTADO")
        
        TextField(value = text, onValueChange = {text = it})
        Button(
            onClick = {
                wsViewModel.sendText(text)
                text = ""
            }
        ) {
            Text(text = "Enviar")
        }

        Column(Modifier.fillMaxSize()) {
            LazyColumn(Modifier.weight(1f).fillMaxWidth()) {
                items(messages.size) { index ->
                    MessageItem(item = messages[index])
                }
            }
        }
    }



}

@Composable
private fun MessageItem(item: Pair<Boolean, String>) {
    val (iAmTheSender, message) = item
    Text(
        text = "${if (iAmTheSender) "You: " else "Other: "} $message",
        modifier = Modifier.padding(8.dp)
    )
}