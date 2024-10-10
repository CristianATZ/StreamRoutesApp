@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.data.model.routes.Route
import net.streamroutes.sreamroutesapp.ui.start_screens.CustomOutlinedTextField
import net.streamroutes.sreamroutesapp.viewmodel.OrsState
import net.streamroutes.sreamroutesapp.viewmodel.OrsViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.RoutesViewModel


@Composable
fun RoutesScreen(
    orsViewModel: OrsViewModel,
    routesViewModel: RoutesViewModel
){
    RoutesScreenView(orsViewModel, routesViewModel)
}

enum class DrawerValue {
    Open, Closed
}


@Composable
fun RoutesScreenView(
    orsViewModel: OrsViewModel,
    routesViewModel: RoutesViewModel
) {
    val drawerState = remember { mutableStateOf(DrawerValue.Open) }

    LaunchedEffect(Unit) {
        routesViewModel.getStaticRoutes()
    }
    val rutas by routesViewModel.routeList.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopBar(
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

        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            MainContent(orsViewModel, rutas)

            AnimatedVisibility(
                visible = sidePanelVisible
            ) {
                SidePanel()
            }
        }
    }    
}

data class RutaView(
    val name: String,
    val time: String,
    val time1: String,
    val aprox: String,
    val aprox1: String = "",
    val ubi: Boolean
)

@Composable
fun SidePanel() {

    Column(
        Modifier
            .fillMaxWidth(0.75f)
            .fillMaxHeight()
            .shadow(8.dp)
            .background(colorScheme.background)
    ) {
        BarraFiltrado()

        // lista de rutas de transporte publico
        ListaRutas()

    }
}

@Composable
fun RutaCard(ruta: Route) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Ruta: ${ruta.name}")
        Text(text = "Número de paradas: ${ruta.noStops}")
        Text(text = "Tiempo de llegada: ${ruta.arriveTime} minutos")

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Inicio:")
        //Text(text = "Lugar: ${ruta.start.place}")
        //Text(text = "Latitud: ${ruta.start.latitude}, Longitud: ${ruta.start.longitude}")

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Fin:")
        //Text(text = "Lugar: ${ruta.end.place}")
        //Text(text = "Latitud: ${ruta.end.latitude}, Longitud: ${ruta.end.longitude}")

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Duración del viaje: ${ruta.time} minutos")
    }
}

@Composable
fun ListaRutas() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            RutaInfo()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RutaInfo() {
    val scope = rememberCoroutineScope()
    var openBottomSheet by remember {
        mutableStateOf(false)
    }

    val sheetState = SheetState(
        skipPartiallyExpanded = true,
        density = LocalDensity.current,
        initialValue = SheetValue.Hidden
    )

    if(openBottomSheet) {
        BottomSheet(
            sheetState = sheetState,
            onDismiss = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion { openBottomSheet = false }
            },
            onCalculate = {

            }
        )
    }
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.background,
            contentColor = colorScheme.onBackground
        ),
        modifier = Modifier
            .fillMaxWidth(0.92f)
            .padding(vertical = 8.dp)
            .clickable { openBottomSheet = !openBottomSheet }
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(16.dp))

            Image(painter = painterResource(id = R.drawable.autobus), contentDescription = null, modifier = Modifier.size(50.dp))

            Spacer(modifier = Modifier.size(16.dp))

            Column {
                Text(text = "El charco", style = typography.headlineSmall, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.size(8.dp))


                Text(text = "3 paradas restantes", style = typography.bodyLarge)

                Text(text = "7 minutos aprox", style = typography.titleSmall, color = colorScheme.tertiary)
            }

            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun BarraFiltrado() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(8.dp))

        CustomOutlinedTextField(
            value = "",
            onValueChange = {},
            placeholderText = stringResource(id = R.string.txtFiltraAqui),
            leadingIcon = Icons.Filled.Search
        )

        Spacer(modifier = Modifier.size(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    //navController.navigate(AppScreens.SelectOptionScreen.route)
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
                    .fillMaxWidth(0.4f)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.btnFiltrar),
                    style = typography.bodyLarge
                )
            }
        }
    }
}

data class Pager(
    val painter: Painter,
    val name: String
)

@Composable
private fun BottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onCalculate: () -> Unit
) {
    val list = listOf(
        Pager(painterResource(id = R.drawable.lugar_alhondiga),"Alhóndiga de granaditas"),
        Pager(painterResource(id = R.drawable.lugar_parque_bicentenario),"Parque bicentenario"),
        Pager(painterResource(id = R.drawable.lugar_casa_conde_rul),"Casa del Conde Rul"),
        Pager(painterResource(id = R.drawable.lugar_universidad_gto),"Universidad de Guanajuato")
    )

    val pagerState = rememberPagerState(0)

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
    ) {
        Column {
            Box {
                HorizontalPager(
                    state = pagerState,
                    count = list.size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) { page ->
                    Box {
                        Image(painter = list[page].painter, contentDescription = null, modifier = Modifier.fillMaxSize())
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = list[page].name, style = typography.titleLarge, color = Color.White)
                        }
                    }
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(list.size){ index ->
                        val color = if(pagerState.currentPage == index) colorScheme.tertiary else colorScheme.onTertiary
                        val size = if(pagerState.currentPage == index) 16.dp else 8.dp
                        Box(modifier = Modifier
                            .padding(8.dp)
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
                        text = "El charco",
                        style = typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    // descripcion
                    Text(
                        text = "Un lugar coincide con tus intereses.",
                        style = typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    // botons de accion
                    LazyRow(){
                        /*items(list.size){ index ->
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
                        }*/
                    }

                    Spacer(modifier = Modifier.size(8.dp))

                    // duracion y cantidad de paradas
                    Row {
                        RouteInfo(
                            title = stringResource(id = R.string.lblDuracion),
                            desc = "2 horas y 34 minutos.",
                            value = 0.5f
                        )

                        Spacer(modifier = Modifier.size(16.dp))

                        RouteInfo(
                            title = stringResource(id = R.string.lblParadasTotales),
                            desc = "12",
                            value = 1f
                        )
                    }

                    // inicio de la ruta
                    RouteInfo(
                        title = stringResource(id = R.string.lblInicioRuta),
                        desc = "Calle Pipila"
                    )

                    // final de la ruta
                    RouteInfo(
                        title = stringResource(id = R.string.lblFinalRuta),
                        desc = "Calle Obregòn"
                    )

                    // parada mas cercana
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        RouteInfo(
                            title = stringResource(id = R.string.lblParadaCercana),
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
fun MainContent(
    orsViewModel: OrsViewModel,
    rutas: List<Route>
) {
    val index = remember { mutableStateOf(0) }
    val camera = LatLng(21.001793271283407, -101.27624380641946)
    val orsState by orsViewModel.uiState.collectAsState()

    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(camera, -5f)
    }

    var mapLoaded by remember { mutableStateOf(false) }

    Box(
        Modifier.fillMaxSize()
    ) {
        GoogleMap(
            cameraPositionState = cameraState,
            uiSettings = MapUiSettings(
                zoomControlsEnabled = true,
            ),
            properties = MapProperties(
                mapStyleOptions = MapStyleOptions(stringResource(id = R.string.mapStyleLight)),
                maxZoomPreference = 18f,
                minZoomPreference = 13f
            ),
            modifier = Modifier.fillMaxSize(),
            onMapLoaded = { mapLoaded = true }
        ) {
            if (mapLoaded) {
                // cálculo de  punto A y punto B
                val currentRoute = rutas.getOrNull(index.value)
                val puntoA = LatLng(0.0,0.0
                    //currentRoute?.start?.latitude?.toDoubleOrNull() ?: 0.0,
                    //currentRoute?.start?.longitude?.toDoubleOrNull() ?: 0.0
                )
                val puntoB = LatLng(0.0, 0.0
                    //currentRoute?.end?.latitude?.toDoubleOrNull() ?: 0.0,
                    //currentRoute?.end?.longitude?.toDoubleOrNull() ?: 0.0
                )

                // Actualizar ruta
                LaunchedEffect(puntoA, puntoB) {
                    orsViewModel.fetchRouteInfo("${puntoA.longitude},${puntoA.latitude}", "${puntoB.longitude},${puntoB.latitude}")
                }

                /*
                Marker(
                    state = MarkerState(position = puntoA),
                    title = "Inicio: ${rutas.getOrNull(index.value)?.start?.place ?: "Desconocido"}",
                    icon = BitmapDescriptorFactory.fromResource(R.drawable.bus_2)
                )

                Marker(
                    state = MarkerState(position = puntoB),
                    title = "Fin: ${rutas.getOrNull(index.value)?.end?.place ?: "Desconocido"}"
                )*/

                if (orsState.state == OrsState.SUCCESSFUL) {
                    Polyline(
                        points = orsState.geometry!!.coordinates.map { LatLng(it.last(), it.first()) },
                        color = Color.Black,
                        jointType = JointType.ROUND,
                        startCap = RoundCap(),
                        endCap = RoundCap()
                    )
                }
            }
        }

        if (rutas.isNotEmpty()) {
            val puntoA by remember {
                derivedStateOf {
                    LatLng(0.0, 0.0
                        //rutas.getOrNull(index.value)?.start?.latitude?.toDoubleOrNull() ?: 0.0,
                        //rutas.getOrNull(index.value)?.start?.longitude?.toDoubleOrNull() ?: 0.0
                        // rutas[index.value].start.latitude.toDouble(),
                        // rutas[index.value].start.longitude.toDouble()
                    )
                }
            }

            val puntoB by remember {
                derivedStateOf {
                    LatLng(0.0, 0.0
                        //rutas.getOrNull(index.value)?.end?.latitude?.toDoubleOrNull() ?: 0.0,
                        //rutas.getOrNull(index.value)?.end?.longitude?.toDoubleOrNull() ?: 0.0
                        // rutas[index.value].end.latitude.toDouble(),
                        // rutas[index.value].end.longitude.toDouble()
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    // text = rutas[index.value].name,
                    text = rutas[index.value].name + "\n" + puntoA.toString() + " - " + puntoB.toString(),
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.Black
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            if (index.value > 0) {
                                index.value -= 1
                            }
                        },
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    ) {
                        Text("Ruta Anterior")
                    }

                    Button(
                        onClick = {
                            if (index.value < rutas.size - 1) {
                                index.value += 1
                            }
                        },
                        modifier = Modifier.weight(1f).padding(start = 8.dp)
                    ) {
                        Text("Siguiente ruta")
                    }
                }
            }
        }
    }
}





@Composable
private fun TopBar(
    openPanel: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.lblTransporte)
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        openPanel()
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
                        .fillMaxWidth(0.5f)
                        .height(40.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.btnRutas),
                        style = typography.bodyLarge
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Te enviara al menu de opciones",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorScheme.background,
            titleContentColor = colorScheme.onBackground
        )
    )
}