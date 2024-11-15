package net.streamroutes.sreamroutesapp.core.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.streamroutes.sreamroutesapp.features.authentication.presentation.choose.ChooseScreen
import net.streamroutes.sreamroutesapp.features.forum.presentation.ForumScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.MapsScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel
import net.streamroutes.sreamroutesapp.features.parking.presentation.ParkingMain
import net.streamroutes.sreamroutesapp.features.parks.presentation.parks.ParkScreen
import net.streamroutes.sreamroutesapp.features.premium.presentation.PremiumScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.history.HistoryScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.posts.SavedPostScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.EditAccountScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.EditInformation
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.EditProfileScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.routes.SavedRouteScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.apparence.ApparenceScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.maps.MapsSettingsScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.notifications.NotificationsScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.privacity.PrivacityScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.settings.SettingsScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.storage.StorageScreen
import net.streamroutes.sreamroutesapp.features.transportApp.presentation.home.TransportHomeScreen
import net.streamroutes.sreamroutesapp.features.turism.presentation.TourismScreen

@Composable
fun TransportNavigation(
    navHostController: NavHostController,
    onOpenMenu: () -> Unit,
    transportViewModel: TransportViewModel
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
        startDestination = Destinations.Select.route
    ) {
        // seleccion de funciones
        composable(
            route = Destinations.Select.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            ChooseScreen(
                onTransport = {
                    navHostController.navigate(Destinations.HomeTransport.route) {
                        launchSingleTop = true
                    }
                },
                onParking = {
                    navHostController.navigate(Destinations.HomeParking.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // inicio
        composable(
            route = Destinations.HomeTransport.route,
            enterTransition = { slideInFromRight },
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
                    navHostController.navigate(Destinations.HomeTourism.route) {
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
                },
                transportViewModel = transportViewModel
            )
        }

        // turismo
        composable(
            route = Destinations.HomeTourism.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToRight },
            popEnterTransition = { slideInFromRight },
            popExitTransition = { slideOutToRight }
        ) {
            TourismScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
                transportViewModel = transportViewModel
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

        /* SETTINGS -----------------------------------------------------------------------------------------------*/

        // ajustes
        composable(
            route = Destinations.Settings.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            SettingsScreen (
                onBackPressed = {
                    navHostController.popBackStack()
                },
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

        /* PROFILE -----------------------------------------------------------------------------------------------*/

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
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            EditProfileScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
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
                }
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

        /* PARKINGS -----------------------------------------------------------------------------------------------*/

        // main parking
        composable(
            route = Destinations.HomeParking.route,
            enterTransition = { slideInFromRight },
            exitTransition = { slideOutToLeft },
            popEnterTransition = { slideInFromLeft },
            popExitTransition = { slideOutToRight }
        ) {
            ParkingMain(
                onBackPressed = onOpenMenu,
                onProfilePressed = {
                    navHostController.navigate(Destinations.Profile.route) {
                        launchSingleTop = true
                    }
                },
                onSettingsPressed = {
                    navHostController.navigate(Destinations.Settings.route) {
                        launchSingleTop = true
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
                    navHostController.popBackStack()
                }
            )
        }
    }
}