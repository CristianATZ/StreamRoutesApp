package net.streamroutes.sreamroutesapp.data.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.streamroutes.sreamroutesapp.ui.parking_screens.ApartarScreen
import net.streamroutes.sreamroutesapp.ui.parking_screens.IniciarViajeScreen
import net.streamroutes.sreamroutesapp.ui.parking_screens.MainParking
import net.streamroutes.sreamroutesapp.ui.parking_screens.ParkingAccountScreen
import net.streamroutes.sreamroutesapp.ui.parking_screens.ParkingEstacionamientoScreen
import net.streamroutes.sreamroutesapp.ui.parking_screens.ParkingHomeScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.MainScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.ChangeScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.LanguageScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.LoginScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.RegistrationScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.SelectOptionScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.SplashScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.VerificationScreen
import net.streamroutes.sreamroutesapp.utils.MyViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.AccountPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ApartarPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ViajePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.ChangeViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.ConfigurationViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.FastViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.LoginViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.MainViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavigation(
    navHostController: NavHostController,

    myViewModel: MyViewModel,
    mainViewModel: MainViewModel = viewModel(),
    changeViewModel: ChangeViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel(),
    configurationViewModel: ConfigurationViewModel = viewModel(),
    fastViewModel: FastViewModel = viewModel(),

    accountPkViewModel: AccountPkViewModel = viewModel(),
    homePkViewModel: HomePkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    viajePkViewModel: ViajePkViewModel,
    apartarPkViewModel: ApartarPkViewModel
) {
    NavHost(navController = navHostController, startDestination = AppScreens.SelectOptionScreen
        .route) {
        // splash screen
        composable(
            route = AppScreens.SplashScreen.route
        ){
            SplashScreen(navHostController,myViewModel)
        }
        // pantalla principal
        composable(
            route = AppScreens.MainScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            MainScreen(mainViewModel, configurationViewModel, fastViewModel)
        }
        // registrar pantalla
        composable(
            route = AppScreens.RegistrationScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            RegistrationScreen(myViewModel,navHostController)
        }
        // confirmar telefono pantalla
        composable(
            route = AppScreens.VerificationScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            VerificationScreen(myViewModel,navHostController)
        }
        // cambiar contraseña pantalla
        composable(
            route = AppScreens.ChangeScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            ChangeScreen(changeViewModel, navHostController)
        }
        // inicio de sesion pantalla
        composable(
            route = AppScreens.LoginScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            LoginScreen(loginViewModel, navHostController)
        }

        // LANGUAGE SCREEN
        composable(
            route = AppScreens.LanguageScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            LanguageScreen(myViewModel, navHostController)
        }

        composable(
            route = AppScreens.SelectOptionScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            SelectOptionScreen(navHostController)
        }


        // estacionamiento
        composable(
            route = AppScreens.MainParking.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            MainParking(homePkViewModel, accountPkViewModel, parkingPkViewModel, viajePkViewModel, apartarPkViewModel)
        }
    }
}

@Composable
fun ParkingNavigation(
    navHostController: NavHostController,
    accountPkViewModel: AccountPkViewModel,
    homePkViewModel: HomePkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    viajePkViewModel: ViajePkViewModel,
    apartarPkViewModel: ApartarPkViewModel
) {

    NavHost(navController = navHostController, startDestination = AppScreens.ParkingHome.route) {
        composable(AppScreens.ParkingHome.route) {
            ParkingHomeScreen(
                homePkViewModel,
                accountPkViewModel,
                apartarPkViewModel,
                viajePkViewModel,
                navHostController,
            )
        }
        composable(AppScreens.ParkingAccount.route) {
            ParkingAccountScreen(accountPkViewModel)
        }
        composable(AppScreens.ParkingEstacionamiento.route) {
            ParkingEstacionamientoScreen(parkingPkViewModel)
        }

        composable(
            route = AppScreens.ViajeParking.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            IniciarViajeScreen(
                viajePkViewModel = viajePkViewModel,
                parkingPkViewModel = parkingPkViewModel,
                navHostController = navHostController
            )
        }

        composable(
            route = AppScreens.ApartarScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            ApartarScreen(
                apartarPkViewModel = apartarPkViewModel,
                parkingPkViewModel = parkingPkViewModel,
                navHostController = navHostController
            )
        }
    }
}