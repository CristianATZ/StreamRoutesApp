package net.streamroutes.sreamroutesapp.core.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.streamroutes.sreamroutesapp.features.parking.presentation.booking.ParkingBookingScreen
import net.streamroutes.sreamroutesapp.features.parking.presentation.information.ParkingInformationScreen
import net.streamroutes.sreamroutesapp.features.parking.presentation.qr.ParkingQrScreen
import net.streamroutes.sreamroutesapp.features.parking.presentation.route.ParkingRouteScreen
import net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home.ParkingHomeScreen

@Composable
fun SubParkingNavigation(
    navHostController: NavHostController,
    onBackPressed: () -> Unit,
    onSettingsPressed: () -> Unit,
    onProfilePressed: () -> Unit
) {
    val slideInFromLeft = slideInHorizontally(
        initialOffsetX = { -it } // Entra desde la izquierda
    )

    val slideOutToLeft = slideOutHorizontally(
        targetOffsetX = { -it } // Sale hacia la izquierda
    )

    val slideInFromRight = slideInHorizontally(
        initialOffsetX = { it } // Entra desde la derecha
    )

    val slideOutToRight = slideOutHorizontally(
        targetOffsetX = { it } // Sale hacia la derecha
    )

    NavHost(
        navController = navHostController,
        startDestination = Destinations.Parking.route
    ) {
        // lista de estacionamientos
        composable(
            route = Destinations.Parking.route,
            enterTransition = { slideInFromLeft },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            ParkingHomeScreen(
                onOpenMenu = onBackPressed,
                onSettingsPressed = onSettingsPressed,
                onProfilePressed = onProfilePressed,
                onSelectParking = {
                    navHostController.navigate(Destinations.ParkingInformation.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // informacion del estacionamiento
        composable(
            route = Destinations.ParkingInformation.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            ParkingInformationScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
                onBookingPressed = {
                    navHostController.navigate(Destinations.ParkingBooking.route) {
                        launchSingleTop = true
                    }
                },
                onSelectPressed = {
                    navHostController.navigate(Destinations.ParkingRoute.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // apartar lugar
        composable(
            route = Destinations.ParkingBooking.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            ParkingBookingScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        // ruta
        composable(
            route = Destinations.ParkingRoute.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            ParkingRouteScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
                onScanPressed = {
                    navHostController.navigate(Destinations.ParkingQR.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // qr
        composable(
            route = Destinations.ParkingQR.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            ParkingQrScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}