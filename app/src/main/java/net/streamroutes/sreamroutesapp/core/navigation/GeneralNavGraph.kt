package net.streamroutes.sreamroutesapp.core.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.streamroutes.sreamroutesapp.features.authentication.presentation.choose.ChooseScreen
import net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home.ParkingMain
import net.streamroutes.sreamroutesapp.features.transportApp.presentation.home.TransportMain

@Composable
fun GeneralNavigation(
    navHostController: NavHostController
) {
    val slideInFromLeft = fadeIn()

    val slideOutToLeft = fadeOut()

    val slideInFromRight = fadeIn()

    val slideOutToRight = fadeOut()

    NavHost(
        navController = navHostController,
        startDestination = Destinations.Choose.route
    ) {
        // selecciona
        composable(
            route = Destinations.Choose.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            ChooseScreen(
                onParking = {
                    navHostController.navigate(Destinations.ParkingMain.route) {
                        launchSingleTop = true
                    }
                },
                onTransport = {
                    navHostController.navigate(Destinations.TransportMain.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // transporte
        composable(
            route = Destinations.TransportMain.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            TransportMain()
        }

        // estacionamientos
        composable(
            route = Destinations.ParkingMain.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            ParkingMain()
        }
    }
}