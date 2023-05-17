@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import net.streamroutes.sreamroutesapp.Navigation.AppScreens

@Composable
fun MainScreen(navController: NavController) {
    Main(navController)
}

@Composable
fun Main(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        val singapore = LatLng(1.35,103.87)
        val singaporeState = MarkerState(position = singapore)
        val cameraPositionState = rememberCameraPositionState(){
            position = CameraPosition.fromLatLngZoom(singapore,10f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        ){
            Marker(
                state = singaporeState,
                title = "Marker in singapore"
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Pantalla principal")
        // regresar
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Regresar al login")
        }
        Button(onClick = {
            navController.navigate(route = AppScreens.HelpScreen.route)
        }) {
            Text(text = "Ayuda")
        }
        Button(onClick = {
            navController.navigate(route = AppScreens.ProfileScreen.route)
        }) {
            Text(text = "Perfil")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainView(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        val singapore = LatLng(1.35,103.87)
        val singaporeState = MarkerState(position = singapore)
        val cameraPositionState = rememberCameraPositionState(){
            position = CameraPosition.fromLatLngZoom(singapore,10f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        ){
            Marker(
                state = singaporeState,
                title = "Marker in singapore"
            )
        }
    }
}