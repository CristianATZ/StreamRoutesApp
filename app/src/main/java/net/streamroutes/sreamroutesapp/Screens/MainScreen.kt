@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalDrawer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material.DrawerValue
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.os.bundleOf
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
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.Colores.color_fondo_oscuro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.Dialogs.DialogAvisoDePrivacidad
import net.streamroutes.sreamroutesapp.Dialogs.DialogHabilitarContactos
import net.streamroutes.sreamroutesapp.Dialogs.DialogHabilitarUbicacion
import net.streamroutes.sreamroutesapp.Dialogs.DialogInternet
import net.streamroutes.sreamroutesapp.Dialogs.DialogTutorialMain1
import net.streamroutes.sreamroutesapp.Dialogs.DialogTutorialMain2
import net.streamroutes.sreamroutesapp.Dialogs.DialogTutorialMain3
import net.streamroutes.sreamroutesapp.Dialogs.DialogTutorialMain4
import net.streamroutes.sreamroutesapp.Dialogs.DialogTutorialMain5
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun MainScreen(myViewModel: MyViewModel, navController: NavController) {
    Main(myViewModel,navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main( myViewModel: MyViewModel, navController: NavController ){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Variable para almacenar el tipo de mapa actual y su estado
    val defaultMapType = MapType.NORMAL
    var currentMapType by remember { mutableStateOf(defaultMapType) }
    var changeMap by remember { mutableStateOf(1) }

    val context = LocalContext.current

    // Descomentar los dialogos de permiso al finalizar el proyecto
    // DIALOGOS PARA LOS PERMISOS
    val ubicacion = remember { mutableStateOf(true) }
    val contactos = remember { mutableStateOf(true) }
    val aviso = remember { mutableStateOf(true) }

    val tutorial = remember { mutableStateOf(listOf(
        mutableStateOf(true),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false)
    )) }

    /*if( tutorial.value[0].value ){
        DialogTutorialMain1(
            dialogo = tutorial.value[0],
            sigDialogo = tutorial.value[1]
        )
    } else if( tutorial.value[1].value ){
        DialogTutorialMain2(
            dialogo = tutorial.value[1],
            sigDialogo = tutorial.value[2],
            antDialogo = tutorial.value[0]
        )
    } else if( tutorial.value[2].value ){
        DialogTutorialMain3(
            dialogo = tutorial.value[2],
            sigDialogo = tutorial.value[3],
            antDialogo = tutorial.value[1]
        )
    } else if( tutorial.value[3].value ){
        DialogTutorialMain4(
            dialogo = tutorial.value[3],
            sigDialogo = tutorial.value[4],
            antDialogo = tutorial.value[2]
        )
    } else if( tutorial.value[4].value ){
        DialogTutorialMain5(
            dialogo = tutorial.value[4],
            antDialogo = tutorial.value[3]
        )
    }*/


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

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {

            // dentro del menu LOS ELEMENTOS
            Column {
                Text("Text in Drawer")
                Button(onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                }) {
                    Text("Close Drawer")
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = myViewModel.languageType().get(0),
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = "Te mostrara el menu",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults
                        .smallTopAppBarColors(
                            containerColor = color_fondo_topappbar_alterno,
                            titleContentColor = color_letra_topappbar
                        )
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                val itsur = LatLng(20.139468718311957, -101.15069924573676)
                val itsurState = MarkerState(position = itsur)
                val cameraPositionState = rememberCameraPositionState(){
                    position = CameraPosition.fromLatLngZoom(itsur,17f)
                }
                Box(modifier = Modifier.fillMaxSize()){
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
                            mapType = currentMapType,
                            maxZoomPreference = 17f
                        )
                    ){

                    }

                    // Botón cambio tipo de mapa en la parte superior derecha
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ){
                        BoxOption(img = painterResource(id = R.drawable.info)) {
                            myViewModel.idioma = if (myViewModel.idioma == 0) 1 else 0
                        }

                        Spacer(modifier = Modifier.size(10.dp))

                        BoxOption(img = painterResource(id = R.drawable.typemap)) {
                            when (changeMap) {
                                1 -> {
                                    currentMapType = MapType.NORMAL
                                }

                                2 -> {
                                    currentMapType = MapType.SATELLITE
                                }

                                3 -> {
                                    currentMapType = MapType.TERRAIN
                                }

                                4 -> {
                                    currentMapType = MapType.HYBRID
                                }
                            }
                            changeMap++
                            if (changeMap == 5) changeMap = 1
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BoxOption(
    img: Painter,
    desc: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(color_fondo_oscuro, RoundedCornerShape(percent = 10))
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Image(
            painter = img,
            contentDescription = desc,
            modifier = Modifier
                .padding(10.dp)
                .size(34.dp),
            colorFilter = ColorFilter.tint(
                Color.White
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainView(){

}
