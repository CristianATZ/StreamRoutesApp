@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import FastViewModel
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DownloadForOffline
import androidx.compose.material.icons.filled.EmergencyShare
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.DownloadForOffline
import androidx.compose.material.icons.outlined.EmergencyShare
import androidx.compose.material.icons.outlined.FlashOn
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Museum
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.getAddressInfoFromCoordinates
import net.streamroutes.sreamroutesapp.viewmodel.OrsViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.ConfigurationViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.MainViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.RoutesViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.Tema

enum class RoutesNavigationOptions{
    HOME_SCREEN,
    PREMIUM_SCREEN,
    FAST_SCREEN,
    ROUTES_SCREEN,
    TRIP_SCREEN,
    TURISM_SCREEN,
    CHAT_SCREEN,
    UBI_OPTION,
    DOWNLOAD_OPTION,
    CONF_SCREEN,
    HELP_SCREEN,
    PROFILE_SCREEN
}

data class RoutesNavigationItem(
    val option: RoutesNavigationOptions,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector,
    @StringRes val label: Int
)

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    configurationViewModel: ConfigurationViewModel,
    fastViewModel: FastViewModel,
    orsViewModel: OrsViewModel,
    routesViewModel: RoutesViewModel
) {
    Main(configurationViewModel, fastViewModel, orsViewModel, routesViewModel)
}

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Main(
    configurationViewModel: ConfigurationViewModel,
    fastViewModel: FastViewModel,
    orsViewModel: OrsViewModel,
    routesViewModel: RoutesViewModel
){
    val context = LocalContext.current

    var routeScreen by remember {
        mutableStateOf(RoutesNavigationOptions.HOME_SCREEN)
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // variable para recordar el permiso
    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    val backgroundLocationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )

    // lista para el menu de opciones
    val routesNavigationList = listOf(
        RoutesNavigationItem(RoutesNavigationOptions.PROFILE_SCREEN, Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle, R.string.lblProfile),
        RoutesNavigationItem(RoutesNavigationOptions.HOME_SCREEN, Icons.Filled.Home, Icons.Outlined.Home, R.string.lblHome),
        RoutesNavigationItem(RoutesNavigationOptions.PREMIUM_SCREEN, Icons.Filled.MonetizationOn, Icons.Outlined.MonetizationOn, R.string.lblSuscription),
        RoutesNavigationItem(RoutesNavigationOptions.FAST_SCREEN, Icons.Filled.FlashOn, Icons.Outlined.FlashOn, R.string.lblFast),
        RoutesNavigationItem(RoutesNavigationOptions.ROUTES_SCREEN, Icons.Filled.DirectionsBus, Icons.Outlined.DirectionsBus, R.string.lblTransportRoutes),
        RoutesNavigationItem(RoutesNavigationOptions.TRIP_SCREEN, Icons.Filled.Bookmark, Icons.Outlined.Bookmark, R.string.lblTrip),
        RoutesNavigationItem(RoutesNavigationOptions.TURISM_SCREEN, Icons.Filled.Museum, Icons.Outlined.Museum, R.string.lblTurism),
        RoutesNavigationItem(RoutesNavigationOptions.CHAT_SCREEN, Icons.Filled.ChatBubble, Icons.Outlined.ChatBubble, R.string.lblChat),
        RoutesNavigationItem(RoutesNavigationOptions.UBI_OPTION, Icons.Filled.EmergencyShare, Icons.Outlined.EmergencyShare, R.string.lblShare),
        RoutesNavigationItem(RoutesNavigationOptions.DOWNLOAD_OPTION, Icons.Filled.DownloadForOffline, Icons.Outlined.DownloadForOffline, R.string.lblDownload),
        RoutesNavigationItem(RoutesNavigationOptions.CONF_SCREEN, Icons.Filled.Settings, Icons.Outlined.Settings, R.string.lblConfiguration),
        RoutesNavigationItem(RoutesNavigationOptions.HELP_SCREEN, Icons.Filled.Help, Icons.Outlined.Help, R.string.lblHelp),
    )

    val systemUiController = rememberSystemUiController()

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(selectedScreen = routeScreen, items = routesNavigationList){ item ->
                scope.launch(Dispatchers.IO) {
                    drawerState.close()
                    routeScreen = item
                }
            }
        },
        drawerState = drawerState,
        //gesturesEnabled = false
    ) {
        // cuerpo de la navegacion
        Scaffold(
            topBar = {
                if(routeScreen == RoutesNavigationOptions.HOME_SCREEN ||
                    routeScreen == RoutesNavigationOptions.UBI_OPTION ||
                    routeScreen == RoutesNavigationOptions.DOWNLOAD_OPTION ){
                    TopBarBody {
                        scope.launch(Dispatchers.IO) {
                            drawerState.open()
                        }
                    }
                }
            }
        ) { paddingValues ->

            if(configurationViewModel.tema == Tema.Claro) {
                changeStatusBar(systemUiController, Color.Black, Color(0xFFFEFBFF))
            } else {
                changeStatusBar(systemUiController, Color.Black, Color(0xFF00000F))
            }

            Column(modifier = Modifier.padding(paddingValues)) {
                when(routeScreen){
                    RoutesNavigationOptions.PREMIUM_SCREEN -> SuscripcionScreen {
                        routeScreen = RoutesNavigationOptions.HOME_SCREEN
                    }

                    RoutesNavigationOptions.FAST_SCREEN -> FastScreen(fastViewModel, orsViewModel){
                        routeScreen = RoutesNavigationOptions.HOME_SCREEN
                    }
                    RoutesNavigationOptions.ROUTES_SCREEN -> RoutesScreen(orsViewModel, routesViewModel)
                    RoutesNavigationOptions.TRIP_SCREEN -> TripScreen {
                        routeScreen = RoutesNavigationOptions.HOME_SCREEN
                    }
                    RoutesNavigationOptions.TURISM_SCREEN -> TurismScreen {
                        routeScreen = RoutesNavigationOptions.HOME_SCREEN
                    }
                    RoutesNavigationOptions.CHAT_SCREEN -> ChatScreen {
                        routeScreen = RoutesNavigationOptions.HOME_SCREEN
                    }
                    RoutesNavigationOptions.UBI_OPTION -> {
                        getUbi(locationPermissionState, backgroundLocationPermissionState, context)
                        routeScreen = RoutesNavigationOptions.HOME_SCREEN
                    }
                    RoutesNavigationOptions.DOWNLOAD_OPTION -> {
                        startDownload(context = context)
                        routeScreen = RoutesNavigationOptions.HOME_SCREEN
                    }
                    RoutesNavigationOptions.CONF_SCREEN -> ConfigurationScreen(configurationViewModel){
                        routeScreen = RoutesNavigationOptions.HOME_SCREEN
                    }
                    RoutesNavigationOptions.HELP_SCREEN -> HelpScreen {
                        routeScreen = RoutesNavigationOptions.HOME_SCREEN
                    }

                    RoutesNavigationOptions.PROFILE_SCREEN -> ProfileScreen {
                        routeScreen = RoutesNavigationOptions.HOME_SCREEN
                    }
                    else -> HomeScreen()
                }
            }
        }
    }
}

@Composable
fun AppDrawer(
    selectedScreen: RoutesNavigationOptions,
    items: List<RoutesNavigationItem>,
    onChangeScreen: (RoutesNavigationOptions) -> Unit
) {
    ModalDrawerSheet {
        DrawerHeader(modifier = Modifier)

        Spacer(modifier = Modifier.size(16.dp))

        items.forEach { item ->
            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(id = item.label),
                        fontWeight = if (selectedScreen == item.option) FontWeight.Bold else FontWeight.Normal
                    )
                },
                selected = selectedScreen == item.option,
                icon = {
                    Icon(
                        imageVector = if (selectedScreen == item.option) item.iconSelected else item.iconUnselected,
                        contentDescription = "icono de ${stringResource(id = item.label)}"
                    )
                },
                onClick = { onChangeScreen(item.option) },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = colorScheme.tertiary.copy(0.6f),
                    selectedTextColor = colorScheme.onTertiary,
                    selectedIconColor = colorScheme.onTertiary
                )
            )
        }
    }
}

@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(colorScheme.inverseSurface)
            .padding(15.dp)
            .fillMaxWidth()
    ) {

        Image(
            painterResource(id = R.drawable.usuario_3),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(70.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = "Cristian Alexis Torres Zavala",
            textAlign = TextAlign.Center,
            style = typography.bodyLarge,
            color = colorScheme.inverseOnSurface,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(openDrawer: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.Bienvenido))
        },
        navigationIcon = {
            IconButton(onClick = { openDrawer() }) {
                Icon(imageVector = Icons.Outlined.Menu, contentDescription = "Icono de menu")
            }
        }
    )
}

// hoja para compartir la ubicacion
fun getShareUbi(
    context: Context,
    message: String,
) : Intent{
    val shareUbi: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TITLE, "Ubicacion")
        val manager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val level: Int = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        putExtra(Intent.EXTRA_TEXT, "Bat:$level% $message\n")
        type = "text/plain"
    }
    return shareUbi
}

///////// FUNCIONES ////////

private fun startDownload(
    context: Context,
    pdfUrl: String = "https://drive.google.com/uc?export=download&id=1Xt85BpR47S6adKdRlh_ERVfo8TxD1fM4"
) {
    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    val request = DownloadManager.Request(Uri.parse(pdfUrl))
        .setTitle("Rutas")
        .setDescription("Descargando archivo PDF")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setAllowedOverMetered(true)
        .setAllowedOverRoaming(true)
        .setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "Ruta.pdf"
        )

    downloadManager.enqueue(request)
}

// Función para verificar si los servicios de ubicación están habilitados
fun areLocationServicesEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}

// Función para verificar si el permiso de ubicación está habilitado
fun isLocationPermissionGranted(context: Context, permission: String): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    } else {
        true // En versiones anteriores a Android 10, no se requiere este permiso
    }
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
fun getUbi(
    locationPermissionState: PermissionState,
    backgroundLocationPermissionState: PermissionState,
    context: Context
) {
    if (!locationPermissionState.status.isGranted || !backgroundLocationPermissionState.status.isGranted) {
        locationPermissionState.launchPermissionRequest()
        backgroundLocationPermissionState.launchPermissionRequest()
        return
    }

    if (!areLocationServicesEnabled(context)) {
        Toast.makeText(context, "Por favor activa la ubicación en todo momento.", Toast.LENGTH_LONG).show()
        return
    }

    if (!isLocationPermissionGranted(context, android.Manifest.permission.ACCESS_FINE_LOCATION) ||
        !isLocationPermissionGranted(context, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
        Toast.makeText(context, "Por favor activa la ubicación en todo momento.", Toast.LENGTH_LONG).show()
        return
    }

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location? ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude

                // Construir la URL con el marcador en tu ubicación actual
                val mapUrl = "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"
                val addressInfo = getAddressInfoFromCoordinates(context, latitude, longitude)
                val message = "Ubicación: ${addressInfo?.cityName}, ${addressInfo?.streetName}, ${addressInfo?.postalCode}\n"

                val shareIntent = Intent.createChooser(getShareUbi(context, message + mapUrl), null)
                context.startActivity(shareIntent)
            } else {
                Toast.makeText(context, "Por favor activa la ubicación en todo momento.", Toast.LENGTH_LONG).show()
            }
        }
        .addOnFailureListener {
            // Manejar el error al obtener la ubicación actual
            Toast.makeText(context, "Error al obtener la ubicacion actual.", Toast.LENGTH_SHORT).show()
        }
}

fun changeStatusBar(
    systemUiController: SystemUiController,
    navigation: Color,
    status: Color
) {
    systemUiController.setNavigationBarColor(navigation)
    systemUiController.setStatusBarColor(status)
}