package net.streamroutes.sreamroutesapp.data.navigation

sealed class AppScreens (val route: String) {

    // apartado rutas
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object RegistrationScreen: AppScreens("registration_screen")
    object VerificationScreen: AppScreens("verification_screen")
    object ChangeScreen: AppScreens("change_screen")
    object MainScreen: AppScreens("main_screen")
    object HelpScreen: AppScreens("help_screen")
    object HelpCommentsScreen: AppScreens("help_comments_screen")
    object HelpContactScreen: AppScreens("help_contact_screen")
    object HelpAboutAppScreen: AppScreens("help_about_screen")
    object ConfigurationScreen: AppScreens("configuracion_screen")
    object MapOptionsScreen: AppScreens("mapa_options_screen")
    object NotificationsScreen: AppScreens("notifications_screen")
    object PrivacityScreen: AppScreens("privacity_screen")
    object ValoranoScreen: AppScreens("valoranos_screen")
    object LanguageScreen: AppScreens("language_screen")
    object ResenaScreen: AppScreens("resena_screen")
    object SelectOptionScreen: AppScreens("select_screen")

    // apartado parkings
    object MainParking: AppScreens("parking_screen")
    object ViajeParking : AppScreens("viaje_screen")
    object ParkingHome : AppScreens("home")
    object ParkingEstacionamiento : AppScreens("estacionamiento_screen")
    object ParkingAccount : AppScreens("account_screen")
    object ApartarScreen : AppScreens("apartar_screen")
}

