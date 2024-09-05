package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.MyViewModel

@Composable
fun TurismScreen(
    myViewModel: MyViewModel = MyViewModel(),
    onBack: () -> Unit
) {
    Turism(onBack)
}

@Composable
fun Turism(
    onBack: () -> Unit
){
    Scaffold (
        topBar = { TopBar(onBack) },
        bottomBar = { BottomBar() }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CitaTextual()

            Spacer(modifier = Modifier.size(32.dp))

            Lugares()
        }
    }
}

@Composable
fun Lugares() {
    val drawerState = remember { mutableStateOf(DrawerValue.Open) }
    Row(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = stringResource(id = R.string.lblLugaresTuristicos),
            style = typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
    }

    LazyRow {
        item{
            Tarjeta(
                titulo = "Callejón del Beso",
                idFoto = R.drawable.lugar_callejon_beso,
                url = "callejon-del-beso"
            ) {
                if (drawerState.value == DrawerValue.Closed) {
                    drawerState.value = DrawerValue.Open
                } else {
                    drawerState.value = DrawerValue.Closed
                }
            }

            Tarjeta(
                titulo = "Alhóndiga de Granaditas",
                idFoto = R.drawable.lugar_alhondiga,
                url = "alhondiga-de-granaditas"
            ) {
                if(drawerState.value == DrawerValue.Closed){
                    drawerState.value = DrawerValue.Open
                }
                else {
                    drawerState.value = DrawerValue.Closed
                }
            }

            Tarjeta(
                titulo = "Universidad de Guanajuato",
                idFoto = R.drawable.lugar_universidad_gto,
                url = "universidad-guanajuato"
            ) {
                if(drawerState.value == DrawerValue.Closed){
                    drawerState.value = DrawerValue.Open
                }
                else {
                    drawerState.value = DrawerValue.Closed
                }
            }

            Tarjeta(
                titulo = "Parque Bicentenario",
                idFoto = R.drawable.lugar_parque_bicentenario,
                url = "parque-bicentenario"
            ) {
                if(drawerState.value == DrawerValue.Closed){
                    drawerState.value = DrawerValue.Open
                }
                else {
                    drawerState.value = DrawerValue.Closed
                }
            }

            Tarjeta(
                titulo = "Palacio de Gobierno",
                idFoto = R.drawable.lugar_palacio,
                url = "palacio-de-gobierno"
            ) {
                if(drawerState.value == DrawerValue.Closed){
                    drawerState.value = DrawerValue.Open
                }
                else {
                    drawerState.value = DrawerValue.Closed
                }
            }
            Tarjeta(
                titulo = "Minas",
                idFoto = R.drawable.lugar_minas,
                url = "minas"
            ) {
                if(drawerState.value == DrawerValue.Closed){
                    drawerState.value = DrawerValue.Open
                }
                else {
                    drawerState.value = DrawerValue.Closed
                }
            }

            Tarjeta(
                titulo = "Teatro Juárez",
                idFoto = R.drawable.lugar_teatro_juarez,
                url = "teatro-juarez"
            ) {
                if(drawerState.value == DrawerValue.Closed){
                    drawerState.value = DrawerValue.Open
                }
                else {
                    drawerState.value = DrawerValue.Closed
                }
            }

            Tarjeta(
                titulo = "Casa del Conde Rul",
                idFoto = R.drawable.lugar_casa_conde_rul,
                url = "casa-conde-rul"
            ) {
                if(drawerState.value == DrawerValue.Closed){
                    drawerState.value = DrawerValue.Open
                }
                else {
                    drawerState.value = DrawerValue.Closed
                }
            }

            Tarjeta(
                titulo = "Puente del Campanero",
                idFoto = R.drawable.lugar_puente_campanero,
                url = "puente-campanero"
            ) {
                if(drawerState.value == DrawerValue.Closed){
                    drawerState.value = DrawerValue.Open
                }
                else {
                    drawerState.value = DrawerValue.Closed
                }
            }

            Tarjeta(
                titulo = "Sierra de Santa Rosa",
                idFoto = R.drawable.lugar_sierra_santa_rosa,
                url = "sierra-santa-rosa"
            ) {
                if(drawerState.value == DrawerValue.Closed){
                    drawerState.value = DrawerValue.Open
                }
                else {
                    drawerState.value = DrawerValue.Closed
                }
            }
        }
    }
}

@Composable
fun CitaTextual() {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "\"No hay que llegar primero... pero hay que saber llegar\"",
            modifier = Modifier.fillMaxWidth(0.9f),
            textAlign = TextAlign.Left,
            style = typography.titleMedium,
        )
        Text(
            text = "- José Alfredo Jiménez",
            modifier = Modifier.fillMaxWidth(0.7f),
            textAlign = TextAlign.End,
            style = typography.bodyMedium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.lblTurismo),
                style = typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onBack()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "regresar a MainScreen"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorScheme.background,
            titleContentColor = colorScheme.onBackground
        )
    )
}

@Composable
private fun BottomBar(){
    BottomAppBar (
        containerColor = colorScheme.inverseSurface,
        contentColor = colorScheme.inverseOnSurface
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.lblAtencionTurista)
            )

            Button(
                onClick = {

                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.tertiaryContainer,
                    contentColor = colorScheme.onTertiaryContainer
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 4.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.btnChatApoyo),
                    style = typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun Tarjeta(
    titulo: String,
    idFoto: Int,
    url: String,
    onClose: () -> Unit
){
    var rotated by remember { mutableStateOf(false) }

    val rotationFront by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500), label = ""
    )

    val rotationBack by animateFloatAsState(
        targetValue = if (rotated) -180f else 0f,
        animationSpec = tween(500), label = ""
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500), label = ""
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500), label = ""
    )

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .width(300.dp)
            .height(250.dp)
            .padding(horizontal = 16.dp)
            .graphicsLayer {
                rotationY = rotationFront
                cameraDistance = 8 * density
            },
        onClick = {
            rotated = !rotated
        }
    ) {
        if(!rotated){
            // FRENTE
            FrenteTarjeta(
                idPhoto = idFoto,
                titulo = titulo,
                animateFront = animateFront
            )
        }
        else{
            ReversoTarjeta(
                titulo = titulo,
                animateBack = animateBack,
                rotationBack = rotationBack,
                onClose = onClose,
                url = url
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ReversoTarjeta(
    titulo: String,
    animateBack: Float,
    rotationBack: Float,
    onClose: () -> Unit,
    url: String
) {
    // VARIABLES
    var openSheet by remember {
        mutableStateOf(false)
    }

    val sheetState = SheetState(
        skipPartiallyExpanded = true,
        density = LocalDensity.current,
        initialValue = SheetValue.Hidden
    )

    val pageCount = 3
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        pageCount
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

    // REVERSO
    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val uriHandler = LocalUriHandler.current
        Text(
            text = titulo,
            style = typography.bodyLarge,
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
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.inverseSurface,
                contentColor = colorScheme.tertiary
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(top = 16.dp)
                .height(50.dp)
                .graphicsLayer {
                    alpha = animateBack
                    rotationY = rotationBack
                }
        ) {
            Text(
                text = stringResource(id = R.string.btnVerRuta),
                style = typography.titleMedium
            )
        }

        Button(
            onClick = {
                uriHandler.openUri("https://rumapp.org/tourism.html#$url")
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.inverseSurface,
                contentColor = colorScheme.inverseOnSurface
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(top = 16.dp)
                .height(50.dp)
                .graphicsLayer {
                    alpha = animateBack
                    rotationY = rotationBack
                }
        ) {
            Text(
                text = stringResource(id = R.string.btnVerMas),
                style = typography.titleMedium
            )
        }
    }
}

@Composable
fun FrenteTarjeta(
    idPhoto: Int,
    titulo: String,
    animateFront: Float
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ){
        Image(
            painter = painterResource(id = idPhoto),
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
            style = typography.headlineSmall,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .graphicsLayer {
                    alpha = animateFront
                }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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
    val ruta = rutas[pagerState.currentPage]
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
    ) {
        Column {
            // carrusel
            Box {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Blue)
                ) {
                    MapaRuta(ruta)
                }

                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(pageCount){ index ->
                        val color = if(pagerState.currentPage == index) colorScheme.tertiary else colorScheme.onTertiary
                        val size = if(pagerState.currentPage == index) 15.dp else 10.dp
                        Box(modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .background(color, CircleShape)
                            .size(size)
                        )
                    }
                }
            }

            // caja de informacino de la ruta
            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(colorScheme.background)
            ) {
                Column(
                    Modifier
                        .padding(PaddingValues(16.dp))
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // nombre ruta y descripcion
                    Text(
                        text = route,
                        style = typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    // duracion y cantidad de paradas
                    Row {
                        RouteInfo(
                            title = stringResource(id = R.string.lblDuracion),
                            desc = ruta.duracion,
                            value = 0.5f
                        )

                        Spacer(modifier = Modifier.size(16.dp))

                        RouteInfo(
                            title = stringResource(id = R.string.lblParadasTotales),
                            desc = ruta.paradas,
                            value = 1f
                        )
                    }

                    // inicio de la ruta
                    RouteInfo(
                        title = stringResource(id = R.string.lblInicioRuta),
                        desc = ruta.lugarInicio
                    )


                    // parada mas cercana
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        RouteInfo(
                            title = stringResource(id = R.string.lblParadaCercana),
                            desc = ruta.paradaCercana,
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

                    Spacer(modifier = Modifier.size(8.dp))

                    // calcular ruta
                    Button(
                        onClick = {
                            onDismiss()
                            onCalculate()
                        },
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 64.dp, bottomEnd = 64.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorScheme.inverseSurface,
                            contentColor = colorScheme.inverseOnSurface
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.btnMostrarRuta),
                            style = typography.bodyLarge
                        )
                    }

                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        }
    }
}

data class Ruta(
    val duracion: String,
    val paradas: String,
    val lugarInicio: String,
    val lugarFin: String,
    val paradaCercana: String,
    val mapRuta: List<Pair<Double, Double>>,
    val zoom: Double,
    val inicio: Pair<Double, Double>,
    val pov: LatLng
)



@Composable
private fun MapaRuta(
    ruta: Ruta
) {
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ruta.pov, 15f)
    }

    val ruta = ruta.mapRuta
    val rutaGeoPoint = ruta.map { LatLng(it.first, it.second) }
    val inicio = rememberMarkerState(
        position = LatLng(rutaGeoPoint.first().latitude, rutaGeoPoint.first().longitude)
    )

    GoogleMap(
        cameraPositionState = cameraPosition,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
        ),
        properties = com.google.maps.android.compose.MapProperties(
            mapStyleOptions = MapStyleOptions(stringResource(id = R.string.mapStyleLight)),
            maxZoomPreference = 18f,
            minZoomPreference = 15f
        ),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Marker(
            state = inicio, title = "inicio", visible = true
        )
        Polyline(points = rutaGeoPoint)

    }
}


val alhondiga_rutas = listOf(
    Ruta(
        duracion = "10 minutos",
        paradas = "4",
        lugarInicio = "Universidad de Guanajuato",
        lugarFin = "Alhondiga",
        paradaCercana = "Escarola",
        mapRuta = listOf(
            Pair(21.01895889398486, -101.25741027410066),
            Pair(21.019343866463387, -101.25839897585983),
            Pair(21.0195886166583, -101.25847271880534),
            Pair(21.020044972729547, -101.2582378338326),
            Pair(21.020429942419895, -101.2582269089696),
            Pair(21.02078941651196, -101.25850276221017),
            Pair(21.021263615061653, -101.25899711308432),
            Pair(21.021505812666327, -101.25911182434285),
            Pair(21.022097283039926, -101.25888786428618),
            Pair(21.02250264285332, -101.2586693666471),
            Pair(21.02257402685597, -101.25857104271977),
            Pair(21.022650509678023, -101.25856558027937),
            Pair(21.022625015408362, -101.25841809438838),
            Pair(21.02248989570638, -101.25814497236803),
            Pair(21.022364973608802, -101.25786638790727),
            Pair(21.02234202872236, -101.25776260153954),
            Pair(21.022398116216316, -101.25765335273141),
            Pair(21.02243125881646, -101.25760692198794),
            Pair(21.022757585562992, -101.25745124243632),
            Pair(21.02280602462854, -101.25737203705043),
            Pair(21.022877408485932, -101.2571999701776),
            Pair(21.022821321172298, -101.25703336574519),
            Pair(21.022721893609877, -101.25684218033095),
            Pair(21.022540884287555, -101.25679028714708),
            Pair(21.022451654259054, -101.25674931884402),
            Pair(21.022298688371666, -101.25647346560346),
            Pair(21.02227829290816, -101.2563450982539),
            Pair(21.02229613893888, -101.25628501140943),
            Pair(21.022301237804403, -101.25619761236293),
            Pair(21.02231454689693, -101.25616802994156),
            Pair(21.022202371825028, -101.25606424357383),
            Pair(21.018560098905372, -101.25490011777121),
            Pair(21.01830514901267, -101.25493289241186),
            Pair(21.01811393631142, -101.25495201095328),
            Pair(21.01815727787852, -101.25512954026651),
            Pair(21.018213366949087, -101.25542178087423),
            Pair(21.01832299552137, -101.25591886295126),
            Pair(21.018353589527162, -101.25613736056756),
            Pair(21.018937423940432, -101.25724896725013),
            Pair(21.01896291884064, -101.25742376534315),
            Pair(21.019335143894015, -101.25837696124582)
        ),
        zoom = 17.25,
        pov = LatLng(21.020660510916677, -101.25704553348501),
        inicio = Pair(21.018150229524146, -101.25512289819623)
    ),
    Ruta(
        duracion = "5 minutos",
        paradas = "1",
        lugarInicio = "Scotiabank",
        lugarFin = "Alhondiga",
        paradaCercana = "Plaza del Musico",
        mapRuta = listOf(
            Pair(21.018954022576814, -101.25741785215),
            Pair(21.019215446144983, -101.25801410541705),
            Pair(21.0194304877688, -101.2586871488776),
            Pair(21.019139549027255, -101.25903948035358),
            Pair(21.01885704274949, -101.25945053374222),
            Pair(21.018688382030003, -101.25970348967371),
            Pair(21.018152882981237, -101.25867359766697),
            Pair(21.017887241007266, -101.25807282732973),
            Pair(21.017672197157925, -101.25753981304553),
            Pair(21.01735595563968, -101.25707907188463),
            Pair(21.016900566675226, -101.25618017491385),
            Pair(21.017263191333768, -101.25581880929745),
            Pair(21.01733908940654, -101.25564264355945),
            Pair(21.017347522523337, -101.25556585336597),
            Pair(21.017343305965003, -101.25534903399613),
            Pair(21.017309573493932, -101.25517738532834),
            Pair(21.017166210406828, -101.25486119041399),
            Pair(21.01695959865647, -101.2544004492531),
            Pair(21.016896350104247, -101.25410683968977),
            Pair(21.016782502642613, -101.25390808860075),
            Pair(21.016744553469433, -101.25372288872236),
            Pair(21.016744553469433, -101.25345186451005),
            Pair(21.016807802086042, -101.25308146475325),
            Pair(21.016951165517725, -101.25258910410089),
            Pair(21.01705236315122, -101.2528601283132),
            Pair(21.01707344598288, -101.25307243061285),
            Pair(21.017119828202063, -101.2537635423542),
            Pair(21.017128261331276, -101.25393067395179),
            Pair(21.017326439730425, -101.2542242835151),
            Pair(21.017558350288613, -101.25440496632329),
            Pair(21.01787037483421, -101.25464437104417),
            Pair(21.0181149341568, -101.2549560488883),
            Pair(21.01832154430642, -101.25591818484196),
            Pair(21.018366055140632, -101.25620519794667),
            Pair(21.018660958897172, -101.25669247034753),
            Pair(21.01894086700793, -101.25722793452431),
            Pair(21.018965858777975, -101.25743141091148)
        ),
        zoom = 17.25,
        pov = LatLng(21.018429947144696, -101.25623121695456),
        inicio = Pair(21.018150229524146, -101.25512289819623)
    ),
    Ruta(
        duracion = "7 minutos",
        paradas = "3",
        lugarInicio = "Citibanamex Guanajuato",
        lugarFin = "Alhondiga",
        paradaCercana = "Mercado Hidalgo",
        mapRuta = listOf(
            Pair(21.01899278181935, -101.25744862091926),
            Pair(21.01941771927471, -101.25863346143544),
            Pair(21.019301298174494, -101.2588143055142),
            Pair(21.018713370229875, -101.25975594192444),
            Pair(21.01948757189121, -101.26071628634283),
            Pair(21.01955160342758, -101.26095949044877),
            Pair(21.019132487417746, -101.26109668250854),
            Pair(21.018754117785953, -101.2611153905167),
            Pair(21.018497990105658, -101.26108421050311),
            Pair(21.01796252615815, -101.2609095865125),
            Pair(21.017816534501303, -101.26044598119422),
            Pair(21.01770182667066, -101.26027841300689),
            Pair(21.01613240612579, -101.25708344623511),
            Pair(21.01677894682255, -101.25664218334181),
            Pair(21.016799802932308, -101.25645227269689),
            Pair(21.016930153521486, -101.25632938935952),
            Pair(21.01763925873075, -101.25567028782268),
            Pair(21.017858246422755, -101.255608846154),
            Pair(21.017920814275683, -101.25549713402911),
            Pair(21.018040735920433, -101.25563118857899),
            Pair(21.018264936997575, -101.25568145903519),
            Pair(21.018322290707335, -101.25588812646622),
            Pair(21.01835357453974, -101.25619533480966),
            Pair(21.01892711030387, -101.25722308635862),
            Pair(21.01895839400937, -101.25741299697093),
            Pair(21.01907310087343, -101.25763642122071)
        ),
        zoom = 17.25,
        pov = LatLng(21.01771225466381, -101.25785425986423),
        inicio = Pair(21.018322290707335, -101.25588812646622)
    )
)

