@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.configuration.ConfigurationScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.help.HelpScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.profile.ProfileScreen
import net.streamroutes.sreamroutesapp.viewmodel.ConfigurationViewModel
import net.streamroutes.sreamroutesapp.viewmodel.FastViewModel
import net.streamroutes.sreamroutesapp.viewmodel.MainViewModel
import net.streamroutes.sreamroutesapp.viewmodel.MyViewModel

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
    //SHARE_OPTION,
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
    navController: NavController
) {
    Main(configurationViewModel, fastViewModel)
}

@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Main(
    configurationViewModel: ConfigurationViewModel,
    fastViewModel: FastViewModel
){
    val context = LocalContext.current
    // variable con todos los valores

    var routeScreen by remember {
        mutableStateOf(RoutesNavigationOptions.HOME_SCREEN)
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Variable para almacenar el tipo de mapa actual y su estado
    val defaultMapType = MapType.NORMAL
    var currentMapType by remember { mutableStateOf(defaultMapType) }
    var changeMap by remember { mutableStateOf(1) }

    // lista para el menu de opciones
    val routesNavigationList = listOf(
        RoutesNavigationItem(RoutesNavigationOptions.PROFILE_SCREEN, Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle, R.string.lblProfile),
        RoutesNavigationItem(RoutesNavigationOptions.HOME_SCREEN, Icons.Filled.Home, Icons.Outlined.Home, R.string.lblHome),
        RoutesNavigationItem(RoutesNavigationOptions.PREMIUM_SCREEN, Icons.Filled.MonetizationOn, Icons.Outlined.MonetizationOn, R.string.lblSuscription),
        RoutesNavigationItem(RoutesNavigationOptions.FAST_SCREEN, Icons.Filled.FlashOn, Icons.Outlined.FlashOn, R.string.lblFast),
        RoutesNavigationItem(RoutesNavigationOptions.ROUTES_SCREEN, Icons.Filled.DirectionsBus, Icons.Outlined.DirectionsBus, R.string.lblRoutes),
        RoutesNavigationItem(RoutesNavigationOptions.TRIP_SCREEN, Icons.Filled.Bookmark, Icons.Outlined.Bookmark, R.string.lblTrip),
        RoutesNavigationItem(RoutesNavigationOptions.TURISM_SCREEN, Icons.Filled.Museum, Icons.Outlined.Museum, R.string.lblTurism),
        RoutesNavigationItem(RoutesNavigationOptions.CHAT_SCREEN, Icons.Filled.ChatBubble, Icons.Outlined.ChatBubble, R.string.lblChat),
        RoutesNavigationItem(RoutesNavigationOptions.UBI_OPTION, Icons.Filled.EmergencyShare, Icons.Outlined.EmergencyShare, R.string.lblShare),
        RoutesNavigationItem(RoutesNavigationOptions.DOWNLOAD_OPTION, Icons.Filled.DownloadForOffline, Icons.Outlined.DownloadForOffline, R.string.lblDownload),
        RoutesNavigationItem(RoutesNavigationOptions.CONF_SCREEN, Icons.Filled.Settings, Icons.Outlined.Settings, R.string.lblConfiguration),
        RoutesNavigationItem(RoutesNavigationOptions.HELP_SCREEN, Icons.Filled.Help, Icons.Outlined.Help, R.string.lblHelp),
    )

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(selectedScreen = routeScreen, items = routesNavigationList){ item ->
                routeScreen = item
                scope.launch(Dispatchers.IO) {
                    drawerState.close()
                }
            }
        },
        drawerState = drawerState,
        //gesturesEnabled = false
    ) {
        // cuerpo de la navegacion
        Scaffold(
            topBar = {
                if(routeScreen == RoutesNavigationOptions.HOME_SCREEN){
                    TopBarBody(){
                        scope.launch(Dispatchers.IO) {
                            drawerState.open()
                        }
                    }
                }
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                val itsur = LatLng(19.057988677624586, -98.180047630148)
                val cameraState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(itsur, 17f)
                }

                when(routeScreen){
                    RoutesNavigationOptions.PREMIUM_SCREEN -> SuscripcionScreen()
                    RoutesNavigationOptions.FAST_SCREEN -> FastScreen(fastViewModel)
                    RoutesNavigationOptions.ROUTES_SCREEN -> RoutesScreen()
                    RoutesNavigationOptions.TRIP_SCREEN -> TripScreen()
                    RoutesNavigationOptions.TURISM_SCREEN -> TurismScreen()
                    RoutesNavigationOptions.CHAT_SCREEN -> ChatScreen()
                    //NavigationOptions.UBI_OPTION ->
                    //NavigationOptions.DOWNLOAD_OPTION ->
                    //NavigationOptions.SHARE_OPTION ->
                    RoutesNavigationOptions.CONF_SCREEN -> ConfigurationScreen(configurationViewModel)
                    RoutesNavigationOptions.HELP_SCREEN -> HelpScreen {
                        routeScreen = RoutesNavigationOptions.HOME_SCREEN
                    }

                    RoutesNavigationOptions.PROFILE_SCREEN -> ProfileScreen()
                    else -> MapBody(cameraPositionState = cameraState)
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
                label = { Text(text = stringResource(id = item.label)) },
                selected = selectedScreen == item.option,
                icon = {
                    Icon(
                        imageVector = if (selectedScreen == item.option) item.iconSelected else item.iconUnselected,
                        contentDescription = "icono de ${stringResource(id = item.label)}"
                    )
                },
                onClick = { onChangeScreen(item.option) }
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
            .background(colorScheme.secondary)
            .padding(15.dp)
            .fillMaxWidth()
    ) {

        Image(
            painterResource(id = R.drawable.usuario_2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(70.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = "Cristian Torres",
            textAlign = TextAlign.Center,
            style = typography.bodyLarge,
            color = colorScheme.onPrimary,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(openDrawer: () -> Unit) {
    TopAppBar(
        title = {
            //Text(text = "Ciudad")
        },
        navigationIcon = {
            IconButton(onClick = { openDrawer() }) {
                Icon(imageVector = Icons.Outlined.Menu, contentDescription = "Icono de menu")
            }
        }
    )
}

/*@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DrawerBody(
    navController: NavController,
    myViewModel: MyViewModel,
    scope: CoroutineScope,
    drawerState: DrawerState,
    changeScreen: (RoutesNavigationOptions) -> Unit
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
        *//*HeaderProfileMenu(
            navController = navController,
            scope = scope,
            drawerState = drawerState,
            changeScreen = changeScreen
        )*//*

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
                //changeScreen(NavigationOptions.PAQUETES_SCREEN)
                navController.navigate(AppScreens.MainParking.route)
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
                changeScreen(RoutesNavigationOptions.FAST_SCREEN)
            }

            DrawerItem(
                text = myViewModel.languageType().get(167),
                icon = Icons.Outlined.DirectionsBus
            ) {
                changeScreen(RoutesNavigationOptions.ROUTES_SCREEN)
            }

            DrawerItem(
                text = myViewModel.languageType().get(164),
                icon = Icons.Outlined.Route
            ) {
                changeScreen(RoutesNavigationOptions.TRIP_SCREEN)
            }

            DrawerItem(
                text = myViewModel.languageType()[305],
                icon = Icons.Outlined.Museum
            ) {
                changeScreen(RoutesNavigationOptions.TURISM_SCREEN)
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
                changeScreen(RoutesNavigationOptions.CHAT_SCREEN)
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

            *//*DrawerItem(
                text = myViewModel.languageType().get(170),
                icon = Icons.Outlined.StarRate
            ) {
                navController.navigate(AppScreens.ValoranoScreen.route)
            }*//*

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
                changeScreen(RoutesNavigationOptions.CONF_SCREEN)
            }

            DrawerItem(
                text = myViewModel.languageType().get(152),
                icon = Icons.Outlined.HelpOutline
            ) {
                changeScreen(RoutesNavigationOptions.HELP_SCREEN)
            }
        }
    }
}*/

@Composable
private fun MapBody(
    cameraPositionState: CameraPositionState,
) {
    val context = LocalContext.current
    GoogleMap(
        modifier = Modifier
            .fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            maxZoomPreference = 18f,
            minZoomPreference = 15f,
            isMyLocationEnabled = false,
            isBuildingEnabled = false
        ),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false
        )
    ) {

    }
}

///////// SHARE SHEET ////////

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