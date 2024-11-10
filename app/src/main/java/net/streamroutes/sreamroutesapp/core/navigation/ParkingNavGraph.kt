package net.streamroutes.sreamroutesapp.core.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.streamroutes.sreamroutesapp.features.parks.presentation.parks.ParkScreen
import net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home.ParkingHomeScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.EditProfileMain
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.settings.SettingsMain

@Composable
fun ParkingNavigation(
    navHostController: NavHostController,
    onOpenMenu: () -> Unit
) {
    // Definición de animaciones de transición
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
        startDestination = Destinations.HomeParking.route
    ) {
        composable(
            route = Destinations.HomeParking.route,
            enterTransition = { slideInFromLeft },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            ParkingHomeScreen(
                onOpenMenu = onOpenMenu,
                onSettingsPressed = {
                    navHostController.navigate(Destinations.Settings.route) {
                        launchSingleTop = true
                    }
                },
                onProfilePressed = {
                    navHostController.navigate(Destinations.Profile.route) {
                        launchSingleTop = true
                    }
                },
            )
        }

        // profile
        composable(
            route = Destinations.Profile.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            ProfileScreen (
                onBackPressed = {
                    navHostController.navigate(Destinations.HomeParking.route) {
                        launchSingleTop = true
                        popUpTo(0) { inclusive = true }
                    }
                },
                onEditProfile = {
                    navHostController.navigate(Destinations.EditProfile.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // edit profile
        composable(
            route = Destinations.EditProfile.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            EditProfileMain(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        // ajustes
        composable(
            route = Destinations.Settings.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            SettingsMain(
                onBackPressed = {
                    navHostController.navigate(Destinations.HomeParking.route) {
                        launchSingleTop = true
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // parqueos
        composable(
            route = Destinations.Parks.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            ParkScreen(
                onBackPressed = {
                    navHostController.navigate(Destinations.HomeParking.route) {
                        launchSingleTop = true
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}