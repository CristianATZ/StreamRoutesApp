@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.os.Build
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_oscuro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.Dialogs.DialogInternet
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun MainScreen(myViewModel: MyViewModel, navController: NavController) {
    Main(myViewModel,navController)
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
fun Main( myViewModel: MyViewModel, navController: NavController ){
    val context = LocalContext.current
    // variable con todos los valores

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Variable para almacenar el tipo de mapa actual y su estado
    val defaultMapType = MapType.NORMAL
    var currentMapType by remember { mutableStateOf(defaultMapType) }
    var changeMap by remember { mutableStateOf(1) }

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
    }


    if( ubicacion.value ){
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
    }
*/
    // variable internet
    val internet = remember { mutableStateOf(false) }

    // funcion para saber si estas conectado a internet al iniciar la app


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
            DrawerBody(
                navController = navController,
                myViewModel = myViewModel,
                scope = scope,
                drawerState = drawerState
            )
        },
        drawerBackgroundColor = color_fondo_oscuro,
        gesturesEnabled = false
    ) {
        Scaffold(
            topBar = {
                TopBarBody(
                    myViewModel = myViewModel,
                    scope = scope,
                    drawerState = drawerState
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                val itsur = LatLng(20.139468718311957, -101.15069924573676)
                val cameraPositionState = rememberCameraPositionState{
                    position = CameraPosition.fromLatLngZoom(itsur,17f)
                }
                Box(modifier = Modifier.fillMaxSize()){

                    MapBody(cameraPositionState, currentMapType)

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
fun TopBarBody(
    myViewModel: MyViewModel,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    TopAppBar(
        title = {
            Text(
                text = myViewModel.languageType()[0],
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
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DrawerBody(
    navController: NavController,
    myViewModel: MyViewModel,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    val context = LocalContext.current

    // variable para recordar el permiso
    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val backgroundLocationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )

    // Función para verificar si el permiso de ubicación en segundo plano está habilitado
    fun isBackgroundLocationPermissionGranted(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true // En versiones anteriores a Android 10, no se requiere este permiso
        }
    }

    // Función para verificar si el permiso de ubicación precisa esta habilitado
    fun isLocationPermissionGranted(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true // En versiones anteriores a Android 10, no se requiere este permiso
        }
    }

    // Función para verificar si los servicios de ubicación están habilitados
    fun areLocationServicesEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    val color2 = Color(0xFF12417D)
    // 0xFF195093

    Column (
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                colors = listOf(color_fondo_oscuro, color2)
            )),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .clickable {
                    navController.navigate(AppScreens.ProfileScreen.route)
                }
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Menu de opciones",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )

            // opciones del menu
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                CustomText(
                    firstString = "Cristian Alexis Torres Zavala",
                    horizontal = Arrangement.Start,
                    color = color_fondo_claro,
                    size = 20,
                    modifier = Modifier.fillMaxWidth(0.95f)
                )

                CustomText(
                    firstString = "s20120154@alumnos.itsur.edu.mx",
                    horizontal = Arrangement.Start,
                    color = color_fondo_claro,
                    fontWeight = FontWeight.Normal,
                    size = 15,
                    modifier = Modifier.fillMaxWidth(0.95f)
                )
            }

            // boton de cerrar menu
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ) {
                BoxOption(img = Icons.Filled.ArrowBack) {
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            DrawerItem(text = "Version Premium", icon = Icons.Filled.Face) {
                navController.navigate(AppScreens.SuscripcionScreen.route)
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(1.dp)
                    .background(
                        color_fondo_claro
                    )
                    .align(Alignment.CenterHorizontally)
            )

            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                DrawerItem(text = "Rutas", icon = Icons.Filled.Face) {
                    navController.navigate(AppScreens.RoutesScreen.route)
                }

                DrawerItem(text = "Planifica tu viaje", icon = Icons.Filled.Face) {
                    navController.navigate(AppScreens.TripScreen.route)
                }

                DrawerItem(text = "Compartir ubicacion", icon = Icons.Filled.Face) {
                    if (!locationPermissionState.status.isGranted || !backgroundLocationPermissionState.status.isGranted) {
                        locationPermissionState.launchPermissionRequest()
                        backgroundLocationPermissionState.launchPermissionRequest()
                    }

                    if (!areLocationServicesEnabled(context)) {
                        Toast.makeText(context, "Los servicios de ubicación están deshabilitados. Por favor activa la ubicacion del dispositivo.", Toast.LENGTH_LONG).show()
                        // Mostrar un mensaje al usuario indicando que los servicios de ubicación están deshabilitados
                        // y proporcionar una opción para abrir la configuración para habilitarlos
                    } else {
                        if( isBackgroundLocationPermissionGranted(context) && isLocationPermissionGranted(context) ){
                            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                            fusedLocationClient.lastLocation
                                .addOnSuccessListener { location: Location? ->
                                    if (location != null) {
                                        val latitude = location.latitude
                                        val longitude = location.longitude

                                        // Construir la URL con el marcador en tu ubicación actual
                                        val mapUrl = "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"

                                        val message = myViewModel.languageType().get(9) + getAddressInfoFromCoordinates(context,latitude,longitude)

                                        val shareIntent = Intent.createChooser(getShareUbi(context, message + mapUrl, myViewModel), null)
                                        context.startActivity(shareIntent)

                                    } else {
                                        Toast.makeText(context, myViewModel.languageType().get(10), Toast.LENGTH_LONG).show()
                                    }
                                }
                                .addOnFailureListener {
                                    // Manejar el error al obtener la ubicación actual
                                    Toast.makeText(context, "Error al obtener la ubicación actual. Intentalo mas tarde.", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(context, "Por favor ve a la configuracion de la aplicacion y habilita los permisos de ubicacion.", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                DrawerItem(text = "Descargar rutas", icon = Icons.Filled.Face) {
                    // nothing here
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(1.dp)
                    .background(
                        color_fondo_claro
                    )
                    .align(Alignment.CenterHorizontally)
            )

            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                DrawerItem(text = "Comparte", icon = Icons.Filled.Face) {
                    val shareIntent = Intent.createChooser(getShareApp(myViewModel), null)
                    context.startActivity(shareIntent)
                }

                DrawerItem(text = "Valoranos", icon = Icons.Filled.Face) {
                    navController.navigate(AppScreens.ValoranoScreen.route)
                }

                DrawerItem(text = "Configuracion", icon = Icons.Filled.Face) {
                    navController.navigate(AppScreens.ConfigurationScreen.route)
                }

                DrawerItem(text = "Ayuda y soporte", icon = Icons.Filled.Face) {
                    navController.navigate(AppScreens.HelpScreen.route)
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(1.dp)
                    .background(
                        color_fondo_claro
                    )
                    .align(Alignment.CenterHorizontally)
            )

            DrawerItem(text = "Cerrar sesion", icon = Icons.Filled.Face) {

            }
        }
    }
}

@Composable
fun MapBody(
    cameraPositionState: CameraPositionState,
    currentMapType: MapType
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
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions(stringResource(id = R.string.stylejson)),
            mapType = currentMapType,
            maxZoomPreference = 17f
        )
    ){

    }
}

@Composable
private fun BoxOption(
    img: Any,
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
        when (img) {
            is Painter -> {
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
            is ImageVector -> {
                Icon(
                    imageVector = img,
                    contentDescription = desc,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(34.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
private fun DrawerItem(
    text: String,
    icon: ImageVector,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 15.dp, top = 2.dp, end = 15.dp)
            .clip(RoundedCornerShape(50))
            .clickable(onClick = onItemClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Spacer(modifier = Modifier.width(15.dp))

        Icon(imageVector = icon, contentDescription = text, tint = color_fondo_claro)
        
        Spacer(modifier = Modifier.width(12.dp))

        TextOption(text = text)
    }
}

@Composable
fun TextOption(
    text: String, color:
    Color = color_fondo_claro
) {
    Text(
        text = text,
        color = color,
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
private fun CustomText(
    firstString: String,
    horizontal: Arrangement.Horizontal,
    color: Color,
    fontWeight: FontWeight = FontWeight.Bold,
    size: Int,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontal
    ){
        // forgot
        Text(
            text = buildAnnotatedString{
                withStyle(style = SpanStyle(color = color,
                    fontWeight = fontWeight,
                    fontSize = size.sp,
                    fontFamily = FontFamily.SansSerif)
                ) {
                    append(firstString)
                }
            },
            modifier = Modifier
                .wrapContentWidth()
        )
    }
}

///////// SHARE SHEET ////////

// hoja para compartir la aplicacion (pagina)
fun getShareApp(
    myViewModel: MyViewModel
) : Intent{
    val shareApp: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TITLE, myViewModel.languageType().get(34))
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "https://stream_routes_official.com.mx")
        type = "text/plain"
    }
    return shareApp
}
// hoja para compartir la ubicacion
fun getShareUbi(
    context: Context,
    message: String,
    myViewModel: MyViewModel
) : Intent{
    val shareUbi: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TITLE, myViewModel.languageType().get(33))
        val manager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val level: Int = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        putExtra(Intent.EXTRA_TEXT, "Bat:$level% $message\n")
        type = "text/plain"
    }
    return shareUbi
}

///////// FUNCIONES ////////

// funcion para convertir las coordenadas en nombre de calle etc
fun getAddressInfoFromCoordinates(
    context: Context,
    latitude: Double,
    longitude: Double
): String {
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

// funcion para saber si estas contetado a internet
fun isInternetAvailable(
    context: Context
): Boolean {
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

fun checkPermissionFor(permission: String, context: Context): Boolean {
    return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}