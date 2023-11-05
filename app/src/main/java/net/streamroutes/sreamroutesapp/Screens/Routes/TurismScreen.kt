package net.streamroutes.sreamroutesapp.Screens.Routes

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.maps.android.compose.Marker
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.Polyline
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import com.utsman.osmandcompose.rememberOverlayManagerState
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.Screens.Start.TopBar
import net.streamroutes.sreamroutesapp.R
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.CopyrightOverlay
import org.osmdroid.views.overlay.Marker
import java.net.URI

@Composable
fun TurismScreen(
    myViewModel: MyViewModel,
    navController: NavController
) {
    val drawerState = remember { mutableStateOf(DrawerValue.Open) }
    Tourism(myViewModel, navController, drawerState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tourism(
    myViewModel: MyViewModel,
    navController: NavController,
    drawerState: MutableState<DrawerValue>
){
    Scaffold (
        topBar = { TopBar(myViewModel, navController) },
        bottomBar = { BottomBar(myViewModel, navController) }
    ){ paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
                //.fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = "\"No hay que llegar primero... pero hay que saber llegar\"",
                    modifier = Modifier.fillMaxWidth(0.9f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            item {
                Text(
                    text = "- José Alfredo Jiménez",
                    modifier = Modifier.fillMaxWidth(0.9f),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelLarge
                )
            }
            item{
                Tarjeta(
                    "Callejón del Beso",
                    R.drawable.lugar_callejon_beso,
                    "callejon-del-beso",
                    onClose = {
                        if(drawerState.value == DrawerValue.Closed){
                            drawerState.value = DrawerValue.Open
                        }
                        else {
                            drawerState.value = DrawerValue.Closed
                        }
                    }
                )

                Tarjeta(
                    "Alhóndiga de Granaditas",
                    R.drawable.lugar_alhondiga,
                    "alhondiga-de-granaditas",
                    onClose = {
                        if(drawerState.value == DrawerValue.Closed){
                            drawerState.value = DrawerValue.Open
                        }
                        else {
                            drawerState.value = DrawerValue.Closed
                        }
                    }
                )

                Tarjeta(
                    "Universidad de Guanajuato",
                    R.drawable.lugar_universidad_gto,
                    "universidad-guanajuato",
                    onClose = {
                        if(drawerState.value == DrawerValue.Closed){
                            drawerState.value = DrawerValue.Open
                        }
                        else {
                            drawerState.value = DrawerValue.Closed
                        }
                    }
                )
                Tarjeta(
                    "Parque Bicentenario",
                    R.drawable.lugar_parque_bicentenario,
                    "parque-bicentenario",
                    onClose = {
                        if(drawerState.value == DrawerValue.Closed){
                            drawerState.value = DrawerValue.Open
                        }
                        else {
                            drawerState.value = DrawerValue.Closed
                        }
                    }
                )
                Tarjeta(
                    "Palacio de Gobierno",
                    R.drawable.lugar_palacio,
                    "palacio-de-gobierno",
                    onClose = {
                        if(drawerState.value == DrawerValue.Closed){
                            drawerState.value = DrawerValue.Open
                        }
                        else {
                            drawerState.value = DrawerValue.Closed
                        }
                    }
                )
                Tarjeta(
                    "Minas",
                    R.drawable.lugar_minas,
                    "minas",
                    onClose = {
                        if(drawerState.value == DrawerValue.Closed){
                            drawerState.value = DrawerValue.Open
                        }
                        else {
                            drawerState.value = DrawerValue.Closed
                        }
                    }
                )
                Tarjeta(
                    "Teatro Juárez",
                    R.drawable.lugar_teatro_juarez,
                    "teatro-juarez",
                    onClose = {
                        if(drawerState.value == DrawerValue.Closed){
                            drawerState.value = DrawerValue.Open
                        }
                        else {
                            drawerState.value = DrawerValue.Closed
                        }
                    }
                )
                Tarjeta(
                    "Casa del Conde Rul",
                    R.drawable.lugar_casa_conde_rul,
                    "casa-conde-rul",
                    onClose = {
                        if(drawerState.value == DrawerValue.Closed){
                            drawerState.value = DrawerValue.Open
                        }
                        else {
                            drawerState.value = DrawerValue.Closed
                        }
                    }
                )
                Tarjeta(
                    "Puente del Campanero",
                    R.drawable.lugar_puente_campanero,
                    "puente-campanero",
                    onClose = {
                        if(drawerState.value == DrawerValue.Closed){
                            drawerState.value = DrawerValue.Open
                        }
                        else {
                            drawerState.value = DrawerValue.Closed
                        }
                    }
                )
                Tarjeta(
                    "Sierra de Santa Rosa",
                    R.drawable.lugar_sierra_santa_rosa,
                    "sierra-santa-rosa",
                    onClose = {
                        if(drawerState.value == DrawerValue.Closed){
                            drawerState.value = DrawerValue.Open
                        }
                        else {
                            drawerState.value = DrawerValue.Closed
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    myViewModel: MyViewModel,
    navController: NavController
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = myViewModel.languageType().get(297),
                style = MaterialTheme.typography.titleLarge
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
                    contentDescription = "regresar a MainScreen"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
private fun BottomBar(
    myViewModel: MyViewModel,
    navController: NavController
){
    BottomAppBar (
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = myViewModel.languageType().get(299)
            )
            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate(AppScreens.ChatScreen.route)
                },
                icon = { Icon(Icons.Filled.Chat, contentDescription = "Invocar Chatbot") },
                text = { Text(text = myViewModel.languageType().get(298)) },
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Tarjeta(
    titulo: String,
    id_foto: Int,
    url: String,
    onClose: () -> Unit
){
    var rotated by remember { mutableStateOf(false) }

    val rotationFront by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val rotationBack by animateFloatAsState(
        targetValue = if (rotated) -180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    Column {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp)
                .graphicsLayer {
                    rotationY = rotationFront
                    cameraDistance = 8 * density
                },
            elevation = CardDefaults.cardElevation(8.dp),
            onClick = {
                rotated = !rotated
            }
        ) {
            if(!rotated){
                // FRENTE
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomStart
                ){
                    Image(
                        painter = painterResource(id = id_foto),
                        contentDescription = titulo,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                alpha = animateFront
                            },
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = titulo,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(8.dp)
                            .graphicsLayer {
                                alpha = animateFront
                            }
                    )
                }
            }
            else{
                // VARIABLES
                var openSheet by remember {
                    mutableStateOf(false)
                }

                var sheetState = SheetState(
                    skipPartiallyExpanded = true,
                    initialValue = SheetValue.Hidden
                )

                val pageCount = 3
                var pagerState = rememberPagerState(
                    initialPage = 0,
                    initialPageOffsetFraction = 0f
                ) {
                    pageCount
                }

                val scope = rememberCoroutineScope()

                // REVERSO
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                ){
                    Column (
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val uriHandler = LocalUriHandler.current
                        Text(
                            text = titulo,
                            modifier = Modifier
                                .graphicsLayer {
                                    alpha = animateBack
                                    rotationY = rotationBack
                                }
                                .padding(4.dp)
                        )
                        Button(
                            onClick = {
                                openSheet = !openSheet
                            },
                            modifier = Modifier
                                .graphicsLayer {
                                    alpha = animateBack
                                    rotationY = rotationBack
                                }
                                .padding(4.dp)
                        ) {
                            Row {
                                Icon(Icons.Filled.Map, contentDescription = "Ver ruta")
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Ver Ruta")
                            }
                        }
                        Button(
                            onClick = {
                                uriHandler.openUri("http://streamroutes.com/tourism.html#"+url)
                            },
                            modifier = Modifier
                                .graphicsLayer {
                                    alpha = animateBack
                                    rotationY = rotationBack
                                }
                                .padding(4.dp)
                        ) {
                            Row {
                                Icon(Icons.Filled.Language, contentDescription = "Ver Más")
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Ver Más")
                            }
                        }
                        if(openSheet) {
                            BottomSheetTourism(
                                route = titulo,
                                sheetState = sheetState,
                                pagerState = pagerState,
                                pageCount = pageCount,
                                onDismiss = {
                                    openSheet = !openSheet
                                },
                                onCalculate = {
                                    onClose()
                                },
                                rutas = alhondiga_rutas
                            )
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
private fun BottomSheetTourism(
    route: String,
    sheetState: SheetState,
    pagerState: PagerState,
    pageCount: Int,
    onDismiss: () -> Unit,
    onCalculate: () -> Unit,
    rutas: List<Ruta>
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
            val ruta = rutas[pagerState.currentPage]
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
                        .background(Color.Blue)
                ) {
                    MapaRuta(ruta)
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(pageCount){ index ->
                        val color = if(pagerState.currentPage == index) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onTertiary
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
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        Column(
                            Modifier
                                .padding(PaddingValues(16.dp))
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // nombre ruta y descripcion
                            Column(
                                Modifier.fillMaxWidth()
                            ) {
                                // nombre
                                Text(
                                    text = route,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.size(8.dp))

                            Spacer(modifier = Modifier.size(8.dp))

                            // duracion y cantidad de paradas
                            Row {
                                RouteInfo(
                                    title = "Duración",
                                    desc = ruta.duracion,
                                    value = 0.5f
                                )

                                Spacer(modifier = Modifier.size(16.dp))

                                RouteInfo(
                                    title = "Paradas totales",
                                    desc = ruta.paradas,
                                    value = 1f
                                )
                            }

                            // inicio de la ruta
                            RouteInfo(
                                title = "Inicio ruta",
                                desc = ruta.lugar_inicio
                            )


                            // parada mas cercana
                            Row(
                                verticalAlignment = Alignment.Bottom
                            ) {
                                RouteInfo(
                                    title = "Parada cercana",
                                    desc = ruta.parada_cercana,
                                    value = 0.7f
                                )

                                Button(
                                    onClick = {  },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.tertiary
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
                                        tint = MaterialTheme.colorScheme.onTertiary
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.size(8.dp))

                            // calcular ruta
                            Button(
                                onClick = {
                                    onDismiss()
                                    onCalculate()
                                },
                                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 64.dp, bottomEnd = 64.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.tertiary
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = "Mostrar ruta",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                            Spacer(modifier = Modifier.size(8.dp))
                        }
                    }
                }
            }
        }
    }
}

/*
@Composable
private fun MapaRuta(){
    val context = LocalContext.current

    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(20.09950128925512, -101.58378650988855)
        zoom = 16.5
    }

    var mapProperties by remember {
        mutableStateOf(DefaultMapProperties)
    }

    val overlayManagerState = rememberOverlayManagerState()

    /*
    SideEffect {
        mapProperties = mapProperties
            .copy(isTilesScaledToDpi = true)
            .copy(tileSources = TileSourceFactory.MAPNIK)
            .copy(isEnableRotationGesture = true)
            .copy(zoomButtonVisibility = ZoomButtonVisibility.ALWAYS)
    }*/

    val ruta1 = listOf(
        Pair(20.09764865179107, -101.58262491376681),
        Pair(20.097653551678118, -101.58351190436487),
        Pair(20.099492939558726, -101.58367216594583),
        Pair(20.099492939558726, -101.58367216594583),
        Pair(20.09941617559237, -101.58406696370828),
        Pair(20.099421075422615, -101.58454176461021),
        Pair(20.100484334655306, -101.58453132942023),
        Pair(20.10058723032516, -101.5850304790142),
        Pair(20.10087468454622, -101.58502004384296)
    )

    val ruta1GeoPoint = ruta1.map { GeoPoint(it.first, it.second) }

    val inicio = rememberMarkerState(
        geoPoint = ruta1GeoPoint.first()
    )
    val final = rememberMarkerState(
        geoPoint = ruta1GeoPoint.last()
    )

    OpenStreetMap(
        modifier = Modifier.fillMaxSize(),
        cameraState = cameraState,
        properties = mapProperties,
        overlayManagerState = overlayManagerState,
        onFirstLoadListener = {
            val copyright = CopyrightOverlay(context)
            overlayManagerState.overlayManager.add(copyright)
        }
    ) {
        com.utsman.osmandcompose.Marker(
            state = inicio, title = "inicio", visible = true
        )
        com.utsman.osmandcompose.Marker(
            state = final, title = "fin", visible = true
        )
        Polyline(geoPoints = ruta1GeoPoint)
    }
}
 */

data class Ruta(
    val duracion: String,
    val paradas: String,
    val lugar_inicio: String,
    val lugar_fin: String,
    val parada_cercana: String,
    val map_ruta: List<Pair<Double, Double>>,
    val zoom: Double,
    val pov: GeoPoint
)


@Composable
private fun MapaRuta(
    ruta: Ruta
) {
    val context = LocalContext.current

    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(ruta.pov)
        zoom = ruta.zoom
    }

    var mapProperties by remember {
        mutableStateOf(DefaultMapProperties)
    }

    val overlayManagerState = rememberOverlayManagerState()
    val ruta = ruta.map_ruta
    val rutaGeoPoint = ruta.map { GeoPoint(it.first, it.second) }
    val inicio = rememberMarkerState(
        geoPoint = rutaGeoPoint.first()
    )

    OpenStreetMap(
        modifier = Modifier.fillMaxSize(),
        cameraState = cameraState,
        properties = mapProperties,
        overlayManagerState = overlayManagerState,
        onFirstLoadListener = {
            val copyright = CopyrightOverlay(context)
            overlayManagerState.overlayManager.add(copyright)
        }
    ) {
        com.utsman.osmandcompose.Marker(
            state = inicio, title = "inicio", visible = true
        )
        Polyline(geoPoints = rutaGeoPoint)
    }
}


val alhondiga_rutas = listOf<Ruta>(
    Ruta(
        duracion = "20 minutos",
        paradas = "4",
        lugar_inicio = "Universidad de Guanajuato",
        lugar_fin = "Alhondiga",
        parada_cercana = "Escarola",
        map_ruta = listOf(
            Pair(21.017082788844267, -101.25332005134952),
            Pair(21.01710384296411, -101.25379369789864),
            Pair(21.017158583661782, -101.25399217835732),
            Pair(21.0175038706763, -101.25438011743566),
            Pair(21.017857578496347, -101.25461919617),
            Pair(21.01810601563027, -101.25495751513367),
            Pair(21.01820707468496, -101.25537703064862),
            Pair(21.01831655525027, -101.2557785024855),
            Pair(21.018388138653336, -101.25624763811511),
            Pair(21.01874605515329, -101.25683405765214),
            Pair(21.018939750783243, -101.25724906224758),
            Pair(21.01899449080697, -101.2574520536258),
            Pair(21.01924713680996, -101.25813771339216)
        ),
        zoom = 16.5,
        pov = GeoPoint(21.01770177872662, -101.25530936685587)
    ),
    Ruta(
        duracion = "5 minutos",
        paradas = "1",
        lugar_inicio = "Scotiabank",
        lugar_fin = "Alhondiga",
        parada_cercana = "Plaza del Musico",
        map_ruta = listOf(
            Pair(21.018990280070337, -101.26007740898893),
            Pair(21.018725001299014, -101.2596894699106),
            Pair(21.019129235428874, -101.25903989750033),
            Pair(21.01951662544166, -101.25867451301959),
            Pair(21.019120813895668, -101.2577813509555)
        ),
        zoom = 16.5,
        pov = GeoPoint(21.019082916990417, -101.25894967911002)
    ),
    Ruta(
        duracion = "7 minutos",
        paradas = "3",
        lugar_inicio = "Citibanamex Guanajuato",
        lugar_fin = "Alhondiga",
        parada_cercana = "Mercado Hidalgo",
        map_ruta = listOf(
            Pair(21.016855404156008, -101.25616644163438),
            Pair(21.017369124614827, -101.25710922381315),
            Pair(21.017693357108218, -101.25757835944276),
            Pair(21.01789547570844, -101.25809711518707),
            Pair(21.01843866808907, -101.25777232898194),
            Pair(21.018569202397288, -101.25757835944276)
        ),
        zoom = 16.5,
        pov = GeoPoint(21.017870210898398, -101.25688367783737)
    )
)