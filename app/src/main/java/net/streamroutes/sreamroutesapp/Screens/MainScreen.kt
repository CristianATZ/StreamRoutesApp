@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberMarkerState
import com.utsman.osmandcompose.CameraState
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.MarkerState
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.OverlayManagerState
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberOverlayManagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.Colores.color_letra_botones
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.getAddressInfoFromCoordinates
import net.streamroutes.sreamroutesapp.ui.theme.StreamRoutesAppTheme
import org.osmdroid.tileprovider.tilesource.ITileSource
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.CopyrightOverlay

@RequiresApi(Build.VERSION_CODES.Q)
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

@RequiresApi(Build.VERSION_CODES.Q)
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


    // valor del dialog en funcion de la conexion de internet
    /*LaunchedEffect(Unit){
        while(true){
            internet.value = !isInternetAvailable(context)
            delay(100)
        }
    }

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
    }*/

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
        drawerBackgroundColor = MaterialTheme.colorScheme.background,
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
                val cameraState = rememberCameraState {
                    geoPoint = GeoPoint(20.139468718311957, -101.15069924573676)
                    zoom = 17.0
                }
                Box(modifier = Modifier.fillMaxSize()){

                    MapBody(cameraState)

                    // Botón cambio tipo de mapa en la parte superior derecha
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ){
                        BoxOption(
                            img = painterResource(id = R.drawable.translate),
                            onBackground = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    RoundedCornerShape(percent = 10)
                                )
                                .clickable {
                                    myViewModel.idioma = if (myViewModel.idioma == 1) 0 else 1
                                }
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        BoxOption(
                            img = painterResource(id = R.drawable.change),
                            onBackground = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    RoundedCornerShape(percent = 10)
                                )
                                .clickable {
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
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    myViewModel: MyViewModel,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    TopAppBar(
        title = {
            Text(
                text = myViewModel.languageType()[172],
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer
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
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = "Te mostrara el menu",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        colors = TopAppBarDefaults
            .smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
    )
}

@RequiresApi(Build.VERSION_CODES.Q)
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
    // 0xFF195093

    Column (
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.primaryContainer
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                // crear la variable para el tema claro
                // y el tema oscuro por el color a continuaacion
                .background(
                    MaterialTheme.colorScheme.tertiary,
                    RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)
                )
                .clickable {
                    navController.navigate(AppScreens.ProfileScreen.route)
                }
        ){
            // nombre, correo y boton para cerrar el menu
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, bottom = 25.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                CustomText(
                    firstString = "Cristian Alexis Torres Zavala",
                    horizontal = Arrangement.Start,
                    size = 20,
                    modifier = Modifier.fillMaxWidth(0.95f),
                    color = MaterialTheme.colorScheme.onTertiary
                )

                CustomText(
                    firstString = "s20120154@alumnos.itsur.edu.mx",
                    horizontal = Arrangement.Start,
                    fontWeight = FontWeight.Normal,
                    size = 15,
                    modifier = Modifier.fillMaxWidth(0.95f),
                    color = MaterialTheme.colorScheme.onTertiary
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
                BoxOption(
                    img = painterResource(id = R.drawable.back),
                    onBackground = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(percent = 10)
                        )
                        .clickable {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                )
            }

            // cuadro de informacion de suscripcion
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(55.dp)
                        .background(
                            MaterialTheme.colorScheme.tertiary,
                            RoundedCornerShape(percent = 15)
                        )
                        .border(2.dp, MaterialTheme.colorScheme.onTertiary),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomText(
                        firstString = "P R E M I U M",
                        horizontal = Arrangement.Center,
                        size = 20,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            DrawerItem(text = myViewModel.languageType().get(171), icon = painterResource(id = R.drawable.premium)) {
                navController.navigate(AppScreens.SuscripcionScreen.route)
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(1.dp)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(0.5f)
                    )
                    .align(Alignment.CenterHorizontally)
            )

            var more by remember {
                mutableStateOf(false)
            }

            Column(
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessVeryLow
                        )
                    ),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                DrawerItem(
                    text = "Transporte",
                    icon = painterResource(id = R.drawable.routes),
                    selected = more
                ) {
                    more = !more
                }

                if(more){
                    Box {
                        Column(
                            modifier = Modifier
                                .height(190.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            DrawerItem(text = "Ruta mas rapida", icon = painterResource(id = R.drawable.routes)) {
                                navController.navigate(AppScreens.FastScreen.route)
                            }

                            DrawerItem(text = myViewModel.languageType().get(167), icon = painterResource(id = R.drawable.routes)) {
                                navController.navigate(AppScreens.RoutesScreen.route)
                            }

                            DrawerItem(text = myViewModel.languageType().get(164), icon = painterResource(id = R.drawable.add_location)) {
                                navController.navigate(AppScreens.TripScreen.route)
                            }

                            DrawerItem(text = "Turismo", icon = painterResource(id = R.drawable.routes)) {
                                navController.navigate(AppScreens.TurismScreen.route)
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(190.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowDropDown,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.size(32.dp))
                        }
                    }
                }

                DrawerItem(
                    text = "Chat general",
                    icon = painterResource(id = R.drawable.routes),
                ) {

                }

                DrawerItem(text = myViewModel.languageType().get(155), icon = painterResource(id = R.drawable.share_location)) {
                    if (!locationPermissionState.status.isGranted || !backgroundLocationPermissionState.status.isGranted) {
                        locationPermissionState.launchPermissionRequest()
                        backgroundLocationPermissionState.launchPermissionRequest()
                    }

                    if (!areLocationServicesEnabled(context)) {
                        Toast.makeText(context, myViewModel.languageType().get(165), Toast.LENGTH_LONG).show()
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
                                        val addressInfo = getAddressInfoFromCoordinates(context,latitude,longitude)
                                        val message = myViewModel.languageType().get(179) + addressInfo?.cityName + ", " +
                                                addressInfo?.streetName +  ", " + addressInfo?.postalCode + "\n"

                                        val shareIntent = Intent.createChooser(getShareUbi(context, message + mapUrl, myViewModel), null)
                                        context.startActivity(shareIntent)

                                    } else {
                                        Toast.makeText(context, myViewModel.languageType().get(10), Toast.LENGTH_LONG).show()
                                    }
                                }
                                .addOnFailureListener {
                                    // Manejar el error al obtener la ubicación actual
                                    Toast.makeText(context, myViewModel.languageType().get(161), Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(context, myViewModel.languageType().get(165), Toast.LENGTH_LONG).show()
                        }
                    }
                }

                DrawerItem(text = myViewModel.languageType().get(159), icon = painterResource(id = R.drawable.download)) {
                    startDownload(context = context, myViewModel = myViewModel)
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(1.dp)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(0.5f)
                    )
                    .align(Alignment.CenterHorizontally)
            )

            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                DrawerItem(text = myViewModel.languageType().get(154), icon = painterResource(id = R.drawable.share)) {
                    val shareIntent = Intent.createChooser(getShareApp(myViewModel), null)
                    context.startActivity(shareIntent)
                }

                DrawerItem(text = myViewModel.languageType().get(170), icon = painterResource(id = R.drawable.star)) {
                    navController.navigate(AppScreens.ValoranoScreen.route)
                }

                DrawerItem(text = myViewModel.languageType().get(156), icon = painterResource(id = R.drawable.settings)) {
                    navController.navigate(AppScreens.ConfigurationScreen.route)
                }

                DrawerItem(text = myViewModel.languageType().get(152), icon = painterResource(id = R.drawable.help)) {
                    navController.navigate(AppScreens.HelpScreen.route)
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(1.dp)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(0.5f)
                    )
                    .align(Alignment.CenterHorizontally)
            )

            DrawerItem(text = myViewModel.languageType().get(153), icon = painterResource(id = R.drawable.logout)) {

            }
        }
    }
}

@Composable
private fun MapBody(
    cameraPositionState: CameraState,
) {
    val context = LocalContext.current
    val overlayManagerState = rememberOverlayManagerState()

    OpenStreetMap(
        modifier = Modifier
            .fillMaxSize(),
        cameraState = cameraPositionState,
        properties = DefaultMapProperties.copy(
            maxZoomLevel = 18.0,
            minZoomLevel = 15.0,
            tileSources = TileSourceFactory.MAPNIK,
            zoomButtonVisibility = ZoomButtonVisibility.NEVER
        ),
        overlayManagerState = overlayManagerState,
        onFirstLoadListener = {
            val copyright = CopyrightOverlay(context)
            overlayManagerState.overlayManager.add(copyright)

        }
    ) {

    }
}

@Composable
private fun BoxOption(
    img: Any,
    desc: String? = null,
    onBackground: Color,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
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
                        .size(35.dp),
                    colorFilter = ColorFilter.tint(
                        onBackground
                    )
                )
            }
            is ImageVector -> {
                Icon(
                    imageVector = img,
                    contentDescription = desc,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(35.dp),
                    tint = onBackground
                )
            }
        }
    }
}

@Composable
private fun DrawerItem(
    text: String,
    icon: Painter,
    selected: Boolean = false,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 15.dp, top = 2.dp, end = 15.dp)
            .clip(RoundedCornerShape(50))
            .background(if (selected) colorScheme.onPrimary else Color.Transparent)
            .clickable(onClick = onItemClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Spacer(modifier = Modifier.width(15.dp))

        Icon(painter = icon, contentDescription = text, tint = MaterialTheme.colorScheme.onPrimaryContainer)
        
        Spacer(modifier = Modifier.width(12.dp))

        TextOption(text = text, color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}

@Composable
fun TextOption(
    text: String,
    color: Color = color_letra_botones
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
    color: Color = color_letra_botones,
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
        putExtra(Intent.EXTRA_TEXT, "https://streamroutes.com")
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
        putExtra(Intent.EXTRA_TITLE, myViewModel.languageType().get(178))
        val manager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val level: Int = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        putExtra(Intent.EXTRA_TEXT, "Bat:$level% $message\n")
        type = "text/plain"
    }
    return shareUbi
}

///////// FUNCIONES ////////

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

private fun startDownload(
    context: Context,
    pdfUrl: String = "https://drive.google.com/uc?export=download&id=1Xt85BpR47S6adKdRlh_ERVfo8TxD1fM4",
    myViewModel: MyViewModel
) {
    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    val request = DownloadManager.Request(Uri.parse(pdfUrl))
        .setTitle(myViewModel.languageType().get(158))
        .setDescription(myViewModel.languageType().get(159))
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "RutaRoute1.pdf"
        )

    downloadManager.enqueue(request)
}