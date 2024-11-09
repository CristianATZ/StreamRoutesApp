package net.streamroutes.sreamroutesapp.core.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.streamroutes.sreamroutesapp.features.forum.presentation.ForumScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.MapsScreen
import net.streamroutes.sreamroutesapp.features.premium.presentation.PremiumScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.EditProfileMain
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.settings.SettingsMain
import net.streamroutes.sreamroutesapp.features.transportApp.presentation.home.HomeScreen
import net.streamroutes.sreamroutesapp.features.turism.presentation.TurismScreen

@Composable
fun TransportNavigation(
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
        startDestination = Destinations.HomeTransport.route
    ) {
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
                    navHostController.navigate(Destinations.HomeTransport.route) {
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

        // inicio
        composable(
            route = Destinations.HomeTransport.route,
            enterTransition = { slideInFromLeft },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            HomeScreen(
                onMenuPressed = onOpenMenu,
                onMapsPressed = {
                    navHostController.navigate(Destinations.Maps.route) {
                        launchSingleTop = true
                    }
                },
                onTourismPressed = {
                    navHostController.navigate(Destinations.Tourism.route) {
                        launchSingleTop = true
                    }
                },
                onForumPressed = {
                    navHostController.navigate(Destinations.Forum.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // premium
        composable(
            route = Destinations.Premium.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            PremiumScreen(
                onBackPressed = {
                    navHostController.navigate(Destinations.HomeTransport.route) {
                        launchSingleTop = true
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // mapas
        composable(
            route = Destinations.Maps.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            MapsScreen(
                onBackPressed = {
                    navHostController.navigate(Destinations.HomeTransport.route) {
                        launchSingleTop = true
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // turismo
        composable(
            route = Destinations.Tourism.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            TurismScreen(
                onBackPressed = {
                    navHostController.navigate(Destinations.HomeTransport.route) {
                        launchSingleTop = true
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // foro
        composable(
            route = Destinations.Forum.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            ForumScreen(
                onBackPressed = {
                    navHostController.navigate(Destinations.HomeTransport.route) {
                        launchSingleTop = true
                        popUpTo(0) { inclusive = true }
                    }
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
                    navHostController.navigate(Destinations.HomeTransport.route) {
                        launchSingleTop = true
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}