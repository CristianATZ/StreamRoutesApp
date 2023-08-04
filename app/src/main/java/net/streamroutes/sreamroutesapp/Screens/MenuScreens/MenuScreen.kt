package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import android.Manifest
import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.BatteryManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_rows
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun MenuScreen(myViewModel: MyViewModel, navController: NavController){
    Menu(myViewModel,navController)
}

/*
variable para las coordenadas del mapa principal

0. Paradas
1. Terminales
2. Hospitales
3. Resturantes
4. Entretenimiento
*/

// te retorna el tipo de marcador
fun generaTipo(Tipo: Int): String{
    val tipos = mutableListOf<String>("Paradas","Terminales","Hospitales","Resturantes","Entretenimiento")
    return tipos[Tipo]
}

// esto es para generar las coordenadas de todos los marcadores
data class Coordenadas(val latitud: Double, val longitud: Double, val tipo: Int)

val listaCoordenadas = mutableListOf<Coordenadas>()


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun Menu(myViewModel: MyViewModel, navController: NavController){
    // variable con todos los valores

    val context = LocalContext.current

    // variable para recordar el permiso
    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    // saber si tenemos otorgados los permisos
    fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    // obtener los datos a partir de las coordenadas
    fun getAddressInfoFromCoordinates(context: Context, latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context)
        val addressList = geocoder.getFromLocation(latitude, longitude, 1)
        val address = addressList?.get(0)
        if (address != null) {
            val streetName = address.thoroughfare // Calle
            val neighborhood = address.subLocality // Colonia

            // Devolver solo la calle y la colonia en un objeto AddressInfo
            return "$streetName, $neighborhood "
        }
        return ""
    }


    Column(
        modifier = Modifier
            .background(color_fondo_claro)
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                androidx.compose.material3.Text(
                    text = myViewModel.languageType().get(1),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                androidx.compose.material3.IconButton(onClick = { navController.navigate(AppScreens.MainScreen.route) }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Te enviara a la ventana principal",
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


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            // usuario
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "", tint = Color.Black)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    TextUser(
                        text = myViewModel.languageType().get(2),
                        fontSize = 25)
                    TextUser(
                        text = myViewModel.languageType().get(3),
                        fontSize = 18,
                        fontWeight = FontWeight.Normal)
                    TextUser(
                        text = myViewModel.languageType().get(4),
                        fontSize = 18,
                        fontWeight = FontWeight.Normal)
                }
            }

            Spacer(modifier = Modifier.size(30.dp))

            // version premium
            Options(
                text = myViewModel.languageType().get(5),
                onClick = { navController.navigate(route = AppScreens.SuscripcionScreen.route) },
                roundedCornerShape = RoundedCornerShape(topEnd = 15.dp, bottomStart = 15.dp, topStart = 15.dp, bottomEnd = 15.dp))

            Spacer(modifier = Modifier.size(30.dp))

            // rutas
            Options(
                text = myViewModel.languageType().get(6),
                onClick = { navController.navigate(route = AppScreens.RoutesScreen.route) },
                roundedCornerShape = RoundedCornerShape(topEnd = 15.dp, bottomStart = 0.dp, topStart = 15.dp, bottomEnd = 0.dp))

            // planifica tu viaje
            Options(
                text = myViewModel.languageType().get(7),
                onClick = { navController.navigate(route = AppScreens.TripScreen.route) },
                roundedCornerShape = RoundedCornerShape(topEnd = 0.dp, bottomStart = 0.dp, topStart = 0.dp, bottomEnd = 0.dp))

            // compartir ubicacion
            Options(
                text = myViewModel.languageType().get(8),
                onClick = {
                    if (!locationPermissionState.status.isGranted) {
                        locationPermissionState.launchPermissionRequest()
                    }

                    val fusedLocationClient =
                        LocationServices.getFusedLocationProviderClient(context)
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location: Location? ->
                            if (location != null) {
                                val latitude = location.latitude
                                val longitude = location.longitude

                                // Construir la URL con el marcador en tu ubicación actual
                                val mapUrl = "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"

                                val message =
                                    myViewModel.languageType().get(9) + getAddressInfoFromCoordinates(context,latitude,longitude)

                                val shareIntent =
                                    Intent.createChooser(getShareUbi(context, message+mapUrl), null)
                                context.startActivity(shareIntent)

                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        myViewModel.languageType().get(10),
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            }
                        }
                        .addOnFailureListener {
                            // Error al obtener la ubicación actual
                        }
                },
                roundedCornerShape = RoundedCornerShape(topEnd = 0.dp, bottomStart = 15.dp, topStart = 0.dp, bottomEnd = 15.dp))

            Spacer(modifier = Modifier.size(30.dp))

            // comparte
            Options(
                text = myViewModel.languageType().get(11),
                onClick = {
                    val shareIntent = Intent.createChooser(getShareApp(), null)
                    context.startActivity(shareIntent)
                },
                roundedCornerShape = RoundedCornerShape(topEnd = 15.dp, bottomStart = 0.dp, topStart = 15.dp, bottomEnd = 0.dp))

            // valoranos
            Options(
                text = myViewModel.languageType().get(12),
                onClick = { navController.navigate(route = AppScreens.ValoranoScreen.route) },
                roundedCornerShape = RoundedCornerShape(topEnd = 0.dp, bottomStart = 0.dp, topStart = 0.dp, bottomEnd = 0.dp))

            // configuracion
            Options(
                text = myViewModel.languageType().get(13),
                onClick = { navController.navigate(route = AppScreens.ConfigurationScreen.route) },
                roundedCornerShape = RoundedCornerShape(topEnd = 0.dp, bottomStart = 0.dp, topStart = 0.dp, bottomEnd = 0.dp))

            // ayuda y soporte
            Options(
                text = myViewModel.languageType().get(14),
                onClick = { navController.navigate(route = AppScreens.HelpScreen.route) },
                roundedCornerShape = RoundedCornerShape(topEnd = 0.dp, bottomStart = 15.dp, topStart = 0.dp, bottomEnd = 15.dp))
        }
    }
}

// objetos composable
@Composable
private fun Options(
    text: String,
    onClick: () -> Unit,
    fontWeight: FontWeight = FontWeight.Normal,
    roundedCornerShape: RoundedCornerShape
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color_fondo_rows,
                roundedCornerShape
            )
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "ads",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 5.dp)
                .size(45.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
        Text(
            text = text, // texto
            modifier = Modifier
                .align(Alignment.CenterVertically),
            color = color_letra,
            fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
            fontWeight = fontWeight,
            fontSize = 20.sp
        )
    }
}

@Composable
private fun TextUser(
    text: String,
    fontSize: Int,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Text(
        text = text, // texto
        modifier = Modifier
            .fillMaxWidth(), // esto acapara el tamaño completo del Row=0.8f
        color = color_letra,
        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
        fontWeight = fontWeight,
        fontSize = fontSize.sp
    )
}



// checar permisos
fun checkPermissionFor(permission: String, context: Context): Boolean {
    return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}


///////// SHARE SHEET ////////

// hoja para compartir la aplicacion (pagina)
fun getShareApp() : Intent{
    val shareApp: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TITLE, "Comparte la aplicacion con tus amigos")
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "https://stream_routes_official.com.mx")
        type = "text/plain"

    }
    return shareApp
}
// hoja para compartir la ubicacion
fun getShareUbi(context: Context, message: String) : Intent{
    val shareUbi: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TITLE, "Comparte tu ubicacion en tiempo real")
        val manager = context.getSystemService(BATTERY_SERVICE) as BatteryManager
        val level: Int = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        putExtra(Intent.EXTRA_TEXT, "Bat:$level% $message\n")
        type = "text/plain"
    }
    return shareUbi
}