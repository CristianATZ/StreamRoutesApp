@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens.Routes

import android.transition.Visibility
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.GTranslate
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.MenuOpen
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.MarkerState
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.Polyline
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun RouteOption(
    ubication: Boolean
) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    var openSheet by remember {
        mutableStateOf(false)
    }
    
    var drawerState = SheetState(
        skipPartiallyExpanded = true,
        initialValue = SheetValue.Hidden
    )

    if(openDialog){
        AlertDialog(
            onDismissRequest = { openDialog = !openDialog },
            confirmButton = {
                TextButton(onClick = { openDialog = !openDialog }) {
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

    val pageCount = 10
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        pageCount
    }

    if(openSheet){
        ModalBottomSheet(
            onDismissRequest = { openSheet = !openSheet },
            sheetState = drawerState,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.80f)
            ) {
                // carrusel
                Box(
                    Modifier.fillMaxHeight(0.4f)
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
                            .height(250.dp)
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        repeat(pageCount){ index ->
                            val color = if(pagerState.currentPage == index) colorScheme.tertiary else colorScheme.onTertiary
                            Box(modifier = Modifier
                                .padding(8.dp)
                                .background(color, CircleShape)
                                .size(10.dp))
                        }
                    }
                }

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
                                Text(
                                    text = "Nombre ruta",
                                    style = typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Informacion util necesaria de la ruta.",
                                    style = typography.bodyLarge
                                )

                                Row {
                                    RouteInfo(
                                        title = "Duracion",
                                        desc = "2 horas y 34 minutos.",
                                        type = 1
                                    )


                                    Spacer(modifier = Modifier.size(16.dp))

                                    RouteInfo(
                                        title = "Paradas totales",
                                        desc = "6",
                                        type = 1
                                    )
                                }

                                RouteInfo(
                                    title = "Inicio ruta",
                                    desc = "Calle Pipila Uriangato",
                                    type = 2
                                )

                                RouteInfo(
                                    title = "Final ruta",
                                    desc = "Calle Obregon Uriangato",
                                    type = 2
                                )
                            }
                        }
                    }
                }
            }
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
                    style = typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    color = colorScheme.onTertiary
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Ruta 1 - Pipila",
                    style = typography.titleLarge,
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
                        Row {
                            Icon(
                                imageVector = Icons.Outlined.AccessTime,
                                contentDescription = null
                            )
                            Text(
                                text = "3 min",
                                style = typography.labelMedium
                            )
                        }

                        Text(
                            text = "6, 9 min",
                            style = typography.labelMedium
                        )
                    } else {
                        IconButton(
                            onClick = {
                                openDialog = !openDialog
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = colorScheme.tertiary
                            ),
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

@Composable
fun RouteInfo(
    title: String,
    desc: String,
    type: Int
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
                .weight(
                    if( type == 1 ) 0.4f
                    else 0.8f
                )
        ) {
            Text(
                text = desc,
                style = typography.labelMedium,
                color = colorScheme.onSurfaceVariant.copy(0.5f),
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun MainContent() {
    Column(
        Modifier.fillMaxSize()
    ) {
        OpenStreetMap(
            Modifier.fillMaxSize()
        ) {

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