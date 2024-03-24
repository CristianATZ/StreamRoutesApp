package net.streamroutes.sreamroutesapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.viewmodel.MyViewModel
import net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens.ConfigurationScreen
import net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens.MapOptionsScreen
import net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens.NotificationsScreen
import net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens.PrivacityScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpAboutAppScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpCommentsScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpContactScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.HelpScreen
import net.streamroutes.sreamroutesapp.ui.screens.MainScreen
import net.streamroutes.sreamroutesapp.Screens.HelpScreens.ResenaScreen
import net.streamroutes.sreamroutesapp.Screens.MenuScreens.ChatScreen
import net.streamroutes.sreamroutesapp.Screens.ProfileScreens.ProfileScreen
import net.streamroutes.sreamroutesapp.Screens.Routes.RoutesScreen
import net.streamroutes.sreamroutesapp.Screens.MenuScreens.SuscripcionScreen
import net.streamroutes.sreamroutesapp.Screens.Routes.TripScreen
import net.streamroutes.sreamroutesapp.Screens.MenuScreens.ValoranoScreen
import net.streamroutes.sreamroutesapp.Screens.Routes.FastScreen
import net.streamroutes.sreamroutesapp.Screens.Routes.TurismScreen
import net.streamroutes.sreamroutesapp.Screens.Start.ChangeScreen
import net.streamroutes.sreamroutesapp.Screens.Start.LanguageScreen
import net.streamroutes.sreamroutesapp.Screens.Start.LoginScreen
import net.streamroutes.sreamroutesapp.Screens.Start.RegistrationScreen
import net.streamroutes.sreamroutesapp.Screens.Start.SplashScreen
import net.streamroutes.sreamroutesapp.Screens.Start.VerificationScreen


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavigation(myViewModel: MyViewModel) {
    val NavController = rememberNavController()


    NavHost(navController = NavController, startDestination = AppScreens.SplashScreen
        .route) {
        // splash screen
        composable(AppScreens.SplashScreen.route){
            SplashScreen(NavController,myViewModel)
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
            MapOptionsScreen(myViewModel,NavController)
        }

        // NOTIFICACIONES
        composable(AppScreens.NotificationsScreen.route){
            NotificationsScreen(myViewModel,NavController)
        }

        // PRIVACIDAD
        composable(AppScreens.PrivacityScreen.route){
            PrivacityScreen(myViewModel,NavController)
        }

        // VALORANOS SCREEN
        composable(AppScreens.ValoranoScreen.route){
            ValoranoScreen(myViewModel,NavController)
        }

        // LANGUAGE SCREEN
        composable(AppScreens.LanguageScreen.route){
            LanguageScreen(myViewModel, NavController)
        }

        // RESENA
        composable(AppScreens.ResenaScreen.route){
            ResenaScreen()
        }
    }
}