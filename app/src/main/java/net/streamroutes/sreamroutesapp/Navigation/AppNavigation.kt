package net.streamroutes.sreamroutesapp.Navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.MyViewModel
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
import net.streamroutes.sreamroutesapp.Screens.Start.LanguageScreen
import net.streamroutes.sreamroutesapp.Screens.Start.LoginScreen
import net.streamroutes.sreamroutesapp.Screens.Start.RegistrationScreen
import net.streamroutes.sreamroutesapp.Screens.Start.SplashScreen
import net.streamroutes.sreamroutesapp.Screens.Start.VerificationScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppNavigation() {
    val NavController = rememberNavController()
    val myViewModel = viewModel<MyViewModel>()

    NavHost(navController = NavController, startDestination = AppScreens.SplashScreen.route) {
        // splash screen
        composable(AppScreens.SplashScreen.route){
            SplashScreen(NavController)
        }
        // pantalla principal
        composable(AppScreens.MainScreen.route){
            MainScreen(myViewModel,NavController)
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
            ChangeScreen(myViewModel,NavController)
        }
        // inicio de sesion pantalla
        composable(AppScreens.LoginScreen.route){
            LoginScreen(myViewModel,NavController)
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
            HelpAboutAppScreen(myViewModel,NavController)
        }
        composable(AppScreens.HelpCommentsScreen.route){
            HelpCommentsScreen(myViewModel,NavController)
        }
        composable(AppScreens.HelpContactScreen.route){
            HelpContactScreen(myViewModel,NavController)
        }
        composable(AppScreens.HelpScreen.route){
            HelpScreen(myViewModel,NavController)
        }
        // ROUTES
        composable(AppScreens.RoutesScreen.route){
            RoutesScreen(myViewModel,NavController)
        }
        // MENU
        composable(AppScreens.MenuScreen.route){
            MenuScreen(myViewModel, NavController)
        }

        // CONFIGURACION SCREEN
        composable(AppScreens.ConfigurationScreen.route){
            ConfigurationScreen(myViewModel,NavController)
        }

        // MAPA OPCIONES
        composable(AppScreens.MapOptionsScreen.route){
            MapOptionsScreen(myViewModel,NavController)
        }

        // CAMBIAR CIUDAD
        composable(AppScreens.ChangeCityScreen.route){
            ChangeCityScreen(myViewModel,NavController)
        }

        // NOTIFICACIONES
        composable(AppScreens.NotificationsScreen.route){
            NotificationsScreen(myViewModel,NavController)
        }

        // PRIVACIDAD
        composable(AppScreens.PrivacityScreen.route){
            PrivacityScreen(myViewModel,NavController)
        }

        // PLANIFICA TU VIAJE
        composable(AppScreens.TripScreen.route){
            TripScreen(myViewModel,NavController)
        }

        // VERSION PREMIUM
        composable(AppScreens.SuscripcionScreen.route){
            SuscripcionScreen(myViewModel,NavController)
        }

        // VALORANOS SCREEN
        composable(AppScreens.ValoranoScreen.route){
            ValoranoScreen(myViewModel,NavController)
        }

        // LANGUAGE SCREEN
        composable(AppScreens.LanguageScreen.route){
            LanguageScreen(myViewModel, NavController)
        }
    }
}