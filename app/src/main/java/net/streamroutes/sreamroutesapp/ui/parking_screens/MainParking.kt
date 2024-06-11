package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.changeStatusBar
import net.streamroutes.sreamroutesapp.viewmodel.parking.AccountPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel

data class NavigationItem(
    val option: Int,
    val iconUnselected: ImageVector,
    val iconSelected: ImageVector,
    val label: String
)

@Composable
fun MainParking(
    homePkViewModel: HomePkViewModel,
    accountPkViewModel: AccountPkViewModel,
    parkingPkViewModel: ParkingPkViewModel
) {
    var parkingScreen by remember {
        mutableIntStateOf(0)
    }

    // lista de items
    val navigationItems = listOf(
        NavigationItem(0, Icons.Outlined.Home, Icons.Filled.Home, stringResource(id = R.string.lblInicio)),
        NavigationItem(1, Icons.Outlined.DirectionsCar, Icons.Filled.DirectionsCar, stringResource(id = R.string.lblEstacionamiento)),
        NavigationItem(2, Icons.Outlined.AccountCircle, Icons.Filled.AccountCircle, stringResource(id = R.string.lblCuenta))
    )

    val systemUiController = rememberSystemUiController()

    /*LaunchedEffect(key1 = Unit) {
        homePkViewModel.fetchParkings()
        Log.d("CARGANDO", homePkViewModel.uiState.value.state.toString())
    }*/

    Scaffold(
        bottomBar = {
            ParkingBottomBar(selectedScreen = parkingScreen, items = navigationItems){ item ->
                parkingScreen = item
            }
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            changeStatusBar(systemUiController, Color.Black, Color(0xFF001A40))
            AnimatedContent(
                targetState = parkingScreen,
                label = "",
                transitionSpec = {
                    if(parkingScreen > initialState )
                        slideInHorizontally(initialOffsetX = { it }) + fadeIn() togetherWith
                                slideOutHorizontally(targetOffsetX = {-it}) + fadeOut()
                    else
                        slideInHorizontally(initialOffsetX = { -it }) + fadeIn() togetherWith
                                slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
                }
            ) {
                when(it) {
                    1 -> ParkingEstacionamientoScreen(parkingPkViewModel)
                    2 -> ParkingAccountScreen(accountPkViewModel)
                    else -> ParkingHomeScreen(
                        homePkViewModel,
                        accountPkViewModel,
                        parkingPkViewModel
                    ){
                        parkingScreen = 1
                    }
                }
            }
        }
    }
}

@Composable
fun ParkingBottomBar(
    selectedScreen: Int,
    items: List<NavigationItem>,
    onChangeScreen: (Int) -> Unit,
) {
    NavigationBar(
        containerColor = colorScheme.primary
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = selectedScreen == item.option,
                onClick = { onChangeScreen(item.option) },
                icon = {
                    Icon(
                        imageVector = if (selectedScreen == item.option) item.iconSelected else item.iconUnselected,
                        contentDescription = "icono de ${item.label}"
                    )
                },
                label = {
                    Text(text = item.label)
                },
                colors = NavigationBarItemColors(
                    selectedTextColor = colorScheme.tertiary,
                    selectedIconColor = colorScheme.onTertiary,
                    selectedIndicatorColor = colorScheme.tertiary,
                    unselectedIconColor = colorScheme.onPrimary,
                    unselectedTextColor = colorScheme.onPrimary,
                    disabledIconColor = Color.Transparent,
                    disabledTextColor = Color.Transparent
                )
            )
        }
    }
}