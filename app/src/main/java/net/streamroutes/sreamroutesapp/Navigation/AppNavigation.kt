package net.streamroutes.sreamroutesapp.Navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.Screens.CustomerScreen
import net.streamroutes.sreamroutesapp.Screens.ForgotScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpAboutAppScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpCommentsScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpContactScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpScreen
import net.streamroutes.sreamroutesapp.Screens.LoginScreen
import net.streamroutes.sreamroutesapp.Screens.MainScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileChangePhoneScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileConfigureScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileContactInfoScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileDataInfoScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileVariedInfoScreen
import net.streamroutes.sreamroutesapp.Screens.RegistrationScreen
import net.streamroutes.sreamroutesapp.Screens.SplashScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppNavigation() {
    val NavController = rememberNavController()
    NavHost(navController = NavController, startDestination = AppScreens.SplashScreen.route) {
        // splash screen
        composable(AppScreens.SplashScreen.route){
            SplashScreen(NavController)
        }
        // pantalla principal
        composable(AppScreens.MainScreen.route){
            MainScreen(NavController)
        }
        // registrar pantalla
        composable(AppScreens.RegistrationScreen.route){
            RegistrationScreen(NavController)
        }
        // cambiar contraseña pantalla
        composable(AppScreens.ForgotScreen.route){
            ForgotScreen(NavController)
        }
        // inicio de sesion pantalla
        composable(AppScreens.LoginScreen.route){
            LoginScreen(NavController)
        }
        // PROFILE
        composable(AppScreens.ProfileScreen.route){
            ProfileScreen(NavController)
        }
        composable(AppScreens.ProfileChangePhoneScreen.route){
            ProfileChangePhoneScreen(NavController)
        }
        composable(AppScreens.ProfileConfigureScreen.route){
            ProfileConfigureScreen(NavController)
        }
        composable(AppScreens.ProfileContactInfoScreen.route){
            ProfileContactInfoScreen(NavController)
        }
        composable(AppScreens.ProfileDataInfoScreen.route){
            ProfileDataInfoScreen(NavController)
        }
        composable(AppScreens.ProfileVariedInfoScreen.route){
            ProfileVariedInfoScreen(NavController)
        }
        // HELP
        composable(AppScreens.HelpAboutAppScreen.route){
            HelpAboutAppScreen(NavController)
        }
        composable(AppScreens.HelpCommentsScreen.route){
            HelpCommentsScreen(NavController)
        }
        composable(AppScreens.HelpContactScreen.route){
            HelpContactScreen(NavController)
        }
        composable(AppScreens.HelpScreen.route){
            HelpScreen(NavController)
        }
        // CUSTOMER
        composable(AppScreens.CustomerScreen.route){
            CustomerScreen(NavController)
        }
    }
}