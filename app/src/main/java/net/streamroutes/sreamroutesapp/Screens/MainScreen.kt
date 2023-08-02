@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.Dialogs.DialogAvisoDePrivacidad
import net.streamroutes.sreamroutesapp.Dialogs.DialogHabilitarContactos
import net.streamroutes.sreamroutesapp.Dialogs.DialogHabilitarUbicacion
import net.streamroutes.sreamroutesapp.Dialogs.DialogInternet
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun MainScreen(navController: NavController) {
    Main(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main( navController: NavController ){

    val context = LocalContext.current

    // Descomentar los dialogos de permiso al finalizar el proyecto
    // DIALOGOS PARA LOS PERMISOS
    val ubicacion = remember { mutableStateOf(true) }
    val contactos = remember { mutableStateOf(true) }
    val aviso = remember { mutableStateOf(true) }

    /*if( ubicacion.value ){
        DialogHabilitarUbicacion(
            dialogo = ubicacion
        ) {

        }
    }

    if( contactos.value ){
        DialogHabilitarContactos(
            dialogo = contactos
        ) {

        }
    }

    if( aviso.value ){
        DialogAvisoDePrivacidad(
            dialogo = aviso
        ) {

        }
    }*/

    // variable internet
    val internet = remember { mutableStateOf(false) }

    // funcion para saber si estas conectado a internet al iniciar la app
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            val networkCapabilities = it.activeNetwork ?: return false
            val activeNetwork =
                it.getNetworkCapabilities(networkCapabilities) ?: return false

            return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
        return false
    }

    // valor del dialog en funcion de la conexion de internet
    internet.value = !isInternetAvailable(context)

    // dialogo para la conexion a internet
    if(internet.value){
        DialogInternet(
            dialogo = internet
        ) {
            // con estas dos lineas puedes estar en la app
            // aun sin internet pero no podras usarlo
            // al momento de conectarte a internet se cambiara el estado y se cerrara el dialog
            internet.value = false
            internet.value = !isInternetAvailable(context)
        }
    }

    // CUERPO DE LA VENTANA
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        TopAppBar(
            title = {
                androidx.compose.material3.Text(
                    text = "Ciudad",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(AppScreens.MenuScreen.route) }) {
                    androidx.compose.material.Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Te enviara al menu de opciones",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Filled.Notifications,
                        contentDescription = "Te dira tus notificaciones del dia"
                    )
                }
            },
            colors = TopAppBarDefaults
                .smallTopAppBarColors(
                    containerColor = color_fondo_topappbar_alterno,
                    titleContentColor = color_letra_topappbar
                )
        )

        val itsur = LatLng(20.139468718311957, -101.15069924573676)
        val itsurState = MarkerState(position = itsur)
        val cameraPositionState = rememberCameraPositionState(){
            position = CameraPosition.fromLatLngZoom(itsur,17f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                compassEnabled = false,
                indoorLevelPickerEnabled = false,
                mapToolbarEnabled = false,
                myLocationButtonEnabled = false,
                rotationGesturesEnabled = true,
                scrollGesturesEnabled = true,
                scrollGesturesEnabledDuringRotateOrZoom = false,
                tiltGesturesEnabled = false,
                zoomControlsEnabled = false,
                zoomGesturesEnabled = true
            ),
            properties = MapProperties(
                mapStyleOptions = MapStyleOptions(stringResource(id = R.string.stylejson)),
                mapType = MapType.NORMAL,
                maxZoomPreference = 17f
            )
        ){

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainView(){
    preview()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun preview(){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        TopAppBar(
            title = {
                Text(text = "Ciudad",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Te enviara al menu de opciones"
                    )
                }
            },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Filled.Notifications,
                        contentDescription = "Te dira tus notificaciones del dia"
                    )
                }
            }
        )

        val itsur = LatLng(20.139468718311957, -101.15069924573676)
        val itsurState = MarkerState(position = itsur)
        val cameraPositionState = rememberCameraPositionState(){
            position = CameraPosition.fromLatLngZoom(itsur,17f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        ){
            Marker(
                state = itsurState,
                title = "Nuetra Universidad"
            )
        }
    }
}