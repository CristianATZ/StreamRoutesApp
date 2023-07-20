package net.streamroutes.sreamroutesapp.Navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens.ChangeCityScreen
import net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens.ConfigurationScreen
import net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens.MapOptionsScreen
import net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens.NotificationsScreen
import net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens.PrivacityScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpAboutAppScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpCommentsScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpContactScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpScreen
import net.streamroutes.sreamroutesapp.Screens.MainScreen
import net.streamroutes.sreamroutesapp.Screens.MenuScreens.MenuScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileChangeEmailScren
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileChangePhoneScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileConfigureScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileContactInfoScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileDataInfoScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileVariedInfoScreen
import net.streamroutes.sreamroutesapp.Screens.MenuScreens.RoutesScreen
import net.streamroutes.sreamroutesapp.Screens.MenuScreens.SuscripcionScreen
import net.streamroutes.sreamroutesapp.Screens.MenuScreens.TripScreen
import net.streamroutes.sreamroutesapp.Screens.MenuScreens.ValoranoScreen
import net.streamroutes.sreamroutesapp.Screens.Start.ChangeScreen
import net.streamroutes.sreamroutesapp.Screens.Start.LoginScreen
import net.streamroutes.sreamroutesapp.Screens.Start.RegistrationScreen
import net.streamroutes.sreamroutesapp.Screens.Start.SplashScreen
import net.streamroutes.sreamroutesapp.Screens.Start.VerificationScreen

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
        // confirmar telefono pantalla
        composable(AppScreens.VerificationScreen.route){
            VerificationScreen(NavController)
        }
        // cambiar contraseña pantalla
        composable(AppScreens.ChangeScreen.route){
            ChangeScreen(NavController)
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
        composable(AppScreens.ProfileChangeEmailScreen.route){
            ProfileChangeEmailScren(NavController)
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
        // ROUTES
        composable(AppScreens.RoutesScreen.route){
            RoutesScreen(NavController)
        }
        // MENU
        composable(AppScreens.MenuScreen.route){
            MenuScreen(NavController)
        }

        // CONFIGURACION SCREEN
        composable(AppScreens.ConfigurationScreen.route){
            ConfigurationScreen(NavController)
        }

        // MAPA OPCIONES
        composable(AppScreens.MapOptionsScreen.route){
            MapOptionsScreen(NavController)
        }

        // CAMBIAR CIUDAD
        composable(AppScreens.ChangeCityScreen.route){
            ChangeCityScreen(NavController)
        }

        // NOTIFICACIONES
        composable(AppScreens.NotificationsScreen.route){
            NotificationsScreen(NavController)
        }

        // PRIVACIDAD
        composable(AppScreens.PrivacityScreen.route){
            PrivacityScreen(NavController)
        }

        // PLANIFICA TU VIAJE
        composable(AppScreens.TripScreen.route){
            //TripScreen(NavController)
        }

        // VERSION PREMIUM
        composable(AppScreens.SuscripcionScreen.route){
            SuscripcionScreen(NavController)
        }

        // VALORANOS SCREEN
        composable(AppScreens.ValoranoScreen.route){
            ValoranoScreen(NavController)
        }
    }
}