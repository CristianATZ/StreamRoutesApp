package net.streamroutes.sreamroutesapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
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
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.ChangeViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.ConfigurationViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.FastViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.LoginViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.MainViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavigation(
    myViewModel: MyViewModel,
    mainViewModel: MainViewModel = viewModel(),
    changeViewModel: ChangeViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel(),
    configurationViewModel: ConfigurationViewModel = viewModel(),
    fastViewModel: FastViewModel = viewModel(),

    homePkViewModel: HomePkViewModel = viewModel()
) {
    val NavController = rememberNavController()


    NavHost(navController = NavController, startDestination = AppScreens.SplashScreen
        .route) {
        // splash screen
        composable(AppScreens.SplashScreen.route){
            SplashScreen(NavController,myViewModel)
        }
        // pantalla principal
        composable(AppScreens.MainScreen.route){
            MainScreen(mainViewModel, configurationViewModel, fastViewModel, NavController)
        }
        // registrar pantalla
        composable(AppScreens.RegistrationScreen.route){
            RegistrationScreen(myViewModel,NavController)
        }
        // confirmar telefono pantalla
        composable(AppScreens.VerificationScreen.route){
            VerificationScreen(myViewModel,NavController)
        }
        // cambiar contraseña pantalla
        composable(AppScreens.ChangeScreen.route){
            ChangeScreen(changeViewModel, NavController)
        }
        // inicio de sesion pantalla
        composable(AppScreens.LoginScreen.route){
            LoginScreen(loginViewModel, NavController)
        }

        // LANGUAGE SCREEN
        composable(AppScreens.LanguageScreen.route){
            LanguageScreen(myViewModel, NavController)
        }

        composable(AppScreens.MainParking.route){
            MainParking(homePkViewModel)
        }

        composable(AppScreens.SelectOptionScreen.route){
            SelectOptionScreen(NavController)
        }
    }
}