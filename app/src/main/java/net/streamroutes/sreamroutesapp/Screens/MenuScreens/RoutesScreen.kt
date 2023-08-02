package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_oscuro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun RoutesScreen(navController: NavController){
    RoutesScreenView(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutesScreenView(navController: NavController){
    val currentTime = remember {
        Calendar.getInstance().time
    }
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedTime = sdf.format(currentTime)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.18f)
                .background(color_fondo_topappbar_alterno) // Color de fondo del Box
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .background(color_fondo_topappbar_alterno)
                    .padding(top = 12.dp, end = 12.dp)
            ){
                Row{
                    IconButton(onClick = { }) {
                        androidx.compose.material.Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Te enviara al menu de opciones",
                            tint = Color.White
                        )
                    }
                    Column {
                        Row {
                            Text(
                                text = "Origen",
                                color = Color.White,
                                modifier = Modifier.padding(start = 2.dp)
                            )
                        }
                        Row {
                            val origen = remember { mutableStateOf("") }
                            BasicTextField(
                                value = origen.value,
                                onValueChange = {newText -> origen.value = newText },
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight(.3f)
                                    .background(Color.White)
                                    .padding(start = 12.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                            )
                        }
                    }

                }

                Row{
                    Text(
                        text="Destino",
                        color = Color.White,
                        modifier = Modifier.padding(start = 50.dp)
                    )
                }
                Row{
                    Spacer(modifier = Modifier.width(48.dp))

                    val dest = remember { mutableStateOf("") }
                    BasicTextField(
                        value = dest.value,
                        onValueChange = {newText -> dest.value = newText },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.53f)
                            .background(Color.White)
                            .padding(start = 12.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }

                Row{
                    Text(
                        text = "Hora: $formattedTime",
                        color = Color.White,
                        modifier = Modifier.padding(start = 16.dp, top = 5.dp)
                    )
                }
            }
        }

        //MAP
        map()

        Botones()

    }
}

@Composable
fun map() {

    // Mapa
    val itsur = LatLng(20.139468718311957, -101.15069924573676)
    val itsurState = MarkerState(position = itsur)
    val cameraPositionState = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(itsur, 17f)
    }

    // Variable para almacenar el tipo de mapa actual y su estado
    val defaultMapType = MapType.NORMAL
    var currentMapType by remember { mutableStateOf(defaultMapType) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.90f)
    ) {
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
            onMapClick = { latLng ->
                // Aquí podrías realizar alguna acción si lo deseas
            },
            properties = MapProperties(
                mapStyleOptions = MapStyleOptions(stringResource(id = R.string.stylejson)),
                mapType = currentMapType, // Usar el tipo de mapa actual
                maxZoomPreference = 17f
            )
        )

        // Botón cambio tipo de mapa en la parte superior derecha
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.End
        ){
            Row(
                modifier = Modifier
                    .background(color_fondo_oscuro,RoundedCornerShape(percent = 10))
                    .clickable {
                        currentMapType = if (currentMapType == MapType.NORMAL) {
                            MapType.SATELLITE
                        } else {
                            MapType.NORMAL
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.typemap),
                    contentDescription = "Tipo de mapa",
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}



@Composable
fun Botones() {

    Box(
        modifier = Modifier
            .fillMaxSize())
    {
    //Boton parte inferior
    val roundCornerShape = RoundedCornerShape(
        topEnd = 30.dp,
        bottomStart = 30.dp,
        topStart = 10.dp,
        bottomEnd = 10.dp
    )
    Button(
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF192833), // Cambiamos el color de fondo del botón aquí
            contentColor = Color.White
        ),
        shape = roundCornerShape,
        modifier = Modifier
            .wrapContentSize()
            .padding(7.dp).align(Alignment.Center)
    ) {
        Text(
            text = "Buscar",
            fontSize = 26.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
    }
}

