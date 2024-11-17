package net.streamroutes.sreamroutesapp.core.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel
import net.streamroutes.sreamroutesapp.features.turism.presentation.turismList.TourismListScreen
import net.streamroutes.sreamroutesapp.features.turism.presentation.turismList.TourismViewModel
import net.streamroutes.sreamroutesapp.features.turism.presentation.turismMap.TurismMapScreen
import net.streamroutes.sreamroutesapp.features.turism.presentation.turismRoute.TurismRouteScreen

@Composable
fun TourismNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    tourismViewModel: TourismViewModel
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

    NavHost(
        navController = navHostController,
        startDestination = Destinations.HomeTourism.route
    ) {
        // lista de puntos turisticos
        composable(
            route = Destinations.HomeTourism.route,
            enterTransition = {
                slideInFromTop
            },
            exitTransition = {
                slideOutToTop
            }
        ) {
            TourismListScreen(
                onSelectRoute = {
                    navHostController.navigate(Destinations.TourismRoute.route) {
                        launchSingleTop = true
                    }
                },
                onSelectMap = {
                    navHostController.navigate(Destinations.MapsPoints.route) {
                        launchSingleTop = true
                    }
                },
                modifier = modifier,
                tourismViewModel = tourismViewModel
            )
        }

        // mapa de puntos
        composable(
            route = Destinations.MapsPoints.route,
            enterTransition = {
                slideInFromBottom
            },
            exitTransition = {
                slideOutToBottom
            }
        ) {
            TurismMapScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
                modifier = modifier
            )
        }

        // ruta del punto turistico
        composable(
            route = Destinations.TourismRoute.route,
            enterTransition = {
                slideInFromBottom
            },
            exitTransition = {
                slideOutToBottom
            }
        ) {
            TurismRouteScreen (
                onBackPressed = {
                    navHostController.popBackStack()
                },
                onShareLocation = {

                },
                modifier = modifier,
                tourismViewModel = tourismViewModel
            )
        }
    }
}