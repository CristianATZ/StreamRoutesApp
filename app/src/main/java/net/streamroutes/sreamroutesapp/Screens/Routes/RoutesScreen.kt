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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.GTranslate
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.MenuOpen
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

        BoxWithConstraints {
            val width = constraints

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
                        IntOffset(fullSize.width+(fullSize.width/2), 0)
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
}

@Composable
fun SidePanel() {
    Column(
        Modifier
            .fillMaxWidth(0.75f)
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            .background(Color.White)
    ) {

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