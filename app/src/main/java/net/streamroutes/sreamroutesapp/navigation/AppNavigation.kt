package net.streamroutes.sreamroutesapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.viewmodel.MyViewModel
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.configuration.MapOptionsScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.configuration.NotificationsScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.configuration.PrivacityScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.help.HelpAboutAppScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.help.HelpCommentsScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.help.HelpContactScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.MainScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.start.ChangeScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.start.LanguageScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.start.LoginScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.start.RegistrationScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.start.SplashScreen
import net.streamroutes.sreamroutesapp.ui.routes_screens.start.VerificationScreen
import net.streamroutes.sreamroutesapp.ui.parkin_screens.MainParking
import net.streamroutes.sreamroutesapp.ui.parkin_screens.SelectOptionScreen
import net.streamroutes.sreamroutesapp.viewmodel.ChangeViewModel
import net.streamroutes.sreamroutesapp.viewmodel.ConfigurationViewModel
import net.streamroutes.sreamroutesapp.viewmodel.FastViewModel
import net.streamroutes.sreamroutesapp.viewmodel.LoginViewModel
import net.streamroutes.sreamroutesapp.viewmodel.MainViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavigation(
    myViewModel: MyViewModel,
    mainViewModel: MainViewModel = viewModel(),
    changeViewModel: ChangeViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel(),
    configurationViewModel: ConfigurationViewModel = viewModel(),
    fastViewModel: FastViewModel = viewModel()
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

        // HELP
        composable(AppScreens.HelpAboutAppScreen.route){
            HelpAboutAppScreen(myViewModel,NavController)
        }
        composable(AppScreens.HelpCommentsScreen.route){
            HelpCommentsScreen(myViewModel,NavController)
        }
        composable(AppScreens.HelpContactScreen.route){
            HelpContactScreen(myViewModel,NavController)
        }

        // MAPA OPCIONES
        composable(AppScreens.MapOptionsScreen.route){
            MapOptionsScreen(configurationViewModel)
        }

        // NOTIFICACIONES
        composable(AppScreens.NotificationsScreen.route){
            NotificationsScreen(configurationViewModel)
        }

        // PRIVACIDAD
        composable(AppScreens.PrivacityScreen.route){
            PrivacityScreen(configurationViewModel)
        }


        // LANGUAGE SCREEN
        composable(AppScreens.LanguageScreen.route){
            LanguageScreen(myViewModel, NavController)
        }

        composable(AppScreens.MainParking.route){
            MainParking()
        }

        composable(AppScreens.SelectOptionScreen.route){
            SelectOptionScreen(NavController)
        }
    }
}