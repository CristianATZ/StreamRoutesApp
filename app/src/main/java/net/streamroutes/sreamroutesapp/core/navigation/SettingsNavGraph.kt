package net.streamroutes.sreamroutesapp.core.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.streamroutes.sreamroutesapp.features.settings.presentation.apparence.ApparenceScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.maps.MapsSettingsScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.notifications.NotificationsScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.privacity.PrivacityScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.settings.SettingsScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.storage.StorageScreen

@Composable
fun SettingsNavigation(
    navHostController: NavHostController,
    onBackPressed: () -> Unit
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
        startDestination = Destinations.HomeSettings.route
    ) {
        // inicio ajustes
        composable(
            route = Destinations.HomeSettings.route,
            enterTransition = { slideInFromLeft },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            SettingsScreen (
                onBackPressed = onBackPressed,
                onNotificationPressed = {
                    navHostController.navigate(Destinations.NotificationsSettings.route) {
                        launchSingleTop = true
                    }
                },
                onMapsPressed = {
                    navHostController.navigate(Destinations.MapsSettings.route) {
                        launchSingleTop = true
                    }
                },
                onPrivacityPressed = {
                    navHostController.navigate(Destinations.PrivacitySettings.route) {
                        launchSingleTop = true
                    }
                },
                onStoragePressed = {
                    navHostController.navigate(Destinations.StorageSettings.route) {
                        launchSingleTop = true
                    }
                },
                onApparencePressed = {
                    navHostController.navigate(Destinations.ApparenceSettings.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // notificaciones
        composable(
            route = Destinations.NotificationsSettings.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            NotificationsScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        // mapas
        composable(
            route = Destinations.MapsSettings.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            MapsSettingsScreen (
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        // privacidad
        composable(
            route = Destinations.PrivacitySettings.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            PrivacityScreen (
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        // almacenamiento
        composable(
            route = Destinations.StorageSettings.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            StorageScreen (
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        // apariencia
        composable(
            route = Destinations.ApparenceSettings.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            ApparenceScreen (
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}