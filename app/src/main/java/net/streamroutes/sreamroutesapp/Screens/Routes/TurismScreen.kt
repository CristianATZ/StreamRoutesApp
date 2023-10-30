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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.Screens.Start.TopBar
import net.streamroutes.sreamroutesapp.R
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
        }
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
                        name = "Callejón",
                        action = {
                            scope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        }
                    ),
                    RoutesOption(
                        name = "Alhóndiga",
                        action = {
                            scope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    ),
                    RoutesOption(
                        name = "Universidad",
                        action = {
                            scope.launch {
                                pagerState.animateScrollToPage(2)
                            }
                        }
                    ),
                    RoutesOption(
                        name = "Parque Bicentenario",
                        action = {
                            scope.launch {
                                pagerState.animateScrollToPage(3)
                            }
                        }
                    ),
                    RoutesOption(
                        name = "Palacio de Gobierno",
                        action = {
                            scope.launch {
                                pagerState.animateScrollToPage(4)
                            }
                        }
                    ),
                    RoutesOption(
                        name = "Minas",
                        action = {
                            scope.launch {
                                pagerState.animateScrollToPage(5)
                            }
                        }
                    ),
                    RoutesOption(
                        name = "Teatro Juárez",
                        action = {
                            scope.launch {
                                pagerState.animateScrollToPage(6)
                            }
                        }
                    ),
                    RoutesOption(
                        name = "Casa del Conde Rul",
                        action = {
                            scope.launch {
                                pagerState.animateScrollToPage(7)
                            }
                        }
                    ),
                    RoutesOption(
                        name = "Puente del Campanero",
                        action = {
                            scope.launch {
                                pagerState.animateScrollToPage(8)
                            }
                        }
                    ),
                    RoutesOption(
                        name = "Sierra de Santa Rosa",
                        action = {
                            scope.launch {
                                pagerState.animateScrollToPage(9)
                            }
                        }
                    )
                )


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
                            BottomSheet(
                                sheetState = sheetState,
                                pagerState = pagerState,
                                pageCount = pageCount,
                                list = routes_options,
                                onDismiss = {
                                    openSheet = !openSheet
                                },
                                onCalculate = {
                                    onClose()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

