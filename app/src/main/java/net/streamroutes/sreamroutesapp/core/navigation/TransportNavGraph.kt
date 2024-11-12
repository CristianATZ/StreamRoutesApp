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
import net.streamroutes.sreamroutesapp.features.transportApp.presentation.home.TransportHomeScreen
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
        // inicio
        composable(
            route = Destinations.HomeTransport.route,
            enterTransition = { slideInFromLeft },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            TransportHomeScreen(
                onMenuPressed = onOpenMenu,
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

        // profile
        composable(
            route = Destinations.Profile.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            ProfileScreen (
                onBackPressed = {
                    navHostController.popBackStack()
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
                    navHostController.popBackStack()
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
                    navHostController.popBackStack()
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
                    navHostController.popBackStack()
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
                    navHostController.popBackStack()
                }
            )
        }
    }
}