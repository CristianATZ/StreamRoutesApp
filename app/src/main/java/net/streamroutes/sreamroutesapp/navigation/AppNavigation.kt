package net.streamroutes.sreamroutesapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.TransformOrigin
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.utils.MyViewModel
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.MainScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.ChangeScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.LanguageScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.LoginScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.RegistrationScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.SplashScreen
import net.streamroutes.sreamroutesapp.ui.start_screens.VerificationScreen
import net.streamroutes.sreamroutesapp.ui.parking_screens.MainParking
import net.streamroutes.sreamroutesapp.ui.start_screens.SelectOptionScreen
import net.streamroutes.sreamroutesapp.viewmodel.parking.AccountPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.ChangeViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.ChatViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.ConfigurationViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.FastViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.LoginViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.MainViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.ProfileViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavigation(
    myViewModel: MyViewModel,
    mainViewModel: MainViewModel = viewModel(),
    changeViewModel: ChangeViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel(),
    configurationViewModel: ConfigurationViewModel = viewModel(),
    fastViewModel: FastViewModel = viewModel(),

    homePkViewModel: HomePkViewModel = viewModel(),
    accountPkViewModel: AccountPkViewModel = viewModel(),
    parkingPkViewModel: ParkingPkViewModel = viewModel()
) {

    val NavController = rememberNavController()


    NavHost(navController = NavController, startDestination = AppScreens.MainParking
        .route) {
        // splash screen
        composable(
            route = AppScreens.SplashScreen.route
        ){
            SplashScreen(NavController,myViewModel)
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
            RegistrationScreen(myViewModel,NavController)
        }
        // confirmar telefono pantalla
        composable(
            route = AppScreens.VerificationScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            VerificationScreen(myViewModel,NavController)
        }
        // cambiar contraseña pantalla
        composable(
            route = AppScreens.ChangeScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            ChangeScreen(changeViewModel, NavController)
        }
        // inicio de sesion pantalla
        composable(
            route = AppScreens.LoginScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            LoginScreen(loginViewModel, NavController)
        }

        // LANGUAGE SCREEN
        composable(
            route = AppScreens.LanguageScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            LanguageScreen(myViewModel, NavController)
        }

        composable(
            route = AppScreens.MainParking.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            MainParking(homePkViewModel, accountPkViewModel, parkingPkViewModel)
        }

        composable(
            route = AppScreens.SelectOptionScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ){
            SelectOptionScreen(NavController)
        }
    }
}