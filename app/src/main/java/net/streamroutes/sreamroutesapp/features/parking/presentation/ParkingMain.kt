package net.streamroutes.sreamroutesapp.features.parking.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.core.navigation.SubParkingNavigation

@Composable
fun ParkingMain(
    onBackPressed: () -> Unit,
    onProfilePressed: () -> Unit,
    onSettingsPressed: () -> Unit
) {
    val subParkingNavHostController = rememberNavController()
    SubParkingNavigation(
        navHostController = subParkingNavHostController,
        onBackPressed = onBackPressed,
        onSettingsPressed = onSettingsPressed,
        onProfilePressed = onProfilePressed
    )
}