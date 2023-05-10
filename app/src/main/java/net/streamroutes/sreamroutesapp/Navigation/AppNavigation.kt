package net.streamroutes.sreamroutesapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.Screens.ForgotScreen
import net.streamroutes.sreamroutesapp.Screens.LoginScreen
import net.streamroutes.sreamroutesapp.Screens.MainScreen
import net.streamroutes.sreamroutesapp.Screens.RegistrationScreen
import net.streamroutes.sreamroutesapp.Screens.SplashScreen

@Composable
fun AppNavigation() {
    val NavController = rememberNavController()
    NavHost(navController = NavController, startDestination = AppScreens.SplashScreen.route) {
        composable(AppScreens.SplashScreen.route){
            SplashScreen(NavController)
        }
        composable(AppScreens.MainScreen.route){
            MainScreen(NavController)
        }
        composable(AppScreens.RegistrationScreen.route){
            RegistrationScreen(NavController)
        }
        composable(AppScreens.ForgotScreen.route){
            ForgotScreen(NavController)
        }
        composable(AppScreens.LoginScreen.route){
            LoginScreen(NavController)
        }
    }
}