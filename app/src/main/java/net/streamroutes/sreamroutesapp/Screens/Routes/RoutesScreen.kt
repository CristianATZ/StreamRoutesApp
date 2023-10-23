@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens.Routes

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.MarkerState
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.Polyline
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import kotlinx.coroutines.delay
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun RoutesScreen(myViewModel: MyViewModel, navController: NavController){
    RoutesScreenView(myViewModel,navController)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun RoutesScreenView(
    myViewModel: MyViewModel,
    navController: NavController
){

    val autobus = remember { mutableStateOf(true) }
    val paradas = remember { mutableStateOf(true) }
    var destino = remember { mutableStateOf(true) }

   /* if( autobus.value ){
        DialogAutobusCercano(
            dialogo = autobus
        ) {

        }
    }

    if( paradas.value ){
        DialogParada(
            dialogo = paradas
        ) {

        }
    }

    if( destino.value ){
        DialogDestino(
            dialogo = destino
        ) {

        }
    }*/

    // variable de tiempo
    var currentTime by remember { mutableStateOf(Calendar.getInstance().time) }

    // efecto continuo para cambiar el valor de la hora
    LaunchedEffect(Unit){
        while (true) {
            delay(500) // Esperar 0.5 segundo
            currentTime = Calendar.getInstance().time // Obtener la hora actual
        }
    }

    // modificacion de la hora
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedTime = sdf.format(currentTime)


    var fabButton by remember {
        mutableStateOf("")
    }


    // ruta 1
    Scaffold(
        topBar = { TopBarBody(navController,myViewModel) },
        floatingActionButton = { Fab(fabButton,myViewModel) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ){
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

                var dest by remember {
                    mutableStateOf("")
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedTextField(
                        value = dest,
                        onValueChange = {dest = it},
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = null,
                            )
                        },
                        trailingIcon = {
                            if(!dest.isEmpty()){
                                IconButton(
                                    onClick = { dest = "" }
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Clear,
                                        contentDescription = "Borrar texto de destino"
                                    )
                                }
                            }
                        },

                        placeholder = {
                            Text(
                                text = "Buscar destino"
                            )
                        },
                        shape = RoundedCornerShape(15),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth(0.9f)
                    )
                }
            }
        }
    }
}

object SharedState {
    var MarcadorOrigen by mutableStateOf("")
    var MarcadorDestino by mutableStateOf("")
}

@Composable
fun Botones(
    myViewModel: MyViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            shape = RoundedCornerShape(percent = 40),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 5.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp)
        ) {
            Text(
                text = "Buscar",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Fab(
    destino: String,
    myViewModel: MyViewModel
) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    if(openDialog){
        DialogBody(
            onClick = {
                openDialog = !openDialog
            },
            myViewModel = myViewModel
        )
    }

    ExtendedFloatingActionButton(
        modifier = Modifier
            .padding(16.dp),
        text = {
            Text(
                text = if (destino.isEmpty()) myViewModel.languageType().get(81) else myViewModel.languageType().get(67)
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                modifier = Modifier
                    .size(34.dp)
            )
        },
        onClick = {
            if( destino.isEmpty() ) openDialog = !openDialog
            else {
                // buscar una ruta
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun DialogBody(
    onClick: () -> Unit,
    myViewModel: MyViewModel
) {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.surface,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RutasHeader(
                    tittle = myViewModel.languageType().get(69),
                    onClick = {
                        onClick()
                    }
                )
                RutasBody(
                    onClick = { onClick() },
                    myViewModel = myViewModel
                )
            }
        }
    }
}

@Composable
fun RutasHeader(
    tittle: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = "Icono para cerrar el dialogo",
                modifier = Modifier
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = tittle,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    Divider(
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun RutasBody(
    onClick: () -> Unit,
    myViewModel: MyViewModel
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ){
                RouteInformation(
                    name = myViewModel.languageType().get(296) +" 1",
                    duration = myViewModel.languageType().get(71) + " 02:30 Hrs"
                )
                Spacer(Modifier.weight(1f))
                RouteButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if(expanded){
                // inicio y destino
                RouteDirection(
                    start = "12 de Octubre",
                    end = "Auditorio Municipal",
                    myViewModel = myViewModel
                )
                // paradas
                RouteStops(
                    myViewModel = myViewModel
                )
            }
        }
    }

    // menu de iconos
    IconsMenu(
        onClickAdd = { onClick() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteStops(
    listOf: List<String> = listOf("12 de Octubre","Av. America","Calle Querétaro",
        "Avenida Puebla","5 de Mayo", "Guadalupe Victoria", "Avenida Morelos", "Plan Sexenal",
        "Chamizal", "Chamizal esquina San Rogelio", "Blvd. Señor de Esquipulas", "Moroleón - Piñicuaro", "Salvador Díaz Mirón"),
    myViewModel: MyViewModel
) {
    Text(
        text = myViewModel.languageType().get(80),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp)
    )

    LazyRow(
        modifier = Modifier
            .padding(bottom = 8.dp)
    ){
        items(listOf.size){ index ->
            AssistChip(
                onClick = {},
                label = {
                    Text(text = listOf[index])
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    labelColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                border = AssistChipDefaults.assistChipBorder(
                    borderColor = MaterialTheme.colorScheme.tertiaryContainer,
                    borderWidth = 0.dp
                ),
                elevation = AssistChipDefaults.elevatedAssistChipElevation(
                    defaultElevation = 5.dp
                )
            )
        }
    }
}

@Composable
fun IconsMenu(
    modifier: Modifier = Modifier,
    onClickFav: () -> Unit = {},
    onClickAdd: () -> Unit = {},
    onClickShare: () -> Unit = {}
) {
    // iconos de accion FAV, agregar a la ruta como destino
    Card(
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp,MaterialTheme.colorScheme.onSurface),
        modifier = Modifier
            .padding(top = 8.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // fav
            IconButton(
                onClick = onClickFav
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Icono de favorito",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier
                .height(50.dp)
                .width(1.dp)
                .background(MaterialTheme.colorScheme.onSurfaceVariant))

            // add
            IconButton(
                onClick = onClickAdd
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Icono de agregar como destino",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier
                .height(50.dp)
                .width(1.dp)
                .background(MaterialTheme.colorScheme.onSurfaceVariant))

            // share
            IconButton(
                onClick = onClickShare
            ) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = "Icono de compartir ruta",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun RouteDirection(
    modifier: Modifier = Modifier,
    start: String = "",
    end: String = "",
    myViewModel: MyViewModel
) {
    Divider()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = myViewModel.languageType().get(76),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = start,
            fontSize = 12.sp
        )
        Text(
            text = myViewModel.languageType().get(70),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = end,
            fontSize = 12.sp
        )
    }

    Divider()
}

@Composable
fun RouteButton(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = "Mas informacion",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun RouteInformation(
    name: String = "Nombre Ruta",
    duration: String = "Duración Ruta",
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = duration,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    navController: NavController,
    myViewModel: MyViewModel
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = myViewModel.languageType().get(81)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.navigate(AppScreens.MainScreen.route) }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    )
}