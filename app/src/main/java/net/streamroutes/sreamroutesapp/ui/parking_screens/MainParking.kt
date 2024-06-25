package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.data.navigation.AppScreens
import net.streamroutes.sreamroutesapp.data.navigation.ParkingNavigation
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.changeStatusBar
import net.streamroutes.sreamroutesapp.viewmodel.parking.AccountPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ViajePkViewModel

data class NavigationItem(
    val route: String,
    val icon: ImageVector,
    val iconSelected: ImageVector,
    val label: String
)

@Composable
fun MainParking(
    homePkViewModel: HomePkViewModel,
    accountPkViewModel: AccountPkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    viajePkViewModel: ViajePkViewModel,
) {
    val navHostController = rememberNavController()
    // lista de items
    val navigationItems = listOf(
        NavigationItem(AppScreens.ParkingHome.route, Icons.Outlined.Home, Icons.Filled.Home, stringResource(id = R.string.lblInicio)),
        NavigationItem(AppScreens.ParkingEstacionamiento.route, Icons.Outlined.DirectionsCar, Icons.Filled.DirectionsCar, stringResource(id = R.string.lblEstacionamiento)),
        NavigationItem(AppScreens.ParkingAccount.route, Icons.Outlined.AccountCircle, Icons.Filled.AccountCircle, stringResource(id = R.string.lblCuenta))
    )

    val systemUiController = rememberSystemUiController()

    /*LaunchedEffect(key1 = Unit) {
        homePkViewModel.fetchParkings()
        Log.d("PARKINS", homePkViewModel.uiState.value.state.toString())
    }*/

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = navBackStackEntry?.destination?.route in listOf(
                    AppScreens.ParkingHome.route,
                    AppScreens.ParkingAccount.route,
                    AppScreens.ParkingEstacionamiento.route
                ),
            ) {
                ParkingBottomBar(navHostController = navHostController, navList = navigationItems)
            }
        }
    ) { paddingValues ->
        changeStatusBar(systemUiController, Color.Black, Color(0xFF001A40))
        Column(modifier = Modifier.padding(paddingValues)) {
            ParkingNavigation(
                homePkViewModel = homePkViewModel,
                accountPkViewModel = accountPkViewModel,
                parkingPkViewModel = parkingPkViewModel,
                viajePkViewModel = viajePkViewModel,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun ParkingBottomBar(
    navHostController: NavHostController,
    navList: List<NavigationItem>
) {
    NavigationBar(
        containerColor = colorScheme.primary
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navList.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navHostController.navigate(item.route) },
                icon = {
                    Icon(
                        imageVector = if(currentRoute == item.route) item.iconSelected else item.icon,
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