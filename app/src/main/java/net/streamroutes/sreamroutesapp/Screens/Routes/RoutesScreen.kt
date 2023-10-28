@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens.Routes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.MarkerState
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.Polyline
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint


@Composable
fun RoutesScreen(myViewModel: MyViewModel, navController: NavController){
    RoutesScreenView(myViewModel,navController)
}

enum class DrawerValue {
    Open, Closed
}


@Composable
fun RoutesScreenView(
    myViewModel: MyViewModel,
    navController: NavController
) {
    val drawerState = remember { mutableStateOf(DrawerValue.Open) }

    Scaffold(
        topBar = {
            TopBarBody(
                myViewModel = myViewModel,
                navController = navController,
                openPanel = {
                    if (drawerState.value == DrawerValue.Closed) {
                        drawerState.value = DrawerValue.Open
                    } else {
                        drawerState.value = DrawerValue.Closed
                    }
                }
            ) },
    ) { paddingValues ->

        val sidePanelVisible = drawerState.value != DrawerValue.Open

        val configuration = LocalConfiguration.current

        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            MainContent()

            AnimatedVisibility(
                enter = slideIn(tween(1000, easing = LinearOutSlowInEasing)) { fullSize ->
                    IntOffset(fullSize.width, 0)
                },
                exit = slideOut(tween(1000, easing = LinearOutSlowInEasing)) { fullSize ->
                    IntOffset(fullSize.width+(fullSize.width/8), 0)
                },
                visible = sidePanelVisible,
                modifier = Modifier
                    .offset(configuration.screenWidthDp.dp/4)
            ) {
                SidePanel()
            }
        }
    }    
}

@Composable
fun SidePanel() {
    Column(
        Modifier
            .fillMaxWidth(0.75f)
            .fillMaxHeight()
            .shadow(16.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            .background(colorScheme.background)
    ) {
        Column {
            OutlinedTextField(
                value = "",
                onValueChange = {  },
                placeholder = {
                    Text(text = "Filtra aqui")
                },
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Mic,
                            contentDescription = "Buscar por audio"
                        )
                    }
                },
                shape = CircleShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedBorderColor = MaterialTheme.colorScheme.background,
                    unfocusedBorderColor = MaterialTheme.colorScheme.background,
                    focusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
                    focusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedTrailingIconColor = MaterialTheme.colorScheme.onBackground
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(64.dp, CircleShape)
                    .padding(8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 30.dp
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FilterAlt,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiary
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = "Filtrar",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(10) {
                if(it%3 == 0)
                    RouteOption(true)
                else
                    RouteOption(false)
            }
        }
    }
}

data class RoutesOption(
    val name: String,
    val action: () -> Unit
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun RouteOption(
    ubication: Boolean
) {

    var openExactUbi by remember {
        mutableStateOf(false)
    }

    var openUnexactUbi by remember {
        mutableStateOf(false)
    }

    var openSheet by remember {
        mutableStateOf(false)
    }
    
    var sheetState = SheetState(
        skipPartiallyExpanded = true,
        initialValue = SheetValue.Hidden
    )

    if(openExactUbi){
        AlertDialog(
            onDismissRequest = { openExactUbi = !openExactUbi },
            confirmButton = {
                TextButton(onClick = { openExactUbi = !openExactUbi }) {
                    Text(text = "Aceptar")
                }
            },
            text = {
                Text(
                    text = "Este autobus tiene localizacion en tiempo real.",
                    style = typography.bodyLarge
                )
            }
        )
    }
    
    if(openUnexactUbi){
        AlertDialog(
            onDismissRequest = { openUnexactUbi = !openUnexactUbi },
            confirmButton = {
                TextButton(onClick = { openUnexactUbi = !openUnexactUbi }) {
                    Text(text = "Aceptar")
                }
            },
            text = {
                Text(
                    text = "Este autobus NO tiene localizacion en tiempo real.",
                    style = typography.bodyLarge
                )
            }
        )
    }

    val pageCount = 10
    var pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        pageCount
    }

    val scope = rememberCoroutineScope()

    val routes_options = listOf(
        RoutesOption(
            name = "Autobus",
            action = {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            }
        ),
        RoutesOption(
            name = "Mapa",
            action = {
                scope.launch {
                    pagerState.animateScrollToPage(1)
                }
            }
        ),
        RoutesOption(
            name = "Paradas",
            action = {
                scope.launch {
                    pagerState.animateScrollToPage(2)
                }
            }
        ),
        RoutesOption(
            name = "Iconicos",
            action = {
                scope.launch {
                    pagerState.animateScrollToPage(8)
                }
            }
        ),
    )

    if(openSheet){
        BottomSheet(
            sheetState = sheetState,
            pagerState = pagerState,
            pageCount = pageCount,
            list = routes_options
        ) {
            openSheet = !openSheet
        }
    }

    Card (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = 8.dp)
            .combinedClickable(
                onClick = { openSheet = !openSheet },
                onDoubleClick = { }
            )
    ) {
        Row(
            modifier = Modifier
                .height(100.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .background(colorScheme.tertiary),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "2.30 Hrs",
                    style = typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = colorScheme.onTertiary.copy(0.5f),
                    modifier = Modifier.padding(PaddingValues(8.dp))
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Ruta 1 - Pipila",
                    style = typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.size(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    if (!ubication){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.AccessTime,
                                contentDescription = null
                            )
                            
                            Spacer(modifier = Modifier.size(8.dp))

                            // tiempo aprox
                            Column {
                                Text(
                                    text = "3 min",
                                    style = typography.labelMedium
                                )
                                Text(
                                    text = "6, 9 min",
                                    style = typography.labelMedium
                                )
                            }

                            Spacer(modifier = Modifier.size(16.dp))
                            
                            // boton indicando que no tiene ubicacino exacta
                            Button(
                                onClick = {
                                    openUnexactUbi = !openUnexactUbi
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorScheme.error
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .width(75.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Error,
                                    contentDescription = "Autobus sin ubicacion exacta",
                                    tint = colorScheme.onError
                                )
                            }

                        }
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // tiempo exacto
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.AccessTime,
                                    contentDescription = null
                                )

                                Spacer(modifier = Modifier.size(8.dp))

                                Text(
                                    text = "5 min",
                                    style = typography.labelMedium
                                )
                            }
                            
                            Spacer(modifier = Modifier.size(16.dp))

                            // boton indicando que si tiene ubicacion exacta
                            Button(
                                onClick = {
                                    openExactUbi = !openExactUbi
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorScheme.tertiary
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .width(75.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.BusAlert,
                                    contentDescription = "Autobus con ubicacion exacta",
                                    tint = colorScheme.onTertiary
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    sheetState: SheetState,
    pagerState: PagerState,
    pageCount: Int,
    list: List<RoutesOption>,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.80f)
        ) {
            // carrusel
            Box(
                Modifier
                    .fillMaxHeight(0.4f)
                    .align(Alignment.TopCenter)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Green)
                ) {

                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(pageCount){ index ->
                        val color = if(pagerState.currentPage == index) colorScheme.tertiary else colorScheme.onTertiary
                        val size = if(pagerState.currentPage == index) 15.dp else 10.dp
                        Box(modifier = Modifier
                            .padding(8.dp)
                            .background(color, CircleShape)
                            .size(size)
                        )
                    }
                }
            }

            // caja de informacino de la ruta
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Column(
                        Modifier
                            .fillMaxHeight(0.67f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                            .background(colorScheme.background)
                    ) {
                        Column(
                            Modifier
                                .padding(PaddingValues(16.dp))
                                .fillMaxWidth()
                        ) {
                            // nombre ruta y descripcion
                            Column {
                                // nombre
                                Text(
                                    text = "Nombre ruta",
                                    style = typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )

                                // descripcion
                                Text(
                                    text = "Informacion util necesaria de la ruta.",
                                    style = typography.bodyLarge
                                )
                            }

                            // botons de accion
                            LazyRow(){
                                items(list.size){ index ->
                                    val item = list[index]
                                    Chip(
                                        onClick = { item.action() },
                                        colors = ChipDefaults.chipColors(
                                            backgroundColor = colorScheme.tertiary
                                        ),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier
                                            .padding(4.dp)
                                    ) {
                                        Text(
                                            text = item.name,
                                            color = colorScheme.onTertiary
                                        )
                                    }
                                }
                            }

                            // duracion y cantidad de paradas
                            Row {
                                RouteInfo(
                                    title = "Duracion",
                                    desc = "2 horas y 34 minutos.",
                                    value = 0.5f
                                )

                                Spacer(modifier = Modifier.size(16.dp))

                                RouteInfo(
                                    title = "Paradas totales",
                                    desc = "6",
                                    value = 1f
                                )
                            }

                            // inicio de la ruta
                            RouteInfo(
                                title = "Inicio ruta",
                                desc = "Calle Pipila Uriangato"
                            )

                            // final de la ruta
                            RouteInfo(
                                title = "Final ruta",
                                desc = "Calle Obregon Uriangato"
                            )

                            // parada mas cercana
                            Row(
                                verticalAlignment = Alignment.Bottom
                            ) {
                                RouteInfo(
                                    title = "Parada cercana",
                                    desc = "Calle 5 de Mayo",
                                    value = 0.7f
                                )

                                Button(
                                    onClick = {  },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorScheme.tertiary
                                    ),
                                    shape = RoundedCornerShape(16.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(55.dp)
                                        .padding(horizontal = 8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.MyLocation,
                                        contentDescription = "Buscar parada cercana",
                                        tint = colorScheme.onTertiary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RouteInfo(
    title: String,
    desc: String,
    value: Float = 1f
) {
    Column {
        Text(
            text = title,
            style = typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Row(
            Modifier
                .background(colorScheme.surfaceVariant.copy(0.3f), RoundedCornerShape(16.dp))
                .fillMaxWidth(value)
                .height(55.dp)
        ) {
            Text(
                text = desc,
                style = typography.labelLarge,
                color = colorScheme.onSurfaceVariant.copy(0.5f),
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun MainContent() {
    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(20.139468718311957, -101.15069924573676)
        zoom = 17.0
    }

    val ruta1I = listOf(
        GeoPoint(20.132195, -101.186513),
        GeoPoint(20.130103, -101.187440),
        GeoPoint(20.129952, -101.185994),
        GeoPoint(20.126200, -101.186821),
        GeoPoint(20.126371, -101.189060),
        GeoPoint(20.126477, -101.190613),
        GeoPoint(20.126474, -101.190628),
        GeoPoint(20.128377, -101.190353),
        GeoPoint(20.129459, -101.190800),
        GeoPoint(20.128658, -101.191782),
        GeoPoint(20.127031, -101.192631),
        GeoPoint(20.126338, -101.193629),
        GeoPoint(20.126167, -101.193720),
        GeoPoint(20.125250, -101.193859),
        GeoPoint(20.125212, -101.194554),
        GeoPoint(20.122248, -101.194691),
        GeoPoint(20.119407, -101.195200),
        GeoPoint(20.118674, -101.195259),
        GeoPoint(20.117485, -101.195490),
        GeoPoint(20.117352, -101.194554),
        GeoPoint(20.116483, -101.194825),
        GeoPoint(20.114481, -101.191281),
        GeoPoint(20.114421, -101.190940),
        GeoPoint(20.114470, -101.187700),
        GeoPoint(20.114447, -101.187574),
        GeoPoint(20.114374, -101.187461),
        GeoPoint(20.114706, -101.187499),
        GeoPoint(20.115807, -101.187730),
        GeoPoint(20.115848, -101.186948),
        GeoPoint(20.116284, -101.187047),
        GeoPoint(20.118583, -101.187491)
    )

    val paradaRuta1I = listOf(
        GeoPoint(20.132148784075383, -101.18658401178985),
        GeoPoint(20.130100414302177, -101.18685751997809),
        GeoPoint(20.1286624384808, -101.18630239997012),
        GeoPoint(20.12626607542876, -101.18713239733687),
        GeoPoint(20.126443968930666, -101.1897577478104),
        GeoPoint(20.12845156492248, -101.19041183942103),
        GeoPoint(20.128053430395777, -101.19201321119603),
        GeoPoint(20.125728467872396, -101.19377208621506),
        GeoPoint(20.124796416351554, -101.19458943512876),
        GeoPoint(20.117503956009642, -101.19544254943673),
        GeoPoint(20.116224965329312, -101.19409890304624),
        GeoPoint(20.11454992685379, -101.19015376389814),
        GeoPoint(20.115158873743283, -101.18754702065762),
        GeoPoint(20.118136641252413, -101.1873841522879)
    )

    Column(
        Modifier.fillMaxSize()
    ) {
        OpenStreetMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraState = cameraState,
            properties = DefaultMapProperties.copy(
                maxZoomLevel = 18.0,
                minZoomLevel = 15.0,
                tileSources = TileSourceFactory.MAPNIK,
                zoomButtonVisibility = ZoomButtonVisibility.NEVER
            )
        ) {
            Polyline(
                geoPoints = ruta1I
            )

            paradaRuta1I.forEach(){
                Marker(
                    state = MarkerState(
                        geoPoint = it
                    )
                )
            }
        }
    }
}

@Composable
private fun TopBarBody(
    myViewModel: MyViewModel,
    navController: NavController,
    openPanel: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Transporte"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(AppScreens.MainScreen.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = "Abrir el menu de opciones"
                )
            }
        },
        actions = {
            TextButton(
                onClick = {
                    openPanel()
                }
            ) {
                Text(
                    text = "Rutas"
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}