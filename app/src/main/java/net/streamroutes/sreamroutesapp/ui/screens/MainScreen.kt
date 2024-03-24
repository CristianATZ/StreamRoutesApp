@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.screens

import android.Manifest
import android.annotation.SuppressLint
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.GTranslate
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Museum
import androidx.compose.material.icons.outlined.Route
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ShareLocation
import androidx.compose.material.icons.outlined.SwitchAccessShortcut
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.maps.android.compose.MapType
import com.utsman.osmandcompose.CameraState
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberOverlayManagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.viewmodel.MyViewModel
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens.ConfigurationScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpScreen
import net.streamroutes.sreamroutesapp.Screens.MenuScreens.ChatScreen
import net.streamroutes.sreamroutesapp.Screens.MenuScreens.SuscripcionScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileScreen
import net.streamroutes.sreamroutesapp.Screens.Routes.FastScreen
import net.streamroutes.sreamroutesapp.Screens.Routes.RoutesScreen
import net.streamroutes.sreamroutesapp.Screens.Routes.TripScreen
import net.streamroutes.sreamroutesapp.Screens.Routes.TurismScreen
import net.streamroutes.sreamroutesapp.viewmodel.getAddressInfoFromCoordinates
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.CopyrightOverlay

enum class NavigationOptions{
    MAIN_SCREEN,
    PAQUETES_SCREEN,
    FAST_SCREEN,
    ROUTES_SCREEN,
    TRIP_SCREEN,
    TURISM_SCREEN,
    CHAT_SCREEN,
    UBI_OPTION,
    DOWNLOAD_OPTION,
    SHARE_OPTION,
    CONF_SCREEN,
    HELP_SCREEN,
    PROFILE_SCREEN
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MainScreen(myViewModel: MyViewModel, navController: NavController) {
    Main(myViewModel,navController)
}

@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun Main(myViewModel: MyViewModel, navController: NavController ){
    val context = LocalContext.current
    // variable con todos los valores

    var screen by remember {
        mutableStateOf(NavigationOptions.MAIN_SCREEN)
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Variable para almacenar el tipo de mapa actual y su estado
    val defaultMapType = MapType.NORMAL
    var currentMapType by remember { mutableStateOf(defaultMapType) }
    var changeMap by remember { mutableStateOf(1) }

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerBody(
                navController = navController,
                myViewModel = myViewModel,
                scope = scope,
                drawerState = drawerState
            ){ item ->
                screen = item
            }
        },
        //drawerBackgroundColor = MaterialTheme.colorScheme.background,
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
                    geoPoint = GeoPoint(19.057988677624586, -98.180047630148)
                    zoom = 17.0
                }

                when(screen){
                    NavigationOptions.PAQUETES_SCREEN -> SuscripcionScreen()
                    NavigationOptions.FAST_SCREEN -> FastScreen()
                    NavigationOptions.ROUTES_SCREEN -> RoutesScreen()
                    NavigationOptions.TRIP_SCREEN -> TripScreen()
                    NavigationOptions.TURISM_SCREEN -> TurismScreen()
                    NavigationOptions.CHAT_SCREEN -> ChatScreen()
                    //NavigationOptions.UBI_OPTION ->
                    //NavigationOptions.DOWNLOAD_OPTION ->
                    //NavigationOptions.SHARE_OPTION ->
                    NavigationOptions.CONF_SCREEN -> ConfigurationScreen()
                    NavigationOptions.HELP_SCREEN -> HelpScreen()
                    NavigationOptions.PROFILE_SCREEN -> ProfileScreen()
                    else -> MapBody(cameraPositionState = cameraState)
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
                text = myViewModel.languageType()[172]
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
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "Abrir el menu de opciones"
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    myViewModel.idioma = if (myViewModel.idioma == 1) 0 else 1
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.GTranslate,
                    contentDescription = "Cambiar el idioma"
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorScheme.primary,
            titleContentColor = colorScheme.onPrimary,
            navigationIconContentColor = colorScheme.onPrimary,
            actionIconContentColor = colorScheme.onPrimary
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
    drawerState: DrawerState,
    changeScreen: (NavigationOptions) -> Unit
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
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // header perfil
        HeaderProfileMenu(
            navController = navController,
            scope = scope,
            drawerState = drawerState,
            changeScreen = changeScreen
        )

        // cuerpo opciones del menu
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // premium

            Text(
                text = "Premium",
                style = typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(16.dp))
            )

            DrawerItem(
                text = myViewModel.languageType()[303],
                icon = Icons.Outlined.AttachMoney
            ) {
                changeScreen(NavigationOptions.PAQUETES_SCREEN)
            }

            // transporte

            Text(
                text = "Transporte",
                style = typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(16.dp))
            )

            DrawerItem(
                text = myViewModel.languageType()[304],
                icon = Icons.Outlined.SwitchAccessShortcut
            ) {
                changeScreen(NavigationOptions.FAST_SCREEN)
            }

            DrawerItem(
                text = myViewModel.languageType().get(167),
                icon = Icons.Outlined.DirectionsBus
            ) {
                changeScreen(NavigationOptions.ROUTES_SCREEN)
            }

            DrawerItem(
                text = myViewModel.languageType().get(164),
                icon = Icons.Outlined.Route
            ) {
                changeScreen(NavigationOptions.TRIP_SCREEN)
            }

            DrawerItem(
                text = myViewModel.languageType()[305],
                icon = Icons.Outlined.Museum
            ) {
                changeScreen(NavigationOptions.TURISM_SCREEN)
            }

            // funciones extra
            Text(
                text = "Funciones",
                style = typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(16.dp))
            )

            DrawerItem(
                text = myViewModel.languageType()[306],
                icon = Icons.Outlined.Chat,
            ) {
                changeScreen(NavigationOptions.CHAT_SCREEN)
            }

            DrawerItem(
                text = myViewModel.languageType().get(155),
                icon = Icons.Outlined.ShareLocation
            ) {
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

            DrawerItem(
                text = myViewModel.languageType().get(159),
                icon = Icons.Outlined.Download
            ) {
                startDownload(context = context, myViewModel = myViewModel)
            }

            DrawerItem(
                text = myViewModel.languageType().get(154),
                icon = Icons.Outlined.Share
            ) {
                val shareIntent = Intent.createChooser(getShareApp(myViewModel), null)
                context.startActivity(shareIntent)
            }

            /*DrawerItem(
                text = myViewModel.languageType().get(170),
                icon = Icons.Outlined.StarRate
            ) {
                navController.navigate(AppScreens.ValoranoScreen.route)
            }*/

            // configuracion y soporte

            Text(
                text = "Configuracion y soporte",
                style = typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(16.dp))
            )

            DrawerItem(
                text = myViewModel.languageType().get(156),
                icon = Icons.Outlined.Settings
            ) {
                changeScreen(NavigationOptions.CONF_SCREEN)
            }

            DrawerItem(
                text = myViewModel.languageType().get(152),
                icon = Icons.Outlined.HelpOutline
            ) {
                changeScreen(NavigationOptions.HELP_SCREEN)
            }
        }
    }
}

@Composable
fun HeaderProfileMenu(
    navController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    changeScreen: (NavigationOptions) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFE8CF41), Color(0xFFE8AA42))
                ),
                RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
            )
            .clickable {
                changeScreen(NavigationOptions.PROFILE_SCREEN)
            }

    ) {
        Column(
            modifier = Modifier
                .padding(PaddingValues(16.dp))
        ) {
            // imagen y boton de regresar
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(100))
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = colorScheme.onTertiary
                    )
                }

            }

            Spacer(modifier = Modifier.size(16.dp))

            // nombre de usuario
            Text(
                text = "Cristian Alexis Torres Zavala",
                style = typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onTertiary
            )
            Text(
                text = "s20120154@alumnos.itsur.edu.mx",
                style = typography.bodySmall,
                color = colorScheme.onTertiary
            )
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
    Card {
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
                            .size(35.dp)
                    )
                }
                is ImageVector -> {
                    Icon(
                        imageVector = img,
                        contentDescription = desc,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(35.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DrawerItem(
    text: String,
    icon: ImageVector,
    selected: Boolean = false,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onItemClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Spacer(modifier = Modifier.width(32.dp))

        Icon(imageVector = icon, contentDescription = text)

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            style = typography.bodyLarge
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