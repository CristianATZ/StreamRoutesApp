package net.streamroutes.sreamroutesapp.features.parking.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.core.navigation.SubParkingNavigation
import net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home.ParkingViewModel

@Composable
fun ParkingMain(
    onBackPressed: () -> Unit,
    onProfilePressed: () -> Unit,
    onSettingsPressed: () -> Unit,
    parkingViewModel: ParkingViewModel = hiltViewModel()
) {
    val subParkingNavHostController = rememberNavController()
    SubParkingNavigation(
        navHostController = subParkingNavHostController,
        onBackPressed = onBackPressed,
        onSettingsPressed = onSettingsPressed,
        onProfilePressed = onProfilePressed,
        parkingViewModel = parkingViewModel
    )
}