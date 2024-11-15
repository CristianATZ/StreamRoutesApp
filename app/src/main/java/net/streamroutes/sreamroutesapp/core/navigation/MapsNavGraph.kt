package net.streamroutes.sreamroutesapp.core.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.streamroutes.sreamroutesapp.features.maps.presentation.fastest.FastestScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.planner.PlannerScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.MapRouteScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.MapStopScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel

@Composable
fun MapsNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    transportViewModel: TransportViewModel
) {
    // Entrada desde la parte superior
    val slideInFromTop = slideInVertically(
        initialOffsetY = { -it } // Entra desde la parte superior
    )

    // Salida hacia la parte superior
    val slideOutToTop = slideOutVertically(
        targetOffsetY = { -it } // Sale hacia la parte superior
    )

    // Entrada desde la parte inferior
    val slideInFromBottom = slideInVertically(
        initialOffsetY = { it } // Entra desde la parte inferior
    )

    // Salida hacia la parte inferior
    val slideOutToBottom = slideOutVertically(
        targetOffsetY = { it } // Sale hacia la parte inferior
    )

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
        startDestination = Destinations.Transport.route
    ) {

        // transporte
        composable(
            route = Destinations.Transport.route,
            enterTransition = {
                if(initialState.destination.route == Destinations.MapStops.route ||
                    initialState.destination.route == Destinations.TransportRoute.route) {
                    slideInFromTop
                } else {
                    slideInFromLeft
                }
            },
            exitTransition = {
                if(targetState.destination.route == Destinations.MapStops.route ||
                    targetState.destination.route == Destinations.TransportRoute.route) {
                    slideOutToTop
                } else {
                    slideOutToLeft
                }
            }
        ) {
            TransportScreen(
                onSelectRoute = {
                    navHostController.navigate(Destinations.TransportRoute.route) {
                        launchSingleTop = true
                    }
                },
                onSelectMap = {
                    navHostController.navigate(Destinations.MapStops.route) {
                        launchSingleTop = true
                    }
                },
                modifier = modifier,
                transportViewModel = transportViewModel
            )
        }

        // planifica
        composable(
            route = Destinations.Planner.route,
            enterTransition = {
                if (initialState.destination.route == Destinations.Transport.route) {
                    slideInFromRight
                } else {
                    slideInFromLeft
                }
            },
            exitTransition = {
                if (targetState.destination.route == Destinations.Transport.route) {
                    slideOutToRight
                } else {
                    slideOutToLeft
                }
            }
        ) {
            PlannerScreen(
                modifier = modifier
            )
        }

        // camina
        composable(
            route = Destinations.Fastest.route,
            enterTransition = {
                slideInFromRight
            },
            exitTransition = {
                slideOutToRight
            }
        ) {
            FastestScreen(
                modifier = modifier
            )
        }

        // mapa de todas las paradas
        composable(
            route = Destinations.MapStops.route,
            enterTransition = {
                slideInFromBottom
            },
            exitTransition = {
                slideOutToBottom
            }
        ) {
            MapStopScreen (
                onBackPressed = {
                    navHostController.popBackStack()
                },
                modifier = modifier
            )
        }

        // mapa de la ruta
        composable(
            route = Destinations.TransportRoute.route,
            enterTransition = {
                slideInFromBottom
            },
            exitTransition = {
                slideOutToBottom
            }
        ) {
            MapRouteScreen (
                onBackPressed = {
                    navHostController.popBackStack()
                },
                onShareLocation = {

                },
                modifier = modifier,
                transportViewModel = transportViewModel
            )
        }
    }
}