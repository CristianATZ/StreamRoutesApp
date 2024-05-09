package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContentState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
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
        NavigationItem(0, Icons.Outlined.Home, Icons.Filled.Home, "Inicio"),
        NavigationItem(1, Icons.Outlined.DirectionsCar, Icons.Filled.DirectionsCar, "Parking"),
        NavigationItem(2, Icons.Outlined.AccountCircle, Icons.Filled.AccountCircle, "Cuenta"),
        NavigationItem(3, Icons.Outlined.Settings, Icons.Filled.Settings, "Ajustes")
    )

    Scaffold(
        bottomBar = {
            ParkingBottomBar(selectedScreen = parkingScreen, items = navigationItems){ item ->
                parkingScreen = item
            }
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
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
                    3 -> ParkingConfigurationScreen()
                    else -> ParkingHomeScreen(
                        homePkViewModel,
                        accountPkViewModel,
                        parkingPkViewModel
                    )
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
    NavigationBar {
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
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun View() {
    //MainParking()
}