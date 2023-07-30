package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
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
import com.google.android.gms.maps.model.LatLng
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
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
            Column(modifier = Modifier.fillMaxSize(1f)
                .background(color_fondo_topappbar_alterno)
                .padding(top = 12.dp, end = 12.dp)){

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
                                modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(.3f)
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
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(.53f).
                            background(Color.White)
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
        val mapView = rememberMapViewWithLifecycle()

        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxHeight(.85f).fillMaxWidth(1f)
        ) { map ->
            val context = mapView.context

            // Lógica para cargar el mapa cuando esté listo
            map.getMapAsync { googleMap ->
                val latLng = LatLng(20.140522, -101.150511) // Latitud y longitud del lugar que deseas mostrar
                val zoomLevel = 67f // Nivel de zoom del mapa

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
            }
        }

        val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp, topStart = 10.dp, bottomEnd = 10.dp)
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
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
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


@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = View.generateViewId()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                mapView.onCreate(Bundle())
            }

            override fun onResume(owner: LifecycleOwner) {
                mapView.onResume()
            }

            override fun onPause(owner: LifecycleOwner) {
                mapView.onPause()
            }

            override fun onDestroy(owner: LifecycleOwner) {
                mapView.onDestroy()
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}