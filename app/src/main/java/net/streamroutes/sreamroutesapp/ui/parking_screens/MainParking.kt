package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import net.streamroutes.sreamroutesapp.viewmodel.parking.AccountPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel

enum class ParkingNavigationOptions {
    HOME_SCREEN,
    PARKING_SCREEN,
    ACCOUNT_SCREEN,
    CONF_SCREEN
}

data class NavigationItem(
    val option: ParkingNavigationOptions,
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
        mutableStateOf(ParkingNavigationOptions.HOME_SCREEN)
    }

    // lista de items
    val navigationItems = listOf(
        NavigationItem(ParkingNavigationOptions.HOME_SCREEN, Icons.Outlined.Home, Icons.Filled.Home, "Inicio"),
        NavigationItem(ParkingNavigationOptions.PARKING_SCREEN, Icons.Outlined.DirectionsCar, Icons.Filled.DirectionsCar, "Parking"),
        NavigationItem(ParkingNavigationOptions.ACCOUNT_SCREEN, Icons.Outlined.AccountCircle, Icons.Filled.AccountCircle, "Cuenta"),
        NavigationItem(ParkingNavigationOptions.CONF_SCREEN, Icons.Outlined.Settings, Icons.Filled.Settings, "Ajustes")
    )

    Scaffold(
        bottomBar = {
            ParkingBottomBar(selectedScreen = parkingScreen, items = navigationItems){ item ->
                parkingScreen = item
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            when(parkingScreen) {
                ParkingNavigationOptions.PARKING_SCREEN -> ParkingEstacionamientoScreen(parkingPkViewModel)
                ParkingNavigationOptions.ACCOUNT_SCREEN -> ParkingAccountScreen(accountPkViewModel)
                ParkingNavigationOptions.CONF_SCREEN -> ParkingConfigurationScreen()
                else -> ParkingHomeScreen(homePkViewModel, accountPkViewModel, parkingPkViewModel)
            }
        }
    }
}

@Composable
fun ParkingBottomBar(
    selectedScreen: ParkingNavigationOptions,
    items: List<NavigationItem>,
    onChangeScreen: (ParkingNavigationOptions) -> Unit,
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