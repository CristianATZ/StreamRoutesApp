@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
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
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.ui.start_screens.CustomOutlinedTextField
import net.streamroutes.sreamroutesapp.utils.MyViewModel
import org.osmdroid.util.GeoPoint


@Composable
fun RoutesScreen(
    myViewModel: MyViewModel = MyViewModel(),
    onBack: () -> Unit
){
    RoutesScreenView(onBack)
}

enum class DrawerValue {
    Open, Closed
}


@Composable
fun RoutesScreenView(
    onBack: () -> Unit
) {
    val drawerState = remember { mutableStateOf(DrawerValue.Open) }

    Scaffold(
        topBar = {
            TopBarBody(
                openPanel = {
                    if (drawerState.value == DrawerValue.Closed) {
                        drawerState.value = DrawerValue.Open
                    } else {
                        drawerState.value = DrawerValue.Closed
                    }
                },
                onBack = onBack
            ) },
    ) { paddingValues ->

        val sidePanelVisible = drawerState.value != DrawerValue.Open

        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            MainContent()

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RutaInfo() {

    var openBottomSheet by remember {
        mutableStateOf(false)
    }

    var sheetState = SheetState(
        skipPartiallyExpanded = true,
        density = LocalDensity.current,
        initialValue = SheetValue.Hidden
    )

    if(openBottomSheet) {
        BottomSheet(
            sheetState = sheetState,
            onDismiss = {
                openBottomSheet = !openBottomSheet
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onCalculate: () -> Unit
) {
    val pagerState = rememberPagerState(0){1}

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
    ) {
        Column {
            Box {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(if (pagerState.currentPage % 2 == 0) Color.LightGray else Color.DarkGray)
                ) {

                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(2){ index ->
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
            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(colorScheme.background)
            ) {
                Column(
                    Modifier
                        .padding(PaddingValues(16.dp))
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
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
fun MainContent() {
    val itsur = LatLng(20.139539228288044, -101.15073143400946)
    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(itsur, 17f)
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

        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraState,
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
}

@Composable
private fun TopBarBody(
    openPanel: () -> Unit,
    onBack: () -> Unit
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
            IconButton(onClick = { onBack() }) {
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