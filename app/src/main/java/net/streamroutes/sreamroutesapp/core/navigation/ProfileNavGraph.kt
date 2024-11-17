package net.streamroutes.sreamroutesapp.core.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginViewModel
import net.streamroutes.sreamroutesapp.features.profile.presentation.history.HistoryScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.posts.SavedPostScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.EditAccountScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.EditInformation
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.EditProfileScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileViewModel
import net.streamroutes.sreamroutesapp.features.profile.presentation.routes.SavedRouteScreen

@Composable
fun ProfileNavigation(
    navHostController: NavHostController,
    onBackPressed: () -> Unit,
    profileViewModel: ProfileViewModel,
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
        startDestination = Destinations.HomeEditProfile.route
    ) {


        // editar perfil
        composable(
            route = Destinations.HomeEditProfile.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            EditProfileScreen(
                onBackPressed = onBackPressed,
                onEditAccount = {
                    navHostController.navigate(Destinations.EditAccount.route) {
                        launchSingleTop = true
                    }
                },
                onEditPersonalInformation = {
                    navHostController.navigate(Destinations.EditPersonalInformation.route) {
                        launchSingleTop = true
                    }
                },
                onPosts = {
                    navHostController.navigate(Destinations.Posts.route) {
                        launchSingleTop = true
                    }
                },
                onRoutes = {
                    navHostController.navigate(Destinations.Routes.route) {
                        launchSingleTop = true
                    }
                },
                onHistory = {
                    navHostController.navigate(Destinations.History.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // edit account
        composable(
            route = Destinations.EditAccount.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            EditAccountScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        // edit personal information
        composable(
            route = Destinations.EditPersonalInformation.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            EditInformation(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        // posts
        composable(
            route = Destinations.Posts.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            SavedPostScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
                profileViewModel = profileViewModel
            )
        }

        // routes
        composable(
            route = Destinations.Routes.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            SavedRouteScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        // history
        composable(
            route = Destinations.History.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            HistoryScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}